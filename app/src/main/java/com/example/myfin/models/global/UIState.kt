package com.example.myfin.models.global

sealed class UIState {
    data class Loading(var message: String?) : UIState()
    data class Success<T>(var data: T) : UIState()
    data class Error(var error: Throwable) : UIState()
    data class Idle(var data: Any?) : UIState()
}