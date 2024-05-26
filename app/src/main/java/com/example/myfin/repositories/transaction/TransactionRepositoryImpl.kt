package com.example.myfin.repositories.transaction

import android.database.sqlite.SQLiteConstraintException
import com.example.myfin.models.Transaction.RequestDeleteTransaction
import com.example.myfin.models.Transaction.RequestSaveOrUpdateTransaction
import com.example.myfin.models.Transaction.RequestSort
import com.example.myfin.models.Transaction.ResponseDeleteTransaction
import com.example.myfin.models.Transaction.ResponseSaveOrUpdateTransaction
import com.example.myfin.models.Transaction.mapToEntityTransaction
import com.example.myfin.models.database.EntityTransaction
import com.example.myfin.models.database.TransactionDao
import com.example.myfin.models.database.mapToResponseListTransaction
import com.example.myfin.models.global.BaseRequest
import com.example.myfin.models.global.ResponseState
import com.example.myfin.utils.enums.OrderBy
import com.example.myfin.utils.enums.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransactionRepositoryImpl(private val dao: TransactionDao) : TransactionRepository {
    override fun saveTransaction(data: RequestSaveOrUpdateTransaction) = flow {
        try {
            dao.upsertTransaction(data.mapToEntityTransaction())
            emit(
                ResponseState.Success(
                    ResponseSaveOrUpdateTransaction(
                        true,
                        if (data.id == null) "Penyimpanan data berhasil" else "Pembaruan data berhasil"
                    )
                )
            )
        } catch (e: SQLiteConstraintException) {
            emit(ResponseState.Error(e))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }

    override fun getTransactions() = flow {
        try {
            val data = dao.getTransactions()
            emit(ResponseState.Success(data.mapToResponseListTransaction()))
        } catch (e: SQLiteConstraintException) {
            emit(ResponseState.Error(e))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }

    override fun sortTransactions(data: RequestSort) = flow {
        try {
            val dataRes: List<EntityTransaction> = when (data.orderBy) {
                OrderBy.AMOUNT -> {
                    when (data.orderType) {
                        OrderType.ASC ->
                            dao.getTransactionsSortByAmountAsc()

                        OrderType.DESC ->
                            dao.getTransactionsSortByAmountDesc()
                    }
                }

                OrderBy.DATE -> {
                    when (data.orderType) {
                        OrderType.ASC ->
                            dao.getTransactionsSortByDateAsc()

                        OrderType.DESC ->
                            dao.getTransactionsSortByDateDesc()
                    }
                }
            }
            emit(ResponseState.Success(dataRes.mapToResponseListTransaction()))
        } catch (e: SQLiteConstraintException) {
            emit(ResponseState.Error(e))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }

    override fun deleteTransaction(data: RequestDeleteTransaction): Flow<ResponseState> = flow {
        try {
            dao.deleteTransaction(data.mapToEntityTransaction())
            emit(
                ResponseState.Success(
                    ResponseDeleteTransaction(
                        true,
                        "Penghapusan data berhasil"
                    )
                )
            )
        } catch (e: SQLiteConstraintException) {
            emit(ResponseState.Error(e))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }

    override fun call(request: BaseRequest?): Flow<ResponseState> {
        return when (request) {
            is RequestSaveOrUpdateTransaction -> saveTransaction(request)
            is RequestSort -> sortTransactions(request)
            is RequestDeleteTransaction -> deleteTransaction(request)
            else -> getTransactions()
        }
    }
}