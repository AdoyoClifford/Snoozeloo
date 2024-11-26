package com.adoyo.snoozeloo.domain.respository

import com.adoyo.snoozeloo.domain.model.Alarm

interface AlarmRepository {
    fun getAlarms(): List<Alarm>
    fun getAlarm(id: Int): Alarm
    fun addAlarm(alarm: Alarm)
    fun removeAlarm(id: Int)
    fun updateAlarm(alarm: Alarm)
}