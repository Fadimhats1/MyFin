package com.example.myfin.domains.global

import com.example.myfin.models.BaseRequest
import com.example.myfin.models.UiState
import kotlinx.coroutines.flow.Flow

interface DatabaseCallUseCase {
    fun call(request: BaseRequest? = null) : Flow<UiState>
}