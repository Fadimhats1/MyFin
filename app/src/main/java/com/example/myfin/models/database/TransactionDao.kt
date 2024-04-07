package com.example.myfin.models.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myfin.models.Transaction
import com.example.myfin.utils.enum.OrderType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Upsert
    suspend fun upsertTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("SELECT * FROM `Transaction`")
    fun getTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` ORDER BY amount (:order)")
    fun getTransactionsSortByAmount(order: OrderType): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` ORDER BY date (:order)")
    fun getTransactionsSortByDate(order: OrderType): Flow<List<Transaction>>
}