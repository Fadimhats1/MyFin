package com.example.myfin.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myfin.utils.global.Converters

@Database(entities = [EntityTransaction::class], version = 5)
@TypeConverters(Converters::class)
abstract class TransactionDatabase: RoomDatabase() {
    abstract val dao: TransactionDao
}