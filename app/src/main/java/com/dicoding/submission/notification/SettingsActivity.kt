package com.dicoding.submission.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.RadioGroup
import com.dicoding.submission.Helper
import com.dicoding.submission.Injection
import com.dicoding.submission.R
import com.dicoding.submission.data.DataRepository
import com.dicoding.submission.data.DataSource
import com.dicoding.submission.model.Movie
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val dataRepository : DataRepository = Injection.provideDataRepository(this)

        toggle_daily_reminder.isChecked = dataRepository.dailyReminderState
        toggle_release_reminder.isChecked = dataRepository.releaseReminderState

        val dailyReminder = DailyReminder()
        val releaseReminder = ReleaseReminder()

        toggle_release_reminder.setOnCheckedChangeListener { _, p1 ->
            dataRepository.saveReleaseReminderState(p1)
            when(p1) {
                true -> dailyReminder.setDailyReminder(this)
                false -> dailyReminder.cancelDailyReminder(this)
            }
        }

        toggle_daily_reminder.setOnCheckedChangeListener { _, p1 ->
            dataRepository.saveDailyReminderState(p1)
            when(p1) {
                true -> releaseReminder.setReleaseReminder(this)
                false -> releaseReminder.cancelReleaseReminder(this)

            }
        }

    }
}
