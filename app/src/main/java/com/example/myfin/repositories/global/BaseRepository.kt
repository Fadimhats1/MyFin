package com.example.myfin.repositories.global

import com.example.myfin.models.BaseRequest
import com.example.myfin.models.ResponseState
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    fun call(request: BaseRequest? = null): Flow<ResponseState>
}