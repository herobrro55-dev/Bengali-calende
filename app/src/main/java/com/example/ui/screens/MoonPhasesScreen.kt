package com.example.ui.screens

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.bengali.BengaliCalendarCalculator
import com.example.data.bengali.BengaliPanjikaData
import com.example.data.model.BengaliDate
import com.example.data.model.MoonPhaseEvent
import com.example.data.model.MoonPhaseType
import com.example.receiver.NotificationHelper
import com.example.ui.theme.DarkMoon
import com.example.ui.theme.FestiveGold
import com.example.ui.theme.FestiveGoldContainer
import com.example.ui.theme.FestiveGoldLight
import com.example.ui.theme.MoonGold
import com.example.ui.theme.NeutralBorder
import com.example.ui.theme.NeutralDark
import com.example.ui.theme.NeutralMedium
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.TerracottaPrimaryContainer
import com.example.ui.viewmodel.CalendarViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

@Composable
fun MoonPhasesScreen(
    viewModel: CalendarViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isBengali by viewModel.isBengaliLanguage.collectAsState()
    val purnimaNotify by viewModel.purnimaNotifyEnabled.collectAsState()
    val amavasyaNotify by viewModel.amavasyaNotifyEnabled.collectAsState()
    val notifyDaysBefore by viewModel.notifyDaysBefore.collectAsState()

    var selectedFilter by remember { mutableStateOf<MoonPhaseType?>(null) }
    var testSentMessage by remember { mutableStateOf<String?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        // Permission result handled
    }

    val today = remember { LocalDate.now() }
    val nextMoonEvent = remember(today) { BengaliPanjikaData.getNextMoonEvent(today) }

    val filteredEvents = remember(selectedFilter) {
        if (selectedFilter == null) {
            BengaliPanjikaData.MOON_PHASE_EVENTS
        } else {
            BengaliPanjikaData.MOON_PHASE_EVENTS.filter { it.type == selectedFilter }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("moon_phases_screen")
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))

            // Screen Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = FestiveGoldContainer,
                    shape = CircleShape,
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "🌕", fontSize = 22.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = if (isBengali) "পূর্ণিমা ও অমাবস্যা নির্দেশিকা" else "Purnima & Amavasya",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TerracottaPrimary
                    )
                    Text(
                        text = if (isBengali) "প্রতি মাসের তিথি ও বিশেষ পূজা অনুস্মারক" else "Monthly moon phases & reminder alerts",
                        style = MaterialTheme.typography.bodySmall,
                        color = NeutralMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hero Next Upcoming Moon Event Card
            if (nextMoonEvent != null) {
                val daysUntil = ChronoUnit.DAYS.between(today, nextMoonEvent.date)
                val isPurnima = nextMoonEvent.type == MoonPhaseType.PURNIMA

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isPurnima) Color(0xFF451A03) else Color(0xFF18181B)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = if (isPurnima) FestiveGold else Color(0xFF3F3F46),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = if (isPurnima) "🌕 আসন্ন পূর্ণিমা" else "🌑 আসন্ন অমাবস্যা",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }

                            Text(
                                text = when {
                                    daysUntil == 0L -> if (isBengali) "আজকেই অনুষ্ঠিত!" else "Happening Today!"
                                    daysUntil == 1L -> if (isBengali) "আগামীকাল!" else "Tomorrow!"
                                    else -> if (isBengali) "${BengaliDate.convertToBengaliDigits(daysUntil.toInt())} দিন বাকি" else "$daysUntil days left"
                                },
                                color = FestiveGoldLight,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = nextMoonEvent.getTitle(isBengali),
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        val gregorianDateStr = nextMoonEvent.date.format(
                            DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH)
                        )
                        val bengaliDateStr = if (isBengali) {
                            "${BengaliDate.convertToBengaliDigits(nextMoonEvent.bengaliDay)} ${nextMoonEvent.bengaliMonth.bengaliName} ${BengaliDate.convertToBengaliDigits(nextMoonEvent.bengaliYear)} বঙ্গাব্দ"
                        } else {
                            "${nextMoonEvent.bengaliDay} ${nextMoonEvent.bengaliMonth.englishName} ${nextMoonEvent.bengaliYear} Bangabda"
                        }

                        Text(
                            text = "$bengaliDateStr ($gregorianDateStr)",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "⏰ লগ্ন সময়: ${nextMoonEvent.startTime} হতে ${nextMoonEvent.endTime}",
                            color = FestiveGoldLight,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = nextMoonEvent.getSignificance(isBengali),
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = { viewModel.jumpToDate(nextMoonEvent.date) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isPurnima) FestiveGold else Color(0xFF52525B)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (isBengali) "ক্যালেন্ডারে বিস্তারিত দেখুন" else "View in Calendar")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Notification Settings Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("notification_settings_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.NotificationsActive,
                            contentDescription = null,
                            tint = TerracottaPrimary,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isBengali) "পূর্ণিমা ও অমাবস্যা নোটিফিকেশন অ্যালার্ট" else "Push Notification Settings",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = TerracottaPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Purnima switch
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isBengali) "🌕 পূর্ণিমা অ্যালার্ট" else "🌕 Purnima Alerts",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = if (isBengali) "প্রতি পূর্ণিমার পূর্বে পূজা ও ব্রত অনুস্মারক" else "Remind before every Purnima",
                                fontSize = 12.sp,
                                color = NeutralMedium
                            )
                        }
                        Switch(
                            checked = purnimaNotify,
                            onCheckedChange = {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                                }
                                viewModel.togglePurnimaNotify(it)
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = TerracottaPrimary
                            ),
                            modifier = Modifier.testTag("purnima_notification_toggle")
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Amavasya switch
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isBengali) "🌑 অমাবস্যা অ্যালার্ট" else "🌑 Amavasya Alerts",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = if (isBengali) "প্রতি অমাবস্যার পূর্বে তর্পণ ও পূজা অনুস্মারক" else "Remind before every Amavasya",
                                fontSize = 12.sp,
                                color = NeutralMedium
                            )
                        }
                        Switch(
                            checked = amavasyaNotify,
                            onCheckedChange = {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                                }
                                viewModel.toggleAmavasyaNotify(it)
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = TerracottaPrimary
                            ),
                            modifier = Modifier.testTag("amavasya_notification_toggle")
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Notification Timing
                    Text(
                        text = if (isBengali) "অ্যালার্টের সময়:" else "Reminder Timing:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (notifyDaysBefore == 0) TerracottaPrimary else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .weight(1f)
                                .clickable { viewModel.setNotifyTimingDaysBefore(0) }
                        ) {
                            Text(
                                text = if (isBengali) "ইভেন্টের দিনে সকাল ৭:০০" else "Day of Event (7 AM)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (notifyDaysBefore == 0) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (notifyDaysBefore == 1) TerracottaPrimary else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .weight(1f)
                                .clickable { viewModel.setNotifyTimingDaysBefore(1) }
                        ) {
                            Text(
                                text = if (isBengali) "১ দিন আগে সন্ধ্যা ৬:০০" else "1 Day Before (6 PM)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (notifyDaysBefore == 1) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Test notification buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                                }
                                viewModel.sendTestNotification(isPurnima = true)
                                testSentMessage = if (isBengali) "🌕 পূর্ণিমার টেস্ট নোটিফিকেশন পাঠানো হয়েছে!" else "🌕 Purnima test alert sent!"
                            },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("test_purnima_notification_button")
                        ) {
                            Text(
                                text = if (isBengali) "টেস্ট পূর্ণিমা অ্যালার্ট" else "Test Purnima",
                                fontSize = 11.sp
                            )
                        }

                        OutlinedButton(
                            onClick = {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                                }
                                viewModel.sendTestNotification(isPurnima = false)
                                testSentMessage = if (isBengali) "🌑 অমাবস্যার টেস্ট নোটিফিকেশন পাঠানো হয়েছে!" else "🌑 Amavasya test alert sent!"
                            },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("test_amavasya_notification_button")
                        ) {
                            Text(
                                text = if (isBengali) "টেস্ট অমাবস্যা অ্যালার্ট" else "Test Amavasya",
                                fontSize = 11.sp
                            )
                        }
                    }

                    if (testSentMessage != null) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = testSentMessage!!,
                            color = TerracottaPrimary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filter row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterChip(
                    selected = selectedFilter == null,
                    onClick = { selectedFilter = null },
                    label = { Text(if (isBengali) "সকল তিথি" else "All Tithis") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = TerracottaPrimaryContainer,
                        selectedLabelColor = TerracottaPrimary
                    )
                )

                FilterChip(
                    selected = selectedFilter == MoonPhaseType.PURNIMA,
                    onClick = { selectedFilter = MoonPhaseType.PURNIMA },
                    label = { Text(if (isBengali) "পূর্ণিমা 🌕" else "Purnima 🌕") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = FestiveGoldContainer,
                        selectedLabelColor = FestiveGold
                    )
                )

                FilterChip(
                    selected = selectedFilter == MoonPhaseType.AMAVASYA,
                    onClick = { selectedFilter = MoonPhaseType.AMAVASYA },
                    label = { Text(if (isBengali) "অমাবস্যা 🌑" else "Amavasya 🌑") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFE4E4E7),
                        selectedLabelColor = Color(0xFF18181B)
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        // List of Moon Phase Events
        items(filteredEvents) { event ->
            MoonPhaseItemCard(
                event = event,
                isBengali = isBengali,
                onViewInCalendar = { viewModel.jumpToDate(event.date) }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
private fun MoonPhaseItemCard(
    event: MoonPhaseEvent,
    isBengali: Boolean,
    onViewInCalendar: () -> Unit
) {
    val isPurnima = event.type == MoonPhaseType.PURNIMA

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("moon_event_card_${event.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (isPurnima) "🌕" else "🌑",
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = event.getTitle(isBengali),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isPurnima) FestiveGold else NeutralDark
                        )
                        val bengaliDateStr = if (isBengali) {
                            "${BengaliDate.convertToBengaliDigits(event.bengaliDay)} ${event.bengaliMonth.bengaliName} ${BengaliDate.convertToBengaliDigits(event.bengaliYear)} বঙ্গাব্দ"
                        } else {
                            "${event.bengaliDay} ${event.bengaliMonth.englishName} ${event.bengaliYear} Bangabda"
                        }
                        Text(
                            text = bengaliDateStr,
                            style = MaterialTheme.typography.bodySmall,
                            color = NeutralMedium
                        )
                    }
                }

                Surface(
                    color = if (isPurnima) FestiveGoldContainer else Color(0xFFF4F4F5),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = event.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                        color = if (isPurnima) Color(0xFF78350F) else Color(0xFF3F3F46),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⏰ ${if (isBengali) "তিথি সময়:" else "Timing:"} ${event.startTime} - ${event.endTime}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = event.getSignificance(isBengali),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "🌸 ${if (isBengali) "ব্রত ও নিয়ম:" else "Rituals:"} ${event.getRituals(isBengali)}",
                fontSize = 12.sp,
                color = NeutralMedium,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = onViewInCalendar,
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isBengali) "পঞ্জিকায় দেখুন" else "View in Panjika",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
