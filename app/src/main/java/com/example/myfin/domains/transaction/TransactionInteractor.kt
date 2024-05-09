package com.example.myfin.domains.transaction

import com.example.myfin.domains.global.DatabaseCallUseCase
import com.example.myfin.models.Sort
import com.example.myfin.models.Transaction
import com.example.myfin.models.UiState
import com.example.myfin.repositories.transaction.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class TransactionInteractor(private val repo: TransactionRepository) : TransactionUseCase, KoinComponent {

    val rep: DatabaseCallUseCase by inject { parametersOf(repo) }
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