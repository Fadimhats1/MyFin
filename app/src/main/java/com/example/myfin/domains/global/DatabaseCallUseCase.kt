package com.example.myfin.domains.global

import com.example.myfin.models.global.BaseRequest
import com.example.myfin.models.global.UIState
import kotlinx.coroutines.flow.Flow

interface DatabaseCallUseCase {
    fun call(request: BaseRequest? = null) : Flow<UIState>
}