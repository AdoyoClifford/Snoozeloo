package com.adoyo.snoozeloo.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.adoyo.snoozeloo.domain.model.Alarm
import kotlinx.coroutines.flow.Flow


@Dao
interface AlarmDao {
    @Upsert
    suspend fun upsertAlarm(alarm: Alarm)

    @Query("SELECT * FROM alarm")
    fun getAlarms(): Flow<List<Alarm>>

    @Query("SELECT * FROM alarm WHERE id = :id")
    suspend fun getAlarm(id: Int): Alarm

    @Query("DELETE FROM alarm WHERE id = :id")
    suspend fun removeAlarm(id: Int)

}