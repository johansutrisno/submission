package com.dicoding.submission

import java.text.SimpleDateFormat
import java.util.*

class Helper {
    object Const {
        const val API_KEY = "fc7cace3c43d3bf03694157f2cd0cb7f"

        // for shared preference state
        const val SETTINGS = "settings"
        const val DAILY_REMINDER = "daily_reminder"
        const val RELEASE_REMINDER = "release_reminder"

        const val DATE_ENGLISH_YYYY_MM_DD = "yyyy-MM-dd" // 2018-10-02
    }

    object DateHelper {
        fun getCurrentDate(format: String): String {
            val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
            return simpleDateFormat.format(Date())
        }
    }
}
