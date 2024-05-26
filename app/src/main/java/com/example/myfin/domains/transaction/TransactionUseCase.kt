package com.example.myfin.domains.transaction

import com.example.myfin.models.Transaction.RequestDeleteTransaction
import com.example.myfin.models.Transaction.RequestSort
import com.example.myfin.models.Transaction.RequestSaveOrUpdateTransaction
import com.example.myfin.models.global.UIState
import kotlinx.coroutines.flow.Flow

interface TransactionUseCase {
    fun getAll(): Flow<UIState>
    fun save(data: RequestSaveOrUpdateTransaction): Flow<UIState>
    fun sort(data: RequestSort): Flow<UIState>
    fun delete(data: RequestDeleteTransaction): Flow<UIState>
}