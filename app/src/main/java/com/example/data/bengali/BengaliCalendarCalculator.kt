package com.example.data.bengali

import com.example.data.model.BengaliDate
import com.example.data.model.BengaliMonth
import com.example.data.model.BengaliSeason
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.floor

object BengaliCalendarCalculator {

    private val TITHI_NAMES = arrayOf(
        "শুক্লা প্রতিপদ", "শুক্লা দ্বিতীয়া", "শুক্লা তৃতীয়া", "শুক্লা চতুর্থী",
        "শুক্লা পঞ্চমী", "শুক্লা ষষ্ঠী", "শুক্লা সপ্তমী", "শুক্লা অষ্টমী",
        "শুক্লা নবমী", "শুক্লা দশমী", "শুক্লা একাদশী", "শুক্লা দ্বাদশী",
        "শুক্লা ত্রয়োদশী", "শুক্লা চতুর্দশী", "পূর্ণিমা",
        "কৃষ্ণা প্রতিপদ", "কৃষ্ণা দ্বিতীয়া", "কৃষ্ণা তৃতীয়া", "কৃষ্ণা চতুর্থী",
        "কৃষ্ণা পঞ্চমী", "কৃষ্ণা ষষ্ঠী", "কৃষ্ণা সপ্তমী", "কৃষ্ণা অষ্টমী",
        "কৃষ্ণা নবমী", "কৃষ্ণা দশমী", "কৃষ্ণা একাদশী", "কৃষ্ণা দ্বাদশী",
        "কৃষ্ণা ত্রয়োদশী", "কৃষ্ণা চতুর্দশী", "অমাবস্যা"
    )

    private val NAKSHATRAS = arrayOf(
        "অশ্বিনী", "ভরণী", "কৃত্তিকা", "রোহিণী", "মৃগশিরা", "আর্দ্রা",
        "পুনর্বসু", "পুষ্যা", "অশ্লেষা", "মঘা", "পূর্বফল্গুনী", "উত্তরফল্গুনী",
        "হস্ত", "চিত্রা", "স্বাতী", "বিশাখা", "অনুরাধা", "জ্যেষ্ঠা",
        "মূল", "পূর্বাষাঢ়া", "উত্তরাষাঢ়া", "শ্রবণা", "ধনিষ্ঠা", "শতভিষা",
        "পূর্বভাদ্রপদ", "উত্তরভাদ্রপদ", "রেবতী"
    )

    // Reference New Moon (Amavasya) epoch: Jan 11, 2024 ~11:57 UTC
    private val REF_NEW_MOON_DATE = LocalDate.of(2024, 1, 11)
    private const val SYNODIC_MONTH = 29.530588853

    fun getBengaliDate(date: LocalDate): BengaliDate {
        val (bengaliYear, bengaliMonth, bengaliDay) = calculateBengaliDateNumbers(date)

        val season = when (bengaliMonth) {
            BengaliMonth.BOISHAKH, BengaliMonth.JOISHTHO -> BengaliSeason.GRISHMA
            BengaliMonth.ASHARH, BengaliMonth.SRABON -> BengaliSeason.BARSHA
            BengaliMonth.BHADRO, BengaliMonth.ASHWIN -> BengaliSeason.SHARAT
            BengaliMonth.KARTIK, BengaliMonth.AGRAHAYAN -> BengaliSeason.HEMANTA
            BengaliMonth.POUSH, BengaliMonth.MAGH -> BengaliSeason.SHEET
            BengaliMonth.FALGUN, BengaliMonth.CHOITRO -> BengaliSeason.BASANTA
        }

        val weekdayBengali = getWeekdayBengali(date.dayOfWeek)
        val weekdayEnglish = date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }

        // Astronomical approximate tithi calculation
        val daysSinceRef = ChronoUnit.DAYS.between(REF_NEW_MOON_DATE, date).toDouble()
        val moonAge = ((daysSinceRef % SYNODIC_MONTH) + SYNODIC_MONTH) % SYNODIC_MONTH
        val tithiIndex = ((moonAge / SYNODIC_MONTH) * 30.0).toInt().coerceIn(0, 29)
        val tithi = TITHI_NAMES[tithiIndex]

        val isPurnima = tithiIndex == 14 || BengaliPanjikaData.isPurnimaDate(date)
        val isAmavasya = tithiIndex == 29 || BengaliPanjikaData.isAmavasyaDate(date)
        val isEkadashi = tithiIndex == 10 || tithiIndex == 25

        val paksha = if (tithiIndex < 15) "শুক্ল পক্ষ (Bright Half)" else "কৃষ্ণ পক্ষ (Dark Half)"

        // Nakshatra approximate cycle
        val nakshatraIndex = ((daysSinceRef * 1.037) % 27.0).toInt().let { if (it < 0) it + 27 else it }
        val nakshatra = NAKSHATRAS[nakshatraIndex % NAKSHATRAS.size]

        // Sunrise & sunset times based on seasonal day of year
        val dayOfYear = date.dayOfYear
        val sunriseMinutes = (5 * 60 + 15) + (25 * kotlin.math.sin((dayOfYear + 10) * 2 * Math.PI / 365)).toInt()
        val sunsetMinutes = (17 * 60 + 45) - (45 * kotlin.math.sin((dayOfYear + 10) * 2 * Math.PI / 365)).toInt()

        val sunrise = formatTime(sunriseMinutes / 60, sunriseMinutes % 60)
        val sunset = formatTime(sunsetMinutes / 60, sunsetMinutes % 60)

        // Auspicious times (Amrita & Mahendra Yoga)
        val amritaYoga = getAmritaYoga(date.dayOfWeek)
        val mahendraYoga = getMahendraYoga(date.dayOfWeek)
        val rahuKalam = getRahuKalam(date.dayOfWeek)

        val festival = BengaliPanjikaData.getFestivalForDate(date)
        val auspiciousOccasions = BengaliPanjikaData.getAuspiciousEventsForDate(date)

        return BengaliDate(
            day = bengaliDay,
            month = bengaliMonth,
            year = bengaliYear,
            gregorianDate = date,
            season = season,
            weekdayBengali = weekdayBengali,
            weekdayEnglish = weekdayEnglish,
            tithi = if (isPurnima) "পূর্ণিমা তিথি (Purnima)" else if (isAmavasya) "অমাবস্যা তিথি (Amavasya)" else tithi,
            paksha = paksha,
            nakshatra = nakshatra,
            sunrise = sunrise,
            sunset = sunset,
            amritaYoga = amritaYoga,
            mahendraYoga = mahendraYoga,
            rahuKalam = rahuKalam,
            isPurnima = isPurnima,
            isAmavasya = isAmavasya,
            isEkadashi = isEkadashi,
            festivalName = festival?.nameBengali,
            festivalSignificance = festival?.descriptionBengali,
            isAuspiciousDay = auspiciousOccasions.isNotEmpty(),
            auspiciousOccasions = auspiciousOccasions
        )
    }

    private fun calculateBengaliDateNumbers(date: LocalDate): Triple<Int, BengaliMonth, Int> {
        val gYear = date.year
        val poilaBoishakhThisYear = LocalDate.of(gYear, 4, 14)

        val startPoilaBoishakh: LocalDate
        val bengaliYear: Int

        if (date.isBefore(poilaBoishakhThisYear)) {
            startPoilaBoishakh = LocalDate.of(gYear - 1, 4, 14)
            bengaliYear = gYear - 594
        } else {
            startPoilaBoishakh = poilaBoishakhThisYear
            bengaliYear = gYear - 593
        }

        val daysDifference = ChronoUnit.DAYS.between(startPoilaBoishakh, date).toInt()

        val isLeapYear = (gYear % 4 == 0 && gYear % 100 != 0) || (gYear % 400 == 0)
        val monthLengths = intArrayOf(
            31, // Boishakh
            31, // Joishtho
            31, // Asharh
            31, // Srabon
            31, // Bhadro
            30, // Ashwin
            30, // Kartik
            30, // Agrahayan
            30, // Poush
            30, // Magh
            if (isLeapYear) 30 else 29, // Falgun
            30  // Choitro
        )

        var accumulatedDays = 0
        val months = BengaliMonth.values()

        for (i in monthLengths.indices) {
            val length = monthLengths[i]
            if (daysDifference < accumulatedDays + length) {
                val day = (daysDifference - accumulatedDays) + 1
                return Triple(bengaliYear, months[i], day)
            }
            accumulatedDays += length
        }

        // Fallback for end of Choitro
        return Triple(bengaliYear, BengaliMonth.CHOITRO, 30)
    }

    private fun getWeekdayBengali(dayOfWeek: DayOfWeek): String {
        return when (dayOfWeek) {
            DayOfWeek.SUNDAY -> "রবিবার (Sunday)"
            DayOfWeek.MONDAY -> "সোমবার (Monday)"
            DayOfWeek.TUESDAY -> "মঙ্গলবার (Tuesday)"
            DayOfWeek.WEDNESDAY -> "বুধবার (Wednesday)"
            DayOfWeek.THURSDAY -> "বৃহস্পতিবার (Thursday)"
            DayOfWeek.FRIDAY -> "শুক্রবার (Friday)"
            DayOfWeek.SATURDAY -> "শনিবার (Saturday)"
        }
    }

    private fun formatTime(hour: Int, minute: Int): String {
        val h = if (hour < 10) "0$hour" else "$hour"
        val m = if (minute < 10) "0$minute" else "$minute"
        return "$h:$m"
    }

    private fun getAmritaYoga(dayOfWeek: DayOfWeek): String {
        return when (dayOfWeek) {
            DayOfWeek.SUNDAY -> "সকাল ০৭:১৫ - ০৯:৩০, রাত ১১:৪৫ - ০১:১০"
            DayOfWeek.MONDAY -> "সকাল ০৬:৪০ - ০৮:৫৫, দুপুর ০১:২০ - ০২:৪৫"
            DayOfWeek.TUESDAY -> "সকাল ০৮:২০ - ১০:৩৫, বিকাল ০৪:১০ - ০৫:৩০"
            DayOfWeek.WEDNESDAY -> "সকাল ০৯:১০ - ১১:২৫, দুপুর ০২:০৫ - ০৩:৩০"
            DayOfWeek.THURSDAY -> "সকাল ০৬:৩০ - ০৮:৪৫, রাত ০৮:২০ - ১০:০০"
            DayOfWeek.FRIDAY -> "সকাল ০৭:৫০ - ১০:০৫, দুপুর ১২:৪০ - ০২:০০"
            DayOfWeek.SATURDAY -> "সকাল ০৬:১৫ - ০৮:৩০, বিকাল ০৩:১৫ - ০৪:৪০"
        }
    }

    private fun getMahendraYoga(dayOfWeek: DayOfWeek): String {
        return when (dayOfWeek) {
            DayOfWeek.SUNDAY -> "দুপুর ০১:১০ - ০২:৪০"
            DayOfWeek.MONDAY -> "সকাল ১১:০০ - ১২:৩০"
            DayOfWeek.TUESDAY -> "দুপুর ০২:১৫ - ০৩:৪৫"
            DayOfWeek.WEDNESDAY -> "সকাল ০৭:৩০ - ০৯:০০"
            DayOfWeek.THURSDAY -> "দুপুর ১২:০০ - ০১:৩০"
            DayOfWeek.FRIDAY -> "বিকাল ০৩:৩০ - ০৫:০০"
            DayOfWeek.SATURDAY -> "সকাল ১০:৩০ - ১২:০০"
        }
    }

    private fun getRahuKalam(dayOfWeek: DayOfWeek): String {
        return when (dayOfWeek) {
            DayOfWeek.SUNDAY -> "বিকাল ০৪:৩০ - ০৬:০০"
            DayOfWeek.MONDAY -> "সকাল ০৭:৩০ - ০৯:০০"
            DayOfWeek.TUESDAY -> "দুপুর ০৩:০০ - ০৪:৩০"
            DayOfWeek.WEDNESDAY -> "দুপুর ১২:০০ - ০১:৩০"
            DayOfWeek.THURSDAY -> "দুপুর ০১:৩০ - ০৩:০০"
            DayOfWeek.FRIDAY -> "সকাল ১০:৩০ - ১২:০০"
            DayOfWeek.SATURDAY -> "সকাল ০৯:০০ - ১০:৩০"
        }
    }
}
