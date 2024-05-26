package com.example.myfin.modules

import androidx.room.Room
import com.example.myfin.models.database.TransactionDao
import com.example.myfin.models.database.TransactionDatabase
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        Room.databaseBuilder(get(), TransactionDatabase::class.java, "Transaction_DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<TransactionDatabase>().dao }
}