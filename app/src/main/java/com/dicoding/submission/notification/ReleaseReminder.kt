package com.dicoding.submission.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.dicoding.submission.Helper
import com.dicoding.submission.Injection
import com.dicoding.submission.R
import com.dicoding.submission.data.DataSource
import com.dicoding.submission.home.MainActivity
import com.dicoding.submission.model.Movie
import com.dicoding.submission.model.Result
import java.util.*
import kotlin.collections.ArrayList

class ReleaseReminder : BroadcastReceiver() {
    private lateinit var alarmManager : AlarmManager
    private lateinit var pendingIntent : PendingIntent
    private lateinit var calendar : Calendar

    var listMovie = ArrayList<Result>()

    override fun onReceive(p0: Context?, p1: Intent?) {
        getReleasedMovie(p0!!)
    }

    private fun getReleasedMovie(context: Context) {
        val dataRepository = Injection.provideDataRepository(context)

        dataRepository.getNewRelease(Helper.DateHelper.getCurrentDate(Helper.Const.DATE_ENGLISH_YYYY_MM_DD),
                object : DataSource.GetMoviesCallback {
                    override fun onMovieLoaded(movie: Movie?) {
                        listMovie.addAll(movie?.results!!)
                        showNotification(context)
                    }

                    override fun onDataNotAvailable(errorMessage: String?) {
                        Log.e("ERROR", errorMessage)
                        showNotification(context)
                    }
                })
    }

    private fun initAlarmManager(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, ReleaseReminder::class.java)

        calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 8)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, 0)
    }

    fun setReleaseReminder(context: Context) {
        initAlarmManager(context)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    fun cancelReleaseReminder(context: Context) {
        initAlarmManager(context)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }

    private fun showNotification(context: Context) {
        val contentTitle = "Catalog Movie"
        val contentText = "There is no released movie today!"
        val subText = "Catalog Movie"

        val intent = Intent(context, MainActivity::class.java)

        val pendingIntent = TaskStackBuilder.create(context)
                .addNextIntent(intent)
                .getPendingIntent(REQUEST_CODE_APP, PendingIntent.FLAG_UPDATE_CURRENT)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            mBuilder.setChannelId(CHANNEL_ID)

            mNotificationManager?.createNotificationChannel(channel)
        }

        if (listMovie.isEmpty()) {
            mBuilder.setSmallIcon(R.drawable.ic_notifications_white_24dp)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_notifications_white_24dp))
                    .setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setSubText(subText)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

            val notification = mBuilder.build()
            mNotificationManager?.notify(0, notification)

        } else {
            for (i in 0 until listMovie.size) {
                mBuilder.setSmallIcon(R.drawable.ic_notifications_white_24dp)
                        .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_notifications_white_24dp))
                        .setContentTitle(contentTitle)
                        .setContentText("${listMovie[i].title} has been release today!")
                        .setSubText(subText)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)

                val notification = mBuilder.build()
                mNotificationManager?.notify(i, notification)
            }

        }
    }

    companion object {
        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channel_02"
        const val CHANNEL_NAME = "movies app channel"

        const val REQUEST_CODE_APP = 102
    }

}