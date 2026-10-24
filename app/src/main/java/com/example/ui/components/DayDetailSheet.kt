package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NightlightRound
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.bengali.BengaliPanjikaData
import com.example.data.model.BengaliDate
import com.example.data.model.ReminderEntity
import com.example.ui.theme.AuspiciousGreen
import com.example.ui.theme.AuspiciousGreenContainer
import com.example.ui.theme.DarkMoon
import com.example.ui.theme.FestiveGold
import com.example.ui.theme.FestiveGoldContainer
import com.example.ui.theme.FestiveGoldLight
import com.example.ui.theme.MoonGold
import com.example.ui.theme.NeutralBorder
import com.example.ui.theme.NeutralDark
import com.example.ui.theme.NeutralMedium
import com.example.ui.theme.TerracottaOnPrimaryContainer
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.TerracottaPrimaryContainer
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DayDetailSheet(
    bengaliDate: BengaliDate,
    reminders: List<ReminderEntity>,
    isBengali: Boolean,
    onAddReminderClick: () -> Unit,
    onToggleReminderCompleted: (ReminderEntity) -> Unit,
    onDeleteReminder: (ReminderEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val gregorianFormatted = bengaliDate.gregorianDate.format(
        DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH)
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("day_detail_card"),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Drag handle
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(NeutralBorder)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Main date header row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = bengaliDate.formattedBengali(isBengali),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = TerracottaPrimary,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "$gregorianFormatted (${if (isBengali) bengaliDate.weekdayBengali else bengaliDate.weekdayEnglish})",
                        style = MaterialTheme.typography.bodyMedium,
                        color = NeutralMedium,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Add Reminder quick button
                Button(
                    onClick = onAddReminderClick,
                    colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("add_reminder_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Reminder",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isBengali) "অনুস্মারক" else "Reminder",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Highlight Banners: Purnima / Amavasya / Festival
            val moonEvent = BengaliPanjikaData.getMoonPhaseEventForDate(bengaliDate.gregorianDate)
            if (moonEvent != null) {
                Surface(
                    color = if (bengaliDate.isPurnima) FestiveGoldContainer else Color(0xFF262626),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (bengaliDate.isPurnima) "🌕" else "🌑",
                            fontSize = 26.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = moonEvent.getTitle(isBengali),
                                fontWeight = FontWeight.Bold,
                                color = if (bengaliDate.isPurnima) FestiveGold else Color.White,
                                fontSize = 15.sp
                            )
                            Text(
                                text = "${moonEvent.startTime} হতে ${moonEvent.endTime}",
                                fontSize = 12.sp,
                                color = if (bengaliDate.isPurnima) Color(0xFF78350F) else Color(0xFFD1D5DB)
                            )
                            Text(
                                text = moonEvent.getSignificance(isBengali),
                                fontSize = 12.sp,
                                color = if (bengaliDate.isPurnima) Color(0xFF78350F) else Color(0xFFD1D5DB),
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            if (bengaliDate.festivalName != null && moonEvent == null) {
                Surface(
                    color = TerracottaPrimaryContainer,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🚩", fontSize = 22.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = bengaliDate.festivalName,
                                fontWeight = FontWeight.Bold,
                                color = TerracottaOnPrimaryContainer,
                                fontSize = 15.sp
                            )
                            bengaliDate.festivalSignificance?.let {
                                Text(
                                    text = it,
                                    fontSize = 12.sp,
                                    color = TerracottaOnPrimaryContainer.copy(alpha = 0.85f)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Panjika Grid (Tithi, Paksha, Nakshatra, Sunrise/Sunset)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PanjikaInfoCard(
                    title = if (isBengali) "তিথি ও পক্ষ" else "Tithi & Paksha",
                    mainText = bengaliDate.tithi,
                    subText = bengaliDate.paksha,
                    icon = Icons.Default.NightlightRound,
                    iconTint = FestiveGold,
                    modifier = Modifier.weight(1f)
                )
                PanjikaInfoCard(
                    title = if (isBengali) "সূর্যোদয় ও সূর্যাস্ত" else "Sun Times",
                    mainText = "🌅 ${bengaliDate.sunrise}",
                    subText = "🌇 ${bengaliDate.sunset}",
                    icon = Icons.Default.WbSunny,
                    iconTint = Color(0xFFEA580C),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PanjikaInfoCard(
                    title = if (isBengali) "নক্ষত্র" else "Nakshatra",
                    mainText = bengaliDate.nakshatra,
                    subText = if (isBengali) "শুভ তারা" else "Constellation",
                    icon = Icons.Default.Star,
                    iconTint = MoonGold,
                    modifier = Modifier.weight(1f)
                )
                PanjikaInfoCard(
                    title = if (isBengali) "অমৃত যোগ" else "Amrita Yoga",
                    mainText = bengaliDate.amritaYoga.substringBefore(","),
                    subText = if (isBengali) "অতি শুভ সময়" else "Highly Auspicious",
                    icon = Icons.Default.Star,
                    iconTint = AuspiciousGreen,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Rahu Kalam warning card
            Surface(
                color = Color(0xFFFEF2F2),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFECACA)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "⚠️", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isBengali) "রাহুকাল (বর্জনীয় সময়): ${bengaliDate.rahuKalam}" else "Rahu Kalam (Inauspicious): ${bengaliDate.rahuKalam}",
                        fontSize = 12.sp,
                        color = Color(0xFF991B1B),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Auspicious Occasions List for this date if any
            if (bengaliDate.isAuspiciousDay) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = AuspiciousGreenContainer,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = if (isBengali) "✨ আজকের শুভ লগ্ন ও অনুষ্ঠান:" else "✨ Auspicious Muhurat for Today:",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF065F46),
                            fontSize = 13.sp
                        )
                        bengaliDate.auspiciousOccasions.forEach { occasion ->
                            Text(
                                text = "• $occasion",
                                fontSize = 12.sp,
                                color = Color(0xFF065F46),
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }
            }

            // Personal Reminders for this date
            if (reminders.isNotEmpty()) {
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = if (isBengali) "📌 আপনার অনুস্মারক (${reminders.size}):" else "📌 Your Reminders (${reminders.size}):",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))

                reminders.forEach { reminder ->
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                IconButton(
                                    onClick = { onToggleReminderCompleted(reminder) },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = if (reminder.isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                        contentDescription = "Toggle completed",
                                        tint = if (reminder.isCompleted) AuspiciousGreen else NeutralMedium,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = reminder.title,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        color = if (reminder.isCompleted) NeutralMedium else MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "${reminder.formattedTime} • ${reminder.reminderCategory.getDisplayName(isBengali)}",
                                        fontSize = 11.sp,
                                        color = NeutralMedium
                                    )
                                }
                            }

                            IconButton(
                                onClick = { onDeleteReminder(reminder) },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Reminder",
                                    tint = Color(0xFFDC2626),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PanjikaInfoCard(
    title: String,
    mainText: String,
    subText: String,
    icon: ImageVector,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = NeutralMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = mainText,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            Text(
                text = subText,
                style = MaterialTheme.typography.labelSmall,
                color = NeutralMedium,
                maxLines = 1
            )
        }
    }
}
