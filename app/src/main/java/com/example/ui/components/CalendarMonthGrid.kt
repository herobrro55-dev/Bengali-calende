package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.bengali.BengaliCalendarCalculator
import com.example.data.model.BengaliDate
import com.example.data.model.ReminderEntity
import com.example.ui.theme.AuspiciousGreen
import com.example.ui.theme.DarkMoon
import com.example.ui.theme.EkadashiPurple
import com.example.ui.theme.FestiveGold
import com.example.ui.theme.FestiveGoldLight
import com.example.ui.theme.MoonGold
import com.example.ui.theme.NeutralBorder
import com.example.ui.theme.NeutralDark
import com.example.ui.theme.NeutralMedium
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.TerracottaPrimaryContainer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

private data class CalendarDayItem(
    val date: LocalDate,
    val isCurrentMonth: Boolean,
    val bengaliDate: BengaliDate,
    val hasReminders: Boolean
)

@Composable
fun CalendarMonthGrid(
    currentYearMonth: YearMonth,
    selectedDate: LocalDate,
    reminders: List<ReminderEntity>,
    isBengali: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val today = remember { LocalDate.now() }

    val daysInGrid = remember(currentYearMonth, reminders) {
        val firstDay = currentYearMonth.atDay(1)
        val daysInMonth = currentYearMonth.lengthOfMonth()

        // Sunday as first day: Sunday = 0, Monday = 1, ..., Saturday = 6
        val firstDayOfWeek = firstDay.dayOfWeek.value % 7

        val list = mutableListOf<CalendarDayItem>()

        // Previous month filler days
        val prevMonth = currentYearMonth.minusMonths(1)
        val prevMonthDays = prevMonth.lengthOfMonth()
        for (i in (firstDayOfWeek - 1) downTo 0) {
            val d = prevMonth.atDay(prevMonthDays - i)
            val bDate = BengaliCalendarCalculator.getBengaliDate(d)
            val hasRem = reminders.any { it.dateEpochDay == d.toEpochDay() }
            list.add(CalendarDayItem(d, false, bDate, hasRem))
        }

        // Current month days
        for (day in 1..daysInMonth) {
            val d = currentYearMonth.atDay(day)
            val bDate = BengaliCalendarCalculator.getBengaliDate(d)
            val hasRem = reminders.any { it.dateEpochDay == d.toEpochDay() }
            list.add(CalendarDayItem(d, true, bDate, hasRem))
        }

        // Next month filler days to complete grid
        val nextMonth = currentYearMonth.plusMonths(1)
        val remainingCells = (7 - (list.size % 7)) % 7
        for (day in 1..remainingCells) {
            val d = nextMonth.atDay(day)
            val bDate = BengaliCalendarCalculator.getBengaliDate(d)
            val hasRem = reminders.any { it.dateEpochDay == d.toEpochDay() }
            list.add(CalendarDayItem(d, false, bDate, hasRem))
        }

        list
    }

    val weekdayHeaders = if (isBengali) {
        listOf("রবি", "সোম", "মঙ্গল", "বুধ", "বৃহঃ", "শুক্র", "শনি")
    } else {
        listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .testTag("calendar_month_grid")
    ) {
        // Weekday header row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            weekdayHeaders.forEachIndexed { index, name ->
                val isSunday = index == 0
                Text(
                    text = name,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isSunday) TerracottaPrimary else NeutralMedium
                )
            }
        }

        // Month grid (rows of 7 days)
        val rows = daysInGrid.chunked(7)
        rows.forEach { rowDays ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowDays.forEach { dayItem ->
                    CalendarDayCell(
                        dayItem = dayItem,
                        isSelected = dayItem.date == selectedDate,
                        isToday = dayItem.date == today,
                        isBengali = isBengali,
                        onClick = { onDateSelected(dayItem.date) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun CalendarDayCell(
    dayItem: CalendarDayItem,
    isSelected: Boolean,
    isToday: Boolean,
    isBengali: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bDate = dayItem.bengaliDate
    val isSunday = dayItem.date.dayOfWeek == DayOfWeek.SUNDAY

    val backgroundColor = when {
        isSelected -> TerracottaPrimary
        isToday -> FestiveGold.copy(alpha = 0.12f)
        !dayItem.isCurrentMonth -> Color.Transparent
        else -> MaterialTheme.colorScheme.surface
    }

    val borderModifier = when {
        isSelected -> Modifier.border(2.dp, FestiveGoldLight, RoundedCornerShape(12.dp))
        isToday -> Modifier.border(1.5.dp, FestiveGold, RoundedCornerShape(12.dp))
        else -> Modifier.border(0.5.dp, NeutralBorder.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
    }

    Surface(
        modifier = modifier
            .padding(2.dp)
            .aspectRatio(0.85f)
            .clip(RoundedCornerShape(12.dp))
            .then(borderModifier)
            .clickable { onClick() }
            .testTag("day_cell_${dayItem.date}"),
        color = backgroundColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top row: Gregorian day number + Moon badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Gregorian day
                Text(
                    text = "${dayItem.date.dayOfMonth}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = when {
                        isSelected -> Color.White.copy(alpha = 0.9f)
                        !dayItem.isCurrentMonth -> NeutralMedium.copy(alpha = 0.4f)
                        isSunday -> TerracottaPrimary
                        else -> NeutralMedium
                    }
                )

                // Moon Phase Indicator (Purnima or Amavasya)
                when {
                    bDate.isPurnima -> {
                        Text(
                            text = "🌕",
                            fontSize = 10.sp
                        )
                    }
                    bDate.isAmavasya -> {
                        Text(
                            text = "🌑",
                            fontSize = 10.sp
                        )
                    }
                    bDate.isEkadashi -> {
                        Text(
                            text = "🌺",
                            fontSize = 9.sp
                        )
                    }
                    bDate.festivalName != null -> {
                        Text(
                            text = "🚩",
                            fontSize = 9.sp
                        )
                    }
                    else -> {
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            }

            // Bengali day (Prominent in center)
            Text(
                text = if (isBengali) bDate.dayInBengaliDigits else "${bDate.day}",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = when {
                    isSelected -> Color.White
                    !dayItem.isCurrentMonth -> NeutralMedium.copy(alpha = 0.35f)
                    bDate.isPurnima -> FestiveGold
                    isSunday -> TerracottaPrimary
                    else -> MaterialTheme.colorScheme.onSurface
                }
            )

            // Bottom indicators: Reminders dot & Bengali month short
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(10.dp)
            ) {
                if (dayItem.hasReminders) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) FestiveGoldLight else AuspiciousGreen)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                }
                if (bDate.isAuspiciousDay) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color.White else FestiveGold)
                    )
                }
            }
        }
    }
}
