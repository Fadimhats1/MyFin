package com.example.myfin.domains.global

import com.example.myfin.models.global.BaseRequest
import com.example.myfin.models.global.ResponseState
import com.example.myfin.models.global.UIState
import com.example.myfin.repositories.global.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatabaseCallInteractor(private val repo: BaseRepository) : DatabaseCallUseCase {
    override fun call(request: BaseRequest?): Flow<UIState> = flow {
        emit(UIState.Loading(null))
        repo.call(request).collect {
            emit(UIState.Idle(null))
            when (it) {
                is ResponseState.Success<*> -> {
                    emit(UIState.Success(it.data))
                }

                is ResponseState.Error -> {
                    emit(UIState.Error(it.error))
                }
            }
            emit(UIState.Idle(null))
        }
    }
}