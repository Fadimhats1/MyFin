package com.example.myfin.repositories.transaction

import com.example.myfin.models.Transaction
import com.example.myfin.models.Sort
import com.example.myfin.repositories.global.BaseRepository
import com.example.myfin.models.ResponseState
import kotlinx.coroutines.flow.Flow

interface TransactionRepository: BaseRepository {
    fun saveTransaction(transaction: Transaction): Flow<ResponseState>

    fun getTransactions(): Flow<ResponseState>

    fun sortTransaction(sort: Sort): Flow<ResponseState>
}