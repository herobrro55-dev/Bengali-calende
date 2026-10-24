package com.example.data.model

import java.time.LocalDate

enum class AuspiciousCategory(
    val bengaliName: String,
    val englishName: String,
    val iconEmoji: String
) {
    MARRIAGE("বিবাহের শুভ লগ্ন", "Marriage Dates", "💍"),
    GRIHA_PRAVESH("গৃহপ্রবেশ", "Housewarming", "🏡"),
    ANNAPRASHAN("অন্নপ্রাশন (মুখে ভাত)", "First Rice Ceremony", "🍚"),
    UPANAYAN("উপনয়ন (পৈতে)", "Sacred Thread Ceremony", "🪔"),
    VEHICLE_PURCHASE("গাড়ি ক্রয়", "Vehicle Purchase", "🚗"),
    BUSINESS_START("ব্যবসা আরম্ভ / শুভ হালখাতা", "Business Opening", "💼"),
    NAMKARAN("নামকরণ", "Naming Ceremony", "👶");

    fun getDisplayName(isBengali: Boolean): String = if (isBengali) bengaliName else englishName
}

data class AuspiciousDate(
    val id: String,
    val date: LocalDate,
    val category: AuspiciousCategory,
    val bengaliDateStr: String,
    val bengaliDateStrEn: String,
    val tithi: String,
    val nakshatra: String,
    val auspiciousHoursBengali: String,
    val auspiciousHoursEnglish: String,
    val descriptionBengali: String,
    val descriptionEnglish: String
) {
    fun getHours(isBengali: Boolean): String = if (isBengali) auspiciousHoursBengali else auspiciousHoursEnglish
    fun getDescription(isBengali: Boolean): String = if (isBengali) descriptionBengali else descriptionEnglish
}
