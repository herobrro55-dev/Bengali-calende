package com.example

import com.example.data.bengali.BengaliCalendarCalculator
import com.example.data.bengali.BengaliPanjikaData
import com.example.data.model.BengaliDate
import com.example.data.model.BengaliMonth
import com.example.data.model.MoonPhaseType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class ExampleUnitTest {
    @Test
    fun testBengaliDigitsConversion() {
        assertEquals("০", BengaliDate.convertToBengaliDigits(0))
        assertEquals("১", BengaliDate.convertToBengaliDigits(1))
        assertEquals("২৫", BengaliDate.convertToBengaliDigits(25))
        assertEquals("১৪৩৩", BengaliDate.convertToBengaliDigits(1433))
    }

    @Test
    fun testPoilaBoishakhCalculation() {
        // April 14, 2026 should be 1 Boishakh 1433
        val date = LocalDate.of(2026, 4, 14)
        val bDate = BengaliCalendarCalculator.getBengaliDate(date)

        assertEquals(1, bDate.day)
        assertEquals(BengaliMonth.BOISHAKH, bDate.month)
        assertEquals(1433, bDate.year)
    }

    @Test
    fun testPrePoilaBoishakhYearCalculation() {
        // March 20, 2026 is before April 14, so it should be year 1432
        val date = LocalDate.of(2026, 3, 20)
        val bDate = BengaliCalendarCalculator.getBengaliDate(date)

        assertEquals(1432, bDate.year)
        assertEquals(BengaliMonth.CHOITRO, bDate.month)
    }

    @Test
    fun testPurnimaAndAmavasyaDetection() {
        // Sept 25, 2026 is Kojagari Lakshmi Purnima
        val purnimaDate = LocalDate.of(2026, 9, 25)
        assertTrue(BengaliPanjikaData.isPurnimaDate(purnimaDate))

        // Oct 10, 2026 is Dipannita Kali Puja Amavasya
        val amavasyaDate = LocalDate.of(2026, 10, 10)
        assertTrue(BengaliPanjikaData.isAmavasyaDate(amavasyaDate))
    }

    @Test
    fun testNextMoonEvent() {
        val date = LocalDate.of(2026, 8, 1)
        val next = BengaliPanjikaData.getNextMoonEvent(date)
        assertNotNull(next)
        assertTrue(!next!!.date.isBefore(date))
    }
}
