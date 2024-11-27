package com.adoyo.snoozeloo.presentation

import com.adoyo.snoozeloo.domain.model.Alarm

data class AlarmState(
    val alarms: List<Alarm> = emptyList(),
    val selectedAlarm: Alarm? = null

)