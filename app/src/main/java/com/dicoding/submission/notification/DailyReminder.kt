package com.dicoding.submission.notification

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dicoding.submission.home.MainActivity
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.dicoding.submission.R
import java.util.*


class DailyReminder : BroadcastReceiver() {
    private lateinit var alarmManager : AlarmManager
    private lateinit var pendingIntent : PendingIntent
    private lateinit var calendar : Calendar

    override fun onReceive(p0: Context?, p1: Intent?) {
        showNotification(p0!!)
    }

    private fun initAlarmManager(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, DailyReminder::class.java)

        calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, 0)
    }

    fun setDailyReminder(context: Context) {
        initAlarmManager(context)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    fun cancelDailyReminder(context: Context) {
        initAlarmManager(context)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }

    private fun showNotification(context: Context) {
        val contentTitle = "Catalog Movie"
        val contentText = "Catalog Movie missing you!"
        val subText = "Catalog Movie"

        val intent = Intent(context, MainActivity::class.java)

        val pendingIntent = TaskStackBuilder.create(context)
                .addNextIntent(intent)
                .getPendingIntent(REQUEST_CODE_APP, PendingIntent.FLAG_UPDATE_CURRENT)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?


        val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_white_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_notifications_white_24dp))
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSubText(subText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            mBuilder.setChannelId(CHANNEL_ID)

            mNotificationManager?.createNotificationChannel(channel)
        }

        val notification = mBuilder.build()

        mNotificationManager?.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val NOTIFICATION_ID = 100
        const val CHANNEL_ID = "channel_01"
        const val CHANNEL_NAME = "movies app channel"

        const val REQUEST_CODE_APP = 101
    }

}