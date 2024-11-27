package com.adoyo.snoozeloo.domain.respository

import com.adoyo.snoozeloo.domain.model.Alarm
import kotlinx.coroutines.flow.Flow
import com.adoyo.snoozeloo.domain.Result
import com.adoyo.snoozeloo.domain.Error

interface AlarmRepository {
    fun getAlarms(): Flow<Result<List<Alarm>,Error>>
    suspend fun getAlarm(id: Int): Result<Alarm, Error>
    suspend fun addAlarm(alarm: Alarm): Result<Unit, Error>
    suspend fun removeAlarm(id: Int): Result<Unit, Error>
    suspend fun updateAlarm(alarm: Alarm): Result<Unit, Error>
}