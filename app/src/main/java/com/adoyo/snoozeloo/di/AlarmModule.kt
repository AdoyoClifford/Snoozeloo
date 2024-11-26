package com.adoyo.snoozeloo.di

import androidx.room.Room
import com.adoyo.snoozeloo.data.AlarmRepositoryImpl
import com.adoyo.snoozeloo.data.database.AlarmDatabase
import com.adoyo.snoozeloo.domain.respository.AlarmRepository
import com.adoyo.snoozeloo.presentation.AlarmViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    // database injection
    single {
        Room.databaseBuilder(
            androidApplication(),
            AlarmDatabase::class.java,
            "alarm_db"
        ).build()
    }

    // dao injection
    single {
        val database = get<AlarmDatabase>()
        database.alarmDao()
    }

    singleOf(::AlarmRepositoryImpl) bind AlarmRepository::class

    viewModelOf(:: AlarmViewModel)

}