package com.example

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.NightlightRound
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.NightlightRound
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Stars
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.receiver.NotificationHelper
import com.example.ui.screens.AuspiciousDatesScreen
import com.example.ui.screens.CalendarScreen
import com.example.ui.screens.MoonPhasesScreen
import com.example.ui.screens.RemindersScreen
import com.example.ui.theme.FestiveGold
import com.example.ui.theme.FestiveGoldContainer
import com.example.ui.theme.FestiveGoldLight
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.TerracottaPrimaryContainer
import com.example.ui.viewmodel.CalendarTab
import com.example.ui.viewmodel.CalendarViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NotificationHelper.createNotificationChannels(this)

        setContent {
            MyApplicationTheme {
                MainBanglaCalendarApp()
            }
        }
    }
}

@Composable
fun MainBanglaCalendarApp(
    viewModel: CalendarViewModel = viewModel()
) {
    val activeTab by viewModel.activeTab.collectAsState()
    val isBengali by viewModel.isBengaliLanguage.collectAsState()

    // Request notification permissions gracefully on modern Android
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { _ -> }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    // Handle system back gesture to navigate back to Calendar home
    BackHandler(enabled = activeTab != CalendarTab.CALENDAR) {
        viewModel.setActiveTab(CalendarTab.CALENDAR)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .testTag("bottom_navigation_bar"),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                // Tab 1: Calendar / Panjika
                NavigationBarItem(
                    selected = activeTab == CalendarTab.CALENDAR,
                    onClick = { viewModel.setActiveTab(CalendarTab.CALENDAR) },
                    icon = {
                        Icon(
                            imageVector = if (activeTab == CalendarTab.CALENDAR) Icons.Filled.CalendarMonth else Icons.Outlined.CalendarMonth,
                            contentDescription = "Calendar"
                        )
                    },
                    label = {
                        Text(
                            text = if (isBengali) CalendarTab.CALENDAR.bengaliTitle else CalendarTab.CALENDAR.englishTitle,
                            fontWeight = if (activeTab == CalendarTab.CALENDAR) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = TerracottaPrimary,
                        selectedTextColor = TerracottaPrimary,
                        indicatorColor = TerracottaPrimaryContainer
                    ),
                    modifier = Modifier.testTag("nav_item_calendar")
                )

                // Tab 2: Moon Phases (Purnima & Amavasya)
                NavigationBarItem(
                    selected = activeTab == CalendarTab.PURNIMA_AMAVASYA,
                    onClick = { viewModel.setActiveTab(CalendarTab.PURNIMA_AMAVASYA) },
                    icon = {
                        Icon(
                            imageVector = if (activeTab == CalendarTab.PURNIMA_AMAVASYA) Icons.Filled.NightlightRound else Icons.Outlined.NightlightRound,
                            contentDescription = "Purnima & Amavasya"
                        )
                    },
                    label = {
                        Text(
                            text = if (isBengali) CalendarTab.PURNIMA_AMAVASYA.bengaliTitle else CalendarTab.PURNIMA_AMAVASYA.englishTitle,
                            fontWeight = if (activeTab == CalendarTab.PURNIMA_AMAVASYA) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = FestiveGold,
                        selectedTextColor = FestiveGold,
                        indicatorColor = FestiveGoldContainer
                    ),
                    modifier = Modifier.testTag("nav_item_moon_phases")
                )

                // Tab 3: Auspicious Dates (Shubho Muhurat)
                NavigationBarItem(
                    selected = activeTab == CalendarTab.AUSPICIOUS,
                    onClick = { viewModel.setActiveTab(CalendarTab.AUSPICIOUS) },
                    icon = {
                        Icon(
                            imageVector = if (activeTab == CalendarTab.AUSPICIOUS) Icons.Filled.Stars else Icons.Outlined.Stars,
                            contentDescription = "Auspicious Dates"
                        )
                    },
                    label = {
                        Text(
                            text = if (isBengali) CalendarTab.AUSPICIOUS.bengaliTitle else CalendarTab.AUSPICIOUS.englishTitle,
                            fontWeight = if (activeTab == CalendarTab.AUSPICIOUS) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF047857),
                        selectedTextColor = Color(0xFF047857),
                        indicatorColor = Color(0xFFD1FAE5)
                    ),
                    modifier = Modifier.testTag("nav_item_auspicious")
                )

                // Tab 4: Reminders & Notifications
                NavigationBarItem(
                    selected = activeTab == CalendarTab.REMINDERS,
                    onClick = { viewModel.setActiveTab(CalendarTab.REMINDERS) },
                    icon = {
                        Icon(
                            imageVector = if (activeTab == CalendarTab.REMINDERS) Icons.Filled.Notifications else Icons.Outlined.Notifications,
                            contentDescription = "Reminders"
                        )
                    },
                    label = {
                        Text(
                            text = if (isBengali) CalendarTab.REMINDERS.bengaliTitle else CalendarTab.REMINDERS.englishTitle,
                            fontWeight = if (activeTab == CalendarTab.REMINDERS) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = TerracottaPrimary,
                        selectedTextColor = TerracottaPrimary,
                        indicatorColor = TerracottaPrimaryContainer
                    ),
                    modifier = Modifier.testTag("nav_item_reminders")
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Crossfade(targetState = activeTab, label = "tab_transition") { tab ->
                when (tab) {
                    CalendarTab.CALENDAR -> CalendarScreen(viewModel = viewModel)
                    CalendarTab.PURNIMA_AMAVASYA -> MoonPhasesScreen(viewModel = viewModel)
                    CalendarTab.AUSPICIOUS -> AuspiciousDatesScreen(viewModel = viewModel)
                    CalendarTab.REMINDERS -> RemindersScreen(viewModel = viewModel)
                }
            }
        }
    }
}
