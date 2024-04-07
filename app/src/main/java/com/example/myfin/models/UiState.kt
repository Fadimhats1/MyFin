package com.example.myfin.models

sealed class UiState {
    data class Loading(var message: String?) : UiState()
    data class Success<T>(var data: T) : UiState()
    data class Error(var error: Throwable) : UiState()
    data class Idle(var data: Any?) : UiState()
}