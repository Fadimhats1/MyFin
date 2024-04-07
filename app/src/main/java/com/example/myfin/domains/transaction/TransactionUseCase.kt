package com.example.myfin.domains.transaction

import com.example.myfin.models.Sort
import com.example.myfin.models.Transaction
import com.example.myfin.models.UiState
import kotlinx.coroutines.flow.Flow

interface TransactionUseCase {
    fun getAll(): Flow<UiState>
    fun save(transaction: Transaction): Flow<UiState>
    fun sort(sort: Sort): Flow<UiState>
}