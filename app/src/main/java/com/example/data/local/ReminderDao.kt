package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminders ORDER BY dateEpochDay ASC, timeHour ASC, timeMinute ASC")
    fun getAllReminders(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE dateEpochDay = :epochDay ORDER BY timeHour ASC, timeMinute ASC")
    fun getRemindersForDate(epochDay: Long): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE dateEpochDay >= :currentEpochDay AND isCompleted = 0 ORDER BY dateEpochDay ASC, timeHour ASC, timeMinute ASC")
    fun getUpcomingReminders(currentEpochDay: Long): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE id = :id LIMIT 1")
    suspend fun getReminderById(id: Long): ReminderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity): Long

    @Update
    suspend fun updateReminder(reminder: ReminderEntity)

    @Delete
    suspend fun deleteReminder(reminder: ReminderEntity)

    @Query("DELETE FROM reminders WHERE id = :id")
    suspend fun deleteReminderById(id: Long)
}
