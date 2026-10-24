package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

enum class ReminderCategory(val bengaliName: String, val englishName: String, val emoji: String) {
    PUJA("পূজা ও অর্চনা", "Puja & Worship", "🪔"),
    FASTING("ব্রত ও উপবাস", "Fasting & Brata", "🌸"),
    BIRTHDAY("জন্মদিন / বিবাহবার্ষিকী", "Birthday & Anniversary", "🎂"),
    AUSPICIOUS("শুভ অনুষ্ঠান", "Auspicious Occasion", "✨"),
    PERSONAL("ব্যক্তিগত কাজ", "Personal Task", "📌");

    fun getDisplayName(isBengali: Boolean): String = if (isBengali) "$emoji $bengaliName" else "$emoji $englishName"
}

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val notes: String = "",
    val dateEpochDay: Long,
    val timeHour: Int = 9,
    val timeMinute: Int = 0,
    val category: String = ReminderCategory.PUJA.name,
    val notifyEnabled: Boolean = true,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    val localDate: LocalDate
        get() = LocalDate.ofEpochDay(dateEpochDay)

    val localTime: LocalTime
        get() = LocalTime.of(timeHour, timeMinute)

    val formattedTime: String
        get() = localTime.format(DateTimeFormatter.ofPattern("hh:mm a"))

    val reminderCategory: ReminderCategory
        get() = try {
            ReminderCategory.valueOf(category)
        } catch (e: Exception) {
            ReminderCategory.PERSONAL
        }
}
