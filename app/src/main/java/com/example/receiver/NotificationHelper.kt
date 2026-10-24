package com.example.receiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.MainActivity
import com.example.data.model.MoonPhaseEvent
import com.example.data.model.MoonPhaseType
import java.time.LocalDate
import java.time.ZoneId

object NotificationHelper {

    const val CHANNEL_MOON = "bengali_calendar_moon_channel"
    const val CHANNEL_REMINDERS = "bengali_calendar_reminders_channel"

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val moonChannel = NotificationChannel(
                CHANNEL_MOON,
                "Purnima & Amavasya Alerts (পূর্ণিমা ও অমাবস্যা)",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifies users on upcoming Purnima and Amavasya tithis with auspicious timings"
                enableVibration(true)
            }

            val reminderChannel = NotificationChannel(
                CHANNEL_REMINDERS,
                "Event Reminders (ইভেন্ট ও ব্রত অনুস্মারক)",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifies users about personal events, puja, and auspicious occasions"
                enableVibration(true)
            }

            notificationManager.createNotificationChannel(moonChannel)
            notificationManager.createNotificationChannel(reminderChannel)
        }
    }

    fun hasNotificationPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    fun showMoonPhaseNotification(
        context: Context,
        title: String,
        message: String,
        notificationId: Int = 1001
    ) {
        if (!hasNotificationPermission(context)) return

        createNotificationChannels(context)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_MOON)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notification)
    }

    fun showReminderNotification(
        context: Context,
        title: String,
        notes: String,
        notificationId: Int = 2001
    ) {
        if (!hasNotificationPermission(context)) return

        createNotificationChannels(context)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val fullText = if (notes.isNotBlank()) "$notes\nআজকের বিশেষ তিথি ও সময় স্মরণ রাখুন।" else "আজকের বিশেষ তিথি ও সময় স্মরণ রাখুন।"

        val notification = NotificationCompat.Builder(context, CHANNEL_REMINDERS)
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setContentTitle("⏰ $title")
            .setContentText(fullText)
            .setStyle(NotificationCompat.BigTextStyle().bigText(fullText))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notification)
    }

    fun triggerTestNotification(context: Context, isPurnima: Boolean, isBengali: Boolean) {
        val title = if (isPurnima) {
            if (isBengali) "🌕 আজ পবিত্র পূর্ণিমা তিথি!" else "🌕 Today is Auspicious Purnima!"
        } else {
            if (isBengali) "🌑 আজ অমাবস্যা তিথি!" else "🌑 Today is Auspicious Amavasya!"
        }

        val message = if (isPurnima) {
            if (isBengali) "শুভ পূর্ণিমার পুণ্য লগ্নে সত্যনারায়ণ ব্রত, পূজা ও দেববন্দনার বিশেষ সময়। সংসারে সুখ ও শান্তি বিরাজ করুক।"
            else "Full Moon brings divine peace. Ideal time for Satyanarayan Puja and spiritual prayers."
        } else {
            if (isBengali) "অমাবস্যা তিথিতে পিতৃপুরুষের তর্পণ ও মা কালীর আরাধনায় অশুভ শক্তি দূরীভূত হয়। শুভ শক্তির জয় হোক।"
            else "New Moon tithi: Ideal for ancestral peace prayers and Mother Kali's protective grace."
        }

        showMoonPhaseNotification(context, title, message, if (isPurnima) 3001 else 3002)
    }

    fun scheduleEventReminder(
        context: Context,
        reminderId: Long,
        title: String,
        notes: String,
        triggerEpochMillis: Long
    ) {
        if (triggerEpochMillis <= System.currentTimeMillis()) return

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, CalendarAlarmReceiver::class.java).apply {
            action = CalendarAlarmReceiver.ACTION_CUSTOM_REMINDER
            putExtra(CalendarAlarmReceiver.EXTRA_ID, reminderId)
            putExtra(CalendarAlarmReceiver.EXTRA_TITLE, title)
            putExtra(CalendarAlarmReceiver.EXTRA_NOTES, notes)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminderId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerEpochMillis,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    triggerEpochMillis,
                    pendingIntent
                )
            }
        } catch (e: SecurityException) {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                triggerEpochMillis,
                pendingIntent
            )
        }
    }

    fun cancelEventReminder(context: Context, reminderId: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, CalendarAlarmReceiver::class.java).apply {
            action = CalendarAlarmReceiver.ACTION_CUSTOM_REMINDER
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminderId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}
