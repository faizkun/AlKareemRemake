package com.faizdev.alkareemremake.screen.adzanschedule.state

sealed class AdzanScheduleState {
    data class Success(val data: com.faizdev.alkareemremake.data.source.model.Time) : AdzanScheduleState()
    data class Error(val errorType: ErrorType) : AdzanScheduleState()
    object Idle: AdzanScheduleState()
}

enum class ErrorType {
    NO_GPS,
    PERMISSION_ERROR,
    OTHERS
}