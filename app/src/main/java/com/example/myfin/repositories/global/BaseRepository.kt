package com.example.myfin.repositories.global

import com.example.myfin.models.global.BaseRequest
import com.example.myfin.models.global.ResponseState
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    fun call(request: BaseRequest? = null): Flow<ResponseState>
}