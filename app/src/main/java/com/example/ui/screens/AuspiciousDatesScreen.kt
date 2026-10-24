package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Stars
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.bengali.BengaliPanjikaData
import com.example.data.model.AuspiciousCategory
import com.example.data.model.AuspiciousDate
import com.example.data.model.ReminderCategory
import com.example.ui.dialogs.AddReminderDialog
import com.example.ui.theme.AuspiciousGreen
import com.example.ui.theme.AuspiciousGreenContainer
import com.example.ui.theme.FestiveGold
import com.example.ui.theme.FestiveGoldContainer
import com.example.ui.theme.FestiveGoldLight
import com.example.ui.theme.NeutralMedium
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.TerracottaPrimaryContainer
import com.example.ui.viewmodel.CalendarViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun AuspiciousDatesScreen(
    viewModel: CalendarViewModel,
    modifier: Modifier = Modifier
) {
    val isBengali by viewModel.isBengaliLanguage.collectAsState()
    val selectedCategory by viewModel.selectedAuspiciousCategory.collectAsState()

    var reminderTargetDate by remember { mutableStateOf<LocalDate?>(null) }

    val filteredList = remember(selectedCategory) {
        if (selectedCategory == null) {
            BengaliPanjikaData.AUSPICIOUS_DATES
        } else {
            BengaliPanjikaData.AUSPICIOUS_DATES.filter { it.category == selectedCategory }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("auspicious_dates_screen")
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))

            // Header Banner
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = AuspiciousGreenContainer,
                    shape = CircleShape,
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "🪔", fontSize = 22.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = if (isBengali) "শুভ দিন ও শুভ মুহূর্ত" else "Auspicious Dates & Muhurat",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TerracottaPrimary
                    )
                    Text(
                        text = if (isBengali) "বিবাহ, গৃহপ্রবেশ, অন্নপ্রাশন ও ব্যবসা আরম্ভের লগ্ন" else "Marriage, housewarming, ceremonies & new ventures",
                        style = MaterialTheme.typography.bodySmall,
                        color = NeutralMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Category Chips horizontal scroller
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { viewModel.setSelectedAuspiciousCategory(null) },
                        label = { Text(if (isBengali) "সকল শুভ তিথি" else "All Occasions") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TerracottaPrimaryContainer,
                            selectedLabelColor = TerracottaPrimary
                        )
                    )
                }

                items(AuspiciousCategory.values()) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { viewModel.setSelectedAuspiciousCategory(category) },
                        label = { Text("${category.iconEmoji} ${category.getDisplayName(isBengali)}") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = FestiveGoldContainer,
                            selectedLabelColor = Color(0xFF78350F)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        items(filteredList) { item ->
            AuspiciousDateCard(
                item = item,
                isBengali = isBengali,
                onViewInCalendar = { viewModel.jumpToDate(item.date) },
                onAddReminder = { reminderTargetDate = item.date }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }

    if (reminderTargetDate != null) {
        AddReminderDialog(
            initialDate = reminderTargetDate!!,
            isBengali = isBengali,
            onDismiss = { reminderTargetDate = null },
            onSave = { title, notes, date, time, category, notify ->
                viewModel.addReminder(title, notes, date, time, category, notify)
                reminderTargetDate = null
            }
        )
    }
}

@Composable
private fun AuspiciousDateCard(
    item: AuspiciousDate,
    isBengali: Boolean,
    onViewInCalendar: () -> Unit,
    onAddReminder: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("auspicious_item_${item.id}"),
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
                    Text(text = item.category.iconEmoji, fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.category.getDisplayName(isBengali),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TerracottaPrimary
                    )
                }

                Surface(
                    color = FestiveGoldContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = if (isBengali) item.bengaliDateStr else item.bengaliDateStrEn,
                        color = Color(0xFF78350F),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val gregorianFormatted = item.date.format(
                DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH)
            )
            Text(
                text = "📅 $gregorianFormatted",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "🌙 ${item.tithi}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(6.dp)
                    )
                }
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "⭐ ${item.nakshatra}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                color = AuspiciousGreenContainer.copy(alpha = 0.6f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "⏰ ${if (isBengali) "শুভ ক্ষণ:" else "Auspicious Hours:"} ${item.getHours(isBengali)}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF065F46),
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.getDescription(isBengali),
                fontSize = 12.sp,
                color = NeutralMedium,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = onAddReminder,
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AddAlert,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isBengali) "স্মরণ রাখুন" else "Set Reminder",
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onViewInCalendar,
                    colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
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
                        text = if (isBengali) "পঞ্জিকায় দেখুন" else "Panjika",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
