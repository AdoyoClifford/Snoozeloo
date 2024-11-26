package com.adoyo.snoozeloo.data

import com.adoyo.snoozeloo.data.database.AlarmDao
import com.adoyo.snoozeloo.domain.model.Alarm
import com.adoyo.snoozeloo.domain.respository.AlarmRepository

class AlarmRepositoryImpl(
    private val alarmDao: AlarmDao
) : AlarmRepository {

    override fun getAlarms(): List<Alarm> {
        return  alarmDao.getAlarms()
    }

    override fun getAlarm(id: Int): Alarm {
        return alarmDao.getAlarm(id)
    }

    override fun addAlarm(alarm: Alarm) {
        alarmDao.upsertAlarm(alarm)
    }

    override fun removeAlarm(id: Int) {
        alarmDao.removeAlarm(id)
    }

    override fun updateAlarm(alarm: Alarm) {
        alarmDao.upsertAlarm(alarm)
    }
}