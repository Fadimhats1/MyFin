package com.example.myfin.models.global

sealed class ResponseState {
    data class Success<T>(var data: T) : ResponseState()
    data class Error(var error: Throwable) : ResponseState()
}