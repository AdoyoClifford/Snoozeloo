package com.adoyo.snoozeloo.domain.model

data class Alarm(
    val id: Int,
    val time: String,
  //  val repeat: String,
  //  val label: String,
    val enabled: Boolean,
    val timeLeft: String
)