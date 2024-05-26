package com.example.myfin.models.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TransactionDao {
    @Upsert
    suspend fun upsertTransaction(data: EntityTransaction)

    @Delete
    suspend fun deleteTransaction(id: EntityTransaction)

    @Query("SELECT * FROM EntityTransaction")
    fun getTransactions(): List<EntityTransaction>

    @Query("SELECT * FROM EntityTransaction ORDER BY amount ASC")
    fun getTransactionsSortByAmountAsc(): List<EntityTransaction>

    @Query("SELECT * FROM EntityTransaction ORDER BY amount DESC")
    fun getTransactionsSortByAmountDesc(): List<EntityTransaction>

    @Query("SELECT * FROM EntityTransaction ORDER BY date ASC")
    fun getTransactionsSortByDateAsc(): List<EntityTransaction>

    @Query("SELECT * FROM EntityTransaction ORDER BY date DESC")
    fun getTransactionsSortByDateDesc(): List<EntityTransaction>
}