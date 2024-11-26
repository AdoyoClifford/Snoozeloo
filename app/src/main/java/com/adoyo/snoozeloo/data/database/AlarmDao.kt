package com.adoyo.snoozeloo.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.adoyo.snoozeloo.domain.model.Alarm


@Dao
interface AlarmDao {
    @Upsert
    fun upsertAlarm(alarm: Alarm)

    @Query("SELECT * FROM alarm")
    fun getAlarms(): List<Alarm>

    @Query("SELECT * FROM alarm WHERE id = :id")
    fun getAlarm(id: Int): Alarm

    @Query("DELETE FROM alarm WHERE id = :id")
    fun removeAlarm(id: Int)

}