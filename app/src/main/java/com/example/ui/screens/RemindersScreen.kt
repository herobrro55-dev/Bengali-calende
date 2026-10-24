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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.bengali.BengaliCalendarCalculator
import com.example.data.model.ReminderEntity
import com.example.ui.dialogs.AddReminderDialog
import com.example.ui.theme.AuspiciousGreen
import com.example.ui.theme.FestiveGold
import com.example.ui.theme.FestiveGoldContainer
import com.example.ui.theme.NeutralMedium
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.TerracottaPrimaryContainer
import com.example.ui.viewmodel.CalendarViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun RemindersScreen(
    viewModel: CalendarViewModel,
    modifier: Modifier = Modifier
) {
    val isBengali by viewModel.isBengaliLanguage.collectAsState()
    val allReminders by viewModel.allReminders.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }
    var filterType by remember { mutableStateOf(0) } // 0: Upcoming, 1: All, 2: Completed

    val todayEpoch = remember { LocalDate.now().toEpochDay() }

    val displayedReminders = remember(allReminders, filterType) {
        when (filterType) {
            0 -> allReminders.filter { it.dateEpochDay >= todayEpoch && !it.isCompleted }
            1 -> allReminders
            2 -> allReminders.filter { it.isCompleted }
            else -> allReminders
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag("reminders_screen"),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = TerracottaPrimary,
                contentColor = Color.White,
                modifier = Modifier.testTag("fab_add_custom_reminder")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Reminder"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = TerracottaPrimaryContainer,
                        shape = CircleShape,
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.NotificationsActive,
                                contentDescription = null,
                                tint = TerracottaPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (isBengali) "আপনার ব্যক্তিগত অনুস্মারক" else "Personal Reminders",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = TerracottaPrimary
                        )
                        Text(
                            text = if (isBengali) "ব্রত, পূজা ও পারিবারিক অনুষ্ঠানের তালিকা" else "Fasting, Puja & family event notifications",
                            style = MaterialTheme.typography.bodySmall,
                            color = NeutralMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Filter tabs
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = filterType == 0,
                        onClick = { filterType = 0 },
                        label = { Text(if (isBengali) "আসন্ন (${allReminders.count { it.dateEpochDay >= todayEpoch && !it.isCompleted }})" else "Upcoming") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TerracottaPrimaryContainer,
                            selectedLabelColor = TerracottaPrimary
                        )
                    )

                    FilterChip(
                        selected = filterType == 1,
                        onClick = { filterType = 1 },
                        label = { Text(if (isBengali) "সকল (${allReminders.size})" else "All") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = FestiveGoldContainer,
                            selectedLabelColor = Color(0xFF78350F)
                        )
                    )

                    FilterChip(
                        selected = filterType == 2,
                        onClick = { filterType = 2 },
                        label = { Text(if (isBengali) "সম্পন্ন (${allReminders.count { it.isCompleted }})" else "Completed") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFFE4E4E7),
                            selectedLabelColor = Color(0xFF18181B)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            if (displayedReminders.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "🪔", fontSize = 36.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (isBengali) "কোনো অনুস্মারক পাওয়া যায়নি" else "No reminders found",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isBengali) "যেকোনো তারিখের পূজা, ব্রত বা পারিবারিক অনুষ্ঠানের জন্য '+' বোতামে চাপুন।" else "Tap '+' to add a reminder for any puja, brata, or family event.",
                                style = MaterialTheme.typography.bodySmall,
                                color = NeutralMedium,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                items(displayedReminders, key = { it.id }) { reminder ->
                    ReminderListItemCard(
                        reminder = reminder,
                        isBengali = isBengali,
                        onToggleCompleted = { viewModel.toggleReminderCompleted(reminder) },
                        onDelete = { viewModel.deleteReminder(reminder) },
                        onJumpToDate = { viewModel.jumpToDate(reminder.localDate) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }

    if (showAddDialog) {
        AddReminderDialog(
            initialDate = LocalDate.now(),
            isBengali = isBengali,
            onDismiss = { showAddDialog = false },
            onSave = { title, notes, date, time, category, notify ->
                viewModel.addReminder(title, notes, date, time, category, notify)
                showAddDialog = false
            }
        )
    }
}

@Composable
private fun ReminderListItemCard(
    reminder: ReminderEntity,
    isBengali: Boolean,
    onToggleCompleted: () -> Unit,
    onDelete: () -> Unit,
    onJumpToDate: () -> Unit
) {
    val bDate = remember(reminder.dateEpochDay) {
        BengaliCalendarCalculator.getBengaliDate(reminder.localDate)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("reminder_item_${reminder.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    IconButton(
                        onClick = onToggleCompleted,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (reminder.isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                            contentDescription = "Toggle status",
                            tint = if (reminder.isCompleted) AuspiciousGreen else TerracottaPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = reminder.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (reminder.isCompleted) NeutralMedium else MaterialTheme.colorScheme.onSurface,
                            textDecoration = if (reminder.isCompleted) TextDecoration.LineThrough else null
                        )
                        Text(
                            text = reminder.reminderCategory.getDisplayName(isBengali),
                            style = MaterialTheme.typography.labelSmall,
                            color = FestiveGold
                        )
                    }
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color(0xFFDC2626),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onJumpToDate() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val gregorianFormatted = reminder.localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                    Text(
                        text = "📅 $gregorianFormatted (${bDate.formattedBengali(isBengali)})",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "⏰ ${reminder.formattedTime}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = TerracottaPrimary
                        )
                        if (reminder.notifyEnabled) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.Alarm,
                                contentDescription = "Alarm On",
                                tint = AuspiciousGreen,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    }
                }
            }

            if (reminder.notes.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = reminder.notes,
                    fontSize = 12.sp,
                    color = NeutralMedium,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}
