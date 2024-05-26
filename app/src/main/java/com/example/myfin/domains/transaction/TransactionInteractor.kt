package com.example.myfin.domains.transaction

import com.example.myfin.domains.global.DatabaseCallUseCase
import com.example.myfin.domains.transaction.model.mapToListTransactionUIData
import com.example.myfin.domains.transaction.model.mapToTransactionDeleteUIData
import com.example.myfin.domains.transaction.model.mapToTransactionSaveOrUpdateUIData
import com.example.myfin.models.Transaction.RequestDeleteTransaction
import com.example.myfin.models.Transaction.RequestSaveOrUpdateTransaction
import com.example.myfin.models.Transaction.RequestSort
import com.example.myfin.models.Transaction.ResponseDeleteTransaction
import com.example.myfin.models.Transaction.ResponseListTransaction
import com.example.myfin.models.Transaction.ResponseSaveOrUpdateTransaction
import com.example.myfin.models.global.UIState
import com.example.myfin.repositories.transaction.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class TransactionInteractor(private val repo: TransactionRepository) : TransactionUseCase,
    KoinComponent {

    private val dbUseCase: DatabaseCallUseCase by inject { parametersOf(repo) }

    override fun getAll(): Flow<UIState> = dbUseCase.call(null).map {
        when (it) {
            is UIState.Success<*> -> {
                if (it.data is ResponseListTransaction)
                    UIState.Success((it.data as ResponseListTransaction).mapToListTransactionUIData())
                else
                    it
            }

            else -> it
        }
    }

    override fun save(data: RequestSaveOrUpdateTransaction): Flow<UIState> = dbUseCase.call(data).map {
        when (it) {
            is UIState.Success<*> -> {
                if (it.data is ResponseSaveOrUpdateTransaction)
                    UIState.Success((it.data as ResponseSaveOrUpdateTransaction).mapToTransactionSaveOrUpdateUIData())
                else
                    it
            }

            else -> it
        }
    }

    override fun sort(data: RequestSort): Flow<UIState> = dbUseCase.call(data).map {
        when (it) {
            is UIState.Success<*> -> {
                if (it.data is ResponseListTransaction)
                    UIState.Success((it.data as ResponseListTransaction).mapToListTransactionUIData())
                else
                    it
            }

            else -> it
        }
    }

    override fun delete(data: RequestDeleteTransaction): Flow<UIState> = dbUseCase.call(data).map {
        when (it) {
            is UIState.Success<*> -> {
                if (it.data is ResponseDeleteTransaction) {
                    UIState.Success((it.data as ResponseDeleteTransaction).mapToTransactionDeleteUIData())
                } else it
            }

            else -> it
        }
    }
}