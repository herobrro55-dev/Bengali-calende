package com.example.data.model

import java.time.LocalDate

enum class MoonPhaseType(val bengaliLabel: String, val englishLabel: String) {
    PURNIMA("পূর্ণিমা (Full Moon)", "Purnima (Full Moon)"),
    AMAVASYA("অমাবস্যা (New Moon)", "Amavasya (New Moon)")
}

data class MoonPhaseEvent(
    val id: String,
    val date: LocalDate,
    val type: MoonPhaseType,
    val bengaliMonth: BengaliMonth,
    val bengaliDay: Int,
    val bengaliYear: Int,
    val bengaliName: String,
    val englishName: String,
    val startTime: String,
    val endTime: String,
    val significanceBengali: String,
    val significanceEnglish: String,
    val ritualsBengali: String,
    val ritualsEnglish: String
) {
    fun getTitle(isBengali: Boolean): String = if (isBengali) bengaliName else englishName
    fun getSignificance(isBengali: Boolean): String = if (isBengali) significanceBengali else significanceEnglish
    fun getRituals(isBengali: Boolean): String = if (isBengali) ritualsBengali else ritualsEnglish
}
