package com.example.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.data.bengali.BengaliCalendarCalculator
import com.example.data.model.ReminderCategory
import com.example.ui.theme.FestiveGold
import com.example.ui.theme.FestiveGoldContainer
import com.example.ui.theme.TerracottaPrimary
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun AddReminderDialog(
    initialDate: LocalDate,
    isBengali: Boolean,
    onDismiss: () -> Unit,
    onSave: (title: String, notes: String, date: LocalDate, time: LocalTime, category: ReminderCategory, notify: Boolean) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(ReminderCategory.PUJA) }
    var hour by remember { mutableIntStateOf(8) }
    var minute by remember { mutableIntStateOf(0) }
    var isAm by remember { mutableStateOf(true) }
    var notifyEnabled by remember { mutableStateOf(true) }
    var titleError by remember { mutableStateOf(false) }

    val bDate = remember(initialDate) { BengaliCalendarCalculator.getBengaliDate(initialDate) }

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.testTag("add_reminder_dialog"),
        title = {
            Column {
                Text(
                    text = if (isBengali) "নতুন অনুস্মারক যোগ করুন" else "Add Event Reminder",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = TerracottaPrimary
                )
                Text(
                    text = "${bDate.formattedBengali(isBengali)} (${initialDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))})",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Title field
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                        if (it.isNotBlank()) titleError = false
                    },
                    label = { Text(if (isBengali) "অনুষ্ঠানের নাম / ব্রত" else "Event / Puja Title") },
                    placeholder = { Text(if (isBengali) "উদাঃ সত্যনারায়ণ পূজা, জন্মদিন" else "e.g. Satyanarayan Puja") },
                    isError = titleError,
                    supportingText = if (titleError) {
                        { Text(if (isBengali) "শিরোনাম প্রদান করুন" else "Title is required") }
                    } else null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("reminder_title_input")
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Category Chips
                Text(
                    text = if (isBengali) "বিভাগ:" else "Category:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(ReminderCategory.values()) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(category.getDisplayName(isBengali), fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = FestiveGoldContainer,
                                selectedLabelColor = Color(0xFF78350F)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Time Selection Row
                Text(
                    text = if (isBengali) "সময় নির্ধারণ:" else "Reminder Time:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Quick time slots
                    val quickTimes = listOf(
                        Triple(6, 0, "সকাল ৬:০০"),
                        Triple(8, 0, "সকাল ৮:০০"),
                        Triple(12, 0, "দুপুর ১২:০০"),
                        Triple(18, 0, "সন্ধ্যা ৬:০০")
                    )

                    quickTimes.forEach { (h, m, label) ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (hour == h && minute == m) TerracottaPrimary else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    hour = h
                                    minute = m
                                }
                        ) {
                            Text(
                                text = if (isBengali) label else "${if (h > 12) h - 12 else if (h == 0) 12 else h}:${if (m < 10) "0$m" else "$m"} ${if (h >= 12) "PM" else "AM"}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (hour == h && minute == m) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Notes field
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text(if (isBengali) "মন্তব্য / পূজার দ্রব্যাদি (ঐচ্ছিক)" else "Notes / Ritual details (optional)") },
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("reminder_notes_input")
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Notification toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Alarm,
                            contentDescription = null,
                            tint = TerracottaPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isBengali) "নোটিফিকেশন অ্যালার্ট" else "Push Notification",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Switch(
                        checked = notifyEnabled,
                        onCheckedChange = { notifyEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = TerracottaPrimary
                        ),
                        modifier = Modifier.testTag("notification_switch")
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isBlank()) {
                        titleError = true
                    } else {
                        val time = LocalTime.of(hour, minute)
                        onSave(title, notes, initialDate, time, selectedCategory, notifyEnabled)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                modifier = Modifier.testTag("dialog_save_button")
            ) {
                Text(if (isBengali) "সংরক্ষণ করুন" else "Save")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onDismiss,
                modifier = Modifier.testTag("dialog_cancel_button")
            ) {
                Text(if (isBengali) "বাতিল" else "Cancel")
            }
        }
    )
}
