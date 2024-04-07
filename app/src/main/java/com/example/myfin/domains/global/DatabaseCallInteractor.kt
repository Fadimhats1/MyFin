package com.example.myfin.domains.global

import com.example.myfin.models.BaseRequest
import com.example.myfin.models.ResponseState
import com.example.myfin.models.UiState
import com.example.myfin.repositories.global.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatabaseCallInteractor(private val repo: BaseRepository): DatabaseCallUseCase {
    override fun call(request: BaseRequest?): Flow<UiState> = flow {
        emit(UiState.Loading(null))
        repo.call(request).collect{
            emit(UiState.Idle(null))
            when(it){
                is ResponseState.Success<*> -> {
                    emit(UiState.Success(it.data))
                }
                is ResponseState.Error -> {
                    emit(UiState.Error(it.error))
                }
            }
            emit(UiState.Idle(null))
        }
    }
}