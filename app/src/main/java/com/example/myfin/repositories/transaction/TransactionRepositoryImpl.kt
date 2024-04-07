package com.example.myfin.repositories.transaction

import android.database.sqlite.SQLiteConstraintException
import com.example.myfin.models.Transaction
import com.example.myfin.models.database.TransactionDao
import com.example.myfin.models.Sort
import com.example.myfin.models.BaseRequest
import com.example.myfin.models.ResponseState
import com.example.myfin.utils.enum.OrderBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class TransactionRepositoryImpl(private val dao: TransactionDao): TransactionRepository {
    override fun saveTransaction(transaction: Transaction) = flow {
        try {
            dao.upsertTransaction(transaction)
            emit(ResponseState.Success(null))
        }catch (e: SQLiteConstraintException){
            emit(ResponseState.Error(e))
        }catch (e: Exception){
            emit(ResponseState.Error(e))
        }
    }

    override fun getTransactions() = flow {
        try {
            dao.getTransactions()
            emit(ResponseState.Success(null))
        } catch (e: SQLiteConstraintException){
            emit(ResponseState.Error(e))
        } catch (e: Exception){
            emit(ResponseState.Error(e))
        }
    }

    override fun sortTransaction(sort: Sort) = flow {
        try {
            val data: Flow<List<Transaction>> = when(sort.orderBy){
                OrderBy.AMOUNT -> {
                    dao.getTransactionsSortByAmount(sort.orderType)
                }

                OrderBy.DATE -> {
                    dao.getTransactionsSortByDate(sort.orderType)
                }
            }
            emit(ResponseState.Success(data))
        } catch (e: SQLiteConstraintException){
            emit(ResponseState.Error(e))
        } catch (e: Exception){
            emit(ResponseState.Error(e))
        }
    }

    override fun call(request: BaseRequest?): Flow<ResponseState> {
        return when (request) {
            is Transaction -> saveTransaction(request)
            is Sort -> sortTransaction(request)
            else -> getTransactions()
        }
    }
}