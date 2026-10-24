package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.bengali.BengaliCalendarCalculator
import com.example.data.bengali.BengaliPanjikaData
import com.example.data.local.AppDatabase
import com.example.data.model.AuspiciousCategory
import com.example.data.model.AuspiciousDate
import com.example.data.model.BengaliDate
import com.example.data.model.MoonPhaseEvent
import com.example.data.model.ReminderCategory
import com.example.data.model.ReminderEntity
import com.example.receiver.NotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.ZoneId

enum class CalendarTab(val bengaliTitle: String, val englishTitle: String) {
    CALENDAR("পঞ্জিকা", "Calendar"),
    PURNIMA_AMAVASYA("পূর্ণিমা-অমাবস্যা", "Moon Phases"),
    AUSPICIOUS("শুভ দিন", "Auspicious"),
    REMINDERS("অনুস্মারক", "Reminders")
}

class CalendarViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences("bangla_calendar_prefs", Context.MODE_PRIVATE)
    private val reminderDao = AppDatabase.getDatabase(application).reminderDao()

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _currentYearMonth = MutableStateFlow(YearMonth.now())
    val currentYearMonth: StateFlow<YearMonth> = _currentYearMonth.asStateFlow()

    private val _isBengaliLanguage = MutableStateFlow(prefs.getBoolean("pref_bengali_lang", true))
    val isBengaliLanguage: StateFlow<Boolean> = _isBengaliLanguage.asStateFlow()

    private val _activeTab = MutableStateFlow(CalendarTab.CALENDAR)
    val activeTab: StateFlow<CalendarTab> = _activeTab.asStateFlow()

    private val _purnimaNotifyEnabled = MutableStateFlow(prefs.getBoolean("pref_notify_purnima", true))
    val purnimaNotifyEnabled: StateFlow<Boolean> = _purnimaNotifyEnabled.asStateFlow()

    private val _amavasyaNotifyEnabled = MutableStateFlow(prefs.getBoolean("pref_notify_amavasya", true))
    val amavasyaNotifyEnabled: StateFlow<Boolean> = _amavasyaNotifyEnabled.asStateFlow()

    private val _notifyDaysBefore = MutableStateFlow(prefs.getInt("pref_notify_days_before", 0))
    val notifyDaysBefore: StateFlow<Int> = _notifyDaysBefore.asStateFlow()

    private val _selectedAuspiciousCategory = MutableStateFlow<AuspiciousCategory?>(null)
    val selectedAuspiciousCategory: StateFlow<AuspiciousCategory?> = _selectedAuspiciousCategory.asStateFlow()

    val allReminders: StateFlow<List<ReminderEntity>> = reminderDao.getAllReminders()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val remindersForSelectedDate: StateFlow<List<ReminderEntity>> = combine(
        allReminders,
        _selectedDate
    ) { reminders, date ->
        reminders.filter { it.dateEpochDay == date.toEpochDay() }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val selectedBengaliDate: StateFlow<BengaliDate> = _selectedDate
        .combine(_isBengaliLanguage) { date, _ ->
            BengaliCalendarCalculator.getBengaliDate(date)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            BengaliCalendarCalculator.getBengaliDate(LocalDate.now())
        )

    init {
        NotificationHelper.createNotificationChannels(application)
    }

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
        if (YearMonth.from(date) != _currentYearMonth.value) {
            _currentYearMonth.value = YearMonth.from(date)
        }
    }

    fun changeMonth(delta: Int) {
        val newYearMonth = _currentYearMonth.value.plusMonths(delta.toLong())
        _currentYearMonth.value = newYearMonth
        // Adjust selectedDate to stay in month
        val currentDay = _selectedDate.value.dayOfMonth
        val maxDays = newYearMonth.lengthOfMonth()
        _selectedDate.value = newYearMonth.atDay(currentDay.coerceAtMost(maxDays))
    }

    fun setYearMonth(year: Int, month: Int) {
        val newYearMonth = YearMonth.of(year, month)
        _currentYearMonth.value = newYearMonth
        val maxDays = newYearMonth.lengthOfMonth()
        val currentDay = _selectedDate.value.dayOfMonth
        _selectedDate.value = newYearMonth.atDay(currentDay.coerceAtMost(maxDays))
    }

    fun goToToday() {
        val today = LocalDate.now()
        _selectedDate.value = today
        _currentYearMonth.value = YearMonth.from(today)
    }

    fun toggleLanguage() {
        val newLang = !_isBengaliLanguage.value
        _isBengaliLanguage.value = newLang
        prefs.edit().putBoolean("pref_bengali_lang", newLang).apply()
    }

    fun setActiveTab(tab: CalendarTab) {
        _activeTab.value = tab
    }

    fun setSelectedAuspiciousCategory(category: AuspiciousCategory?) {
        _selectedAuspiciousCategory.value = category
    }

    fun togglePurnimaNotify(enabled: Boolean) {
        _purnimaNotifyEnabled.value = enabled
        prefs.edit().putBoolean("pref_notify_purnima", enabled).apply()
    }

    fun toggleAmavasyaNotify(enabled: Boolean) {
        _amavasyaNotifyEnabled.value = enabled
        prefs.edit().putBoolean("pref_notify_amavasya", enabled).apply()
    }

    fun setNotifyTimingDaysBefore(days: Int) {
        _notifyDaysBefore.value = days
        prefs.edit().putInt("pref_notify_days_before", days).apply()
    }

    fun jumpToDate(date: LocalDate) {
        selectDate(date)
        _activeTab.value = CalendarTab.CALENDAR
    }

    fun addReminder(
        title: String,
        notes: String,
        date: LocalDate,
        time: LocalTime,
        category: ReminderCategory,
        notify: Boolean
    ) {
        viewModelScope.launch {
            val entity = ReminderEntity(
                title = title.trim(),
                notes = notes.trim(),
                dateEpochDay = date.toEpochDay(),
                timeHour = time.hour,
                timeMinute = time.minute,
                category = category.name,
                notifyEnabled = notify
            )
            val newId = reminderDao.insertReminder(entity)

            if (notify) {
                val triggerTime = date.atTime(time)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()

                NotificationHelper.scheduleEventReminder(
                    context = getApplication(),
                    reminderId = newId,
                    title = title,
                    notes = notes,
                    triggerEpochMillis = triggerTime
                )
            }
        }
    }

    fun updateReminder(reminder: ReminderEntity) {
        viewModelScope.launch {
            reminderDao.updateReminder(reminder)
            if (reminder.notifyEnabled && !reminder.isCompleted) {
                val triggerTime = reminder.localDate.atTime(reminder.localTime)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()

                NotificationHelper.scheduleEventReminder(
                    context = getApplication(),
                    reminderId = reminder.id,
                    title = reminder.title,
                    notes = reminder.notes,
                    triggerEpochMillis = triggerTime
                )
            } else {
                NotificationHelper.cancelEventReminder(getApplication(), reminder.id)
            }
        }
    }

    fun toggleReminderCompleted(reminder: ReminderEntity) {
        viewModelScope.launch {
            val updated = reminder.copy(isCompleted = !reminder.isCompleted)
            reminderDao.updateReminder(updated)
            if (updated.isCompleted) {
                NotificationHelper.cancelEventReminder(getApplication(), reminder.id)
            }
        }
    }

    fun deleteReminder(reminder: ReminderEntity) {
        viewModelScope.launch {
            NotificationHelper.cancelEventReminder(getApplication(), reminder.id)
            reminderDao.deleteReminder(reminder)
        }
    }

    fun sendTestNotification(isPurnima: Boolean) {
        NotificationHelper.triggerTestNotification(
            context = getApplication(),
            isPurnima = isPurnima,
            isBengali = _isBengaliLanguage.value
        )
    }
}
