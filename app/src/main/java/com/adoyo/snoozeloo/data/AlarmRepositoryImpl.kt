package com.adoyo.snoozeloo.data

import com.adoyo.snoozeloo.data.database.AlarmDao
import com.adoyo.snoozeloo.domain.DataError
import com.adoyo.snoozeloo.domain.Error
import com.adoyo.snoozeloo.domain.Result
import com.adoyo.snoozeloo.domain.Result.Success
import com.adoyo.snoozeloo.domain.model.Alarm
import com.adoyo.snoozeloo.domain.respository.AlarmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AlarmRepositoryImpl(
    private val alarmDao: AlarmDao
) : AlarmRepository {
    override fun getAlarms(): Flow<Result<List<Alarm>, Error>> = flow {
        try {
            alarmDao.getAlarms()
                .collect { alarms ->
                    emit(Success(alarms))
                }
        } catch (e: Exception) {
            emit(Result.Error(DataError.Local.DATABASE_ERROR))
        }
    }

    override suspend fun getAlarm(id: Int): Result<Alarm, Error> {
        return try {
            val alarm = alarmDao.getAlarm(id)
            if (true) {
                return Success(alarm)
            } else {
                Result.Error(DataError.Local.FILE_NOT_FOUND)
            }

        } catch (e: Exception) {
            Result.Error(DataError.Local.DATABASE_ERROR)
        }
    }

    override suspend fun addAlarm(alarm: Alarm): Result<Unit, Error> {
        return try {
            alarmDao.upsertAlarm(alarm)
            Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DATABASE_ERROR)
        }
    }

    override suspend fun removeAlarm(id: Int): Result<Unit, Error> {
        return try {
            alarmDao.removeAlarm(id)
            Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DATABASE_ERROR)
        }
    }

    override suspend fun updateAlarm(alarm: Alarm): Result<Unit, Error> {
        return try {
            alarmDao.upsertAlarm(alarm)
            Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DATABASE_ERROR)
        }
    }
}