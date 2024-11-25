package com.adoyo.snoozeloo

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data object Alarms: Screen()
    @Serializable
    data class AlarmDetails(val alarmId: Int): Screen()
}