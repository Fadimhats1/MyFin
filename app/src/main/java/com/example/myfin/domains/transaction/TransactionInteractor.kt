package com.example.myfin.domains.transaction

import com.example.myfin.models.Sort
import com.example.myfin.models.Transaction
import com.example.myfin.models.UiState
import com.example.myfin.repositories.transaction.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionInteractor(private val repo: TransactionRepository):TransactionUseCase {
    override fun getAll(): Flow<UiState> {
        TODO("Not yet implemented")
    }

    override fun save(transaction: Transaction): Flow<UiState> {
        TODO("Not yet implemented")
    }

    override fun sort(sort: Sort): Flow<UiState> {
        TODO("Not yet implemented")
    }

}