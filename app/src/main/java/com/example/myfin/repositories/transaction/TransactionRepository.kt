package com.example.myfin.repositories.transaction

import com.example.myfin.models.Transaction.RequestDeleteTransaction
import com.example.myfin.models.Transaction.RequestSort
import com.example.myfin.models.Transaction.RequestSaveOrUpdateTransaction
import com.example.myfin.repositories.global.BaseRepository
import com.example.myfin.models.global.ResponseState
import kotlinx.coroutines.flow.Flow

interface TransactionRepository: BaseRepository {
    fun saveTransaction(data: RequestSaveOrUpdateTransaction): Flow<ResponseState>

    fun getTransactions(): Flow<ResponseState>

    fun sortTransactions(data: RequestSort): Flow<ResponseState>

    fun deleteTransaction(data: RequestDeleteTransaction): Flow<ResponseState>
}