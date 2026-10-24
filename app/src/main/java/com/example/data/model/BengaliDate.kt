package com.example.data.model

import java.time.LocalDate

enum class BengaliMonth(
    val bengaliName: String,
    val englishName: String,
    val standardDays: Int,
    val seasonBengali: String,
    val seasonEnglish: String
) {
    BOISHAKH("বৈশাখ", "Boishakh", 31, "গ্রীষ্ম", "Summer"),
    JOISHTHO("জ্যৈষ্ঠ", "Joishtho", 31, "গ্রীষ্ম", "Summer"),
    ASHARH("আষাঢ়", "Asharh", 31, "বর্ষা", "Monsoon"),
    SRABON("শ্রাবণ", "Srabon", 31, "বর্ষা", "Monsoon"),
    BHADRO("ভাদ্র", "Bhadro", 31, "শরৎ", "Autumn"),
    ASHWIN("আশ্বিন", "Ashwin", 30, "শরৎ", "Autumn"),
    KARTIK("কার্তিক", "Kartik", 30, "হেমন্ত", "Late Autumn"),
    AGRAHAYAN("অগ্রহায়ণ", "Agrahayan", 30, "হেমন্ত", "Late Autumn"),
    POUSH("পৌষ", "Poush", 30, "শীত", "Winter"),
    MAGH("মাঘ", "Magh", 30, "শীত", "Winter"),
    FALGUN("ফাল্গুন", "Falgun", 30, "বসন্ত", "Spring"),
    CHOITRO("চৈত্র", "Choitro", 30, "বসন্ত", "Spring");

    fun getDisplayName(isBengali: Boolean): String = if (isBengali) bengaliName else englishName
}

enum class BengaliSeason(val bengaliName: String, val englishName: String) {
    GRISHMA("গ্রীষ্ম", "Summer"),
    BARSHA("বর্ষা", "Monsoon"),
    SHARAT("শরৎ", "Autumn"),
    HEMANTA("হেমন্ত", "Late Autumn"),
    SHEET("শীত", "Winter"),
    BASANTA("বসন্ত", "Spring")
}

data class BengaliDate(
    val day: Int,
    val month: BengaliMonth,
    val year: Int,
    val gregorianDate: LocalDate,
    val season: BengaliSeason,
    val weekdayBengali: String,
    val weekdayEnglish: String,
    val tithi: String,
    val paksha: String,
    val nakshatra: String,
    val sunrise: String,
    val sunset: String,
    val amritaYoga: String,
    val mahendraYoga: String,
    val rahuKalam: String,
    val isPurnima: Boolean = false,
    val isAmavasya: Boolean = false,
    val isEkadashi: Boolean = false,
    val festivalName: String? = null,
    val festivalSignificance: String? = null,
    val isAuspiciousDay: Boolean = false,
    val auspiciousOccasions: List<String> = emptyList()
) {
    val dayInBengaliDigits: String
        get() = convertToBengaliDigits(day)

    val yearInBengaliDigits: String
        get() = convertToBengaliDigits(year)

    fun formattedBengali(isBengali: Boolean): String {
        return if (isBengali) {
            "${convertToBengaliDigits(day)} ${month.bengaliName}, ${convertToBengaliDigits(year)} বঙ্গাব্দ"
        } else {
            "$day ${month.englishName}, $year Bangabda"
        }
    }

    companion object {
        fun convertToBengaliDigits(number: Int): String {
            val bengaliDigits = charArrayOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')
            val str = number.toString()
            val sb = StringBuilder()
            for (ch in str) {
                if (ch in '0'..'9') {
                    sb.append(bengaliDigits[ch - '0'])
                } else {
                    sb.append(ch)
                }
            }
            return sb.toString()
        }
    }
}
