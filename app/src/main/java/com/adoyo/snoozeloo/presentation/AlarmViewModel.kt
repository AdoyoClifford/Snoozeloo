package com.adoyo.snoozeloo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adoyo.snoozeloo.domain.model.Alarm
import com.adoyo.snoozeloo.domain.onSuccess
import com.adoyo.snoozeloo.domain.respository.AlarmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AlarmViewModel(
    private val alarmRepository: AlarmRepository
): ViewModel() {

    private val _state = MutableStateFlow(AlarmState())
    val state = _state.asStateFlow()

    init {
        getAlarms()
    }

    fun getAlarms() {
        viewModelScope.launch{
            alarmRepository.getAlarms().collect { result ->
                result.onSuccess {alarms ->
                    _state.value = state.value.copy(alarms = alarms)
                }
            }
        }
    }

    fun addAlarm(alarm: Alarm) {
        viewModelScope.launch {
            alarmRepository.addAlarm(alarm).onSuccess{
                getAlarms()
            }
        }
    }

    fun removeAlarm(id: Int) {
        viewModelScope.launch {
            alarmRepository.removeAlarm(id).onSuccess {
                getAlarms()
            }
        }
    }

    fun upDateAlarm(alarm: Alarm) {
        viewModelScope.launch {
            alarmRepository.updateAlarm(alarm).onSuccess {
                getAlarms()
            }
        }
    }

    fun selectAlarm(alarm: Alarm) {
        viewModelScope.launch{
            alarmRepository.getAlarm(alarm.id).onSuccess { alarm ->
                _state.value = state.value.copy(selectedAlarm = alarm)
            }
        }
    }

    fun clearSelectedAlarm() {
        _state.value = state.value.copy(selectedAlarm = null)
    }




}