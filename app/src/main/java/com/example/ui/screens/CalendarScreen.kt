package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.components.CalendarHeader
import com.example.ui.components.CalendarMonthGrid
import com.example.ui.components.DayDetailSheet
import com.example.ui.dialogs.AddReminderDialog
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.viewmodel.CalendarViewModel
import java.time.LocalDate

@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel,
    modifier: Modifier = Modifier
) {
    val currentYearMonth by viewModel.currentYearMonth.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()
    val selectedBengaliDate by viewModel.selectedBengaliDate.collectAsState()
    val isBengali by viewModel.isBengaliLanguage.collectAsState()
    val allReminders by viewModel.allReminders.collectAsState()
    val dayReminders by viewModel.remindersForSelectedDate.collectAsState()

    var showAddReminderDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag("calendar_screen"),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddReminderDialog = true },
                containerColor = TerracottaPrimary,
                contentColor = Color.White,
                modifier = Modifier.testTag("calendar_fab_add_reminder")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Reminder"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            CalendarHeader(
                currentYearMonth = currentYearMonth,
                selectedBengaliDate = selectedBengaliDate,
                isBengali = isBengali,
                onPreviousMonth = { viewModel.changeMonth(-1) },
                onNextMonth = { viewModel.changeMonth(1) },
                onTodayClick = { viewModel.goToToday() },
                onToggleLanguage = { viewModel.toggleLanguage() }
            )

            Spacer(modifier = Modifier.height(8.dp))

            CalendarMonthGrid(
                currentYearMonth = currentYearMonth,
                selectedDate = selectedDate,
                reminders = allReminders,
                isBengali = isBengali,
                onDateSelected = { date -> viewModel.selectDate(date) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            DayDetailSheet(
                bengaliDate = selectedBengaliDate,
                reminders = dayReminders,
                isBengali = isBengali,
                onAddReminderClick = { showAddReminderDialog = true },
                onToggleReminderCompleted = { reminder -> viewModel.toggleReminderCompleted(reminder) },
                onDeleteReminder = { reminder -> viewModel.deleteReminder(reminder) }
            )

            Spacer(modifier = Modifier.height(80.dp)) // Padding for bottom bar & FAB
        }
    }

    if (showAddReminderDialog) {
        AddReminderDialog(
            initialDate = selectedDate,
            isBengali = isBengali,
            onDismiss = { showAddReminderDialog = false },
            onSave = { title, notes, date, time, category, notify ->
                viewModel.addReminder(title, notes, date, time, category, notify)
                showAddReminderDialog = false
            }
        )
    }
}
