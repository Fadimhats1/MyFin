package com.example.myfin.models

sealed class ResponseState {
    data class Success<T>(var data: T) : ResponseState()
    data class Error(var error: Throwable) : ResponseState()
}