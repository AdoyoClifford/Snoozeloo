package com.adoyo.snoozeloo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlarmEntity::class], version = 1)
abstract class AlarmDatabase: RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}