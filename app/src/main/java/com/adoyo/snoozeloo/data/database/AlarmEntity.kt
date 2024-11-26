package com.adoyo.snoozeloo.data.database

import androidx.room.Entity

@Entity(tableName = "alarm")
data class AlarmEntity(
    val id: Int,
    val time: String,
    //val repeat: String,
   // val label: String,2
    val enabled: Boolean,
    val timeLeft: String
)
