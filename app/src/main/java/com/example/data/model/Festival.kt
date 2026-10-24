package com.example.data.model

import java.time.LocalDate

enum class FestivalType(val bengaliName: String, val englishName: String) {
    PUJA_BRATA("পূজা ও ব্রত", "Puja & Fasting"),
    HOLIDAY("সরকারি / জাতীয় ছুটি", "Public & National Holiday"),
    CULTURAL("বাঙালি সংস্কৃতি ও উৎসব", "Cultural & Historical")
}

data class Festival(
    val id: String,
    val nameBengali: String,
    val nameEnglish: String,
    val date: LocalDate,
    val bengaliDateStr: String,
    val type: FestivalType,
    val descriptionBengali: String,
    val descriptionEnglish: String,
    val isMajorFestival: Boolean = false
) {
    fun getName(isBengali: Boolean): String = if (isBengali) nameBengali else nameEnglish
    fun getDescription(isBengali: Boolean): String = if (isBengali) descriptionBengali else descriptionEnglish
}
