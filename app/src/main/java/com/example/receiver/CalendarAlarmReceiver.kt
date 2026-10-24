package com.example.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CalendarAlarmReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_CUSTOM_REMINDER = "com.example.banglacalendar.ACTION_REMINDER"
        const val ACTION_MOON_PHASE = "com.example.banglacalendar.ACTION_MOON_PHASE"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_NOTES = "extra_notes"
        const val EXTRA_IS_PURNIMA = "extra_is_purnima"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent == null) return

        when (intent.action) {
            ACTION_CUSTOM_REMINDER -> {
                val id = intent.getLongExtra(EXTRA_ID, 0)
                val title = intent.getStringExtra(EXTRA_TITLE) ?: "ইভেন্ট অনুস্মারক"
                val notes = intent.getStringExtra(EXTRA_NOTES) ?: ""
                NotificationHelper.showReminderNotification(
                    context = context,
                    title = title,
                    notes = notes,
                    notificationId = id.toInt()
                )
            }
            ACTION_MOON_PHASE -> {
                val isPurnima = intent.getBooleanExtra(EXTRA_IS_PURNIMA, true)
                val title = intent.getStringExtra(EXTRA_TITLE) ?: "আজ পূর্ণিমা তিথি"
                val notes = intent.getStringExtra(EXTRA_NOTES) ?: ""
                NotificationHelper.showMoonPhaseNotification(
                    context = context,
                    title = title,
                    message = notes
                )
            }
        }
    }
}
