package com.example.myfin.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfin.models.Transaction

@Database(entities = [Transaction::class], version = 1)
abstract class TransactionDatabase: RoomDatabase() {
    abstract val dao: TransactionDao
}