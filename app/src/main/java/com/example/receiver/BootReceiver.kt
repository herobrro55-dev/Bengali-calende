package com.example.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.data.local.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val pendingResult = goAsync()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val db = AppDatabase.getDatabase(context)
                    val todayEpoch = LocalDate.now().toEpochDay()
                    val reminders = db.reminderDao().getUpcomingReminders(todayEpoch).first()

                    for (reminder in reminders) {
                        if (reminder.notifyEnabled) {
                            val triggerTime = reminder.localDate
                                .atTime(reminder.localTime)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                                .toEpochMilli()

                            NotificationHelper.scheduleEventReminder(
                                context = context,
                                reminderId = reminder.id,
                                title = reminder.title,
                                notes = reminder.notes,
                                triggerEpochMillis = triggerTime
                            )
                        }
                    }
                } catch (e: Exception) {
                    // Log or handle safely
                } finally {
                    pendingResult.finish()
                }
            }
        }
    }
}
