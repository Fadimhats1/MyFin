package com.example.myfin.modules

import com.example.myfin.domains.transaction.TransactionInteractor
import com.example.myfin.domains.transaction.TransactionUseCase
import com.example.myfin.repositories.transaction.TransactionRepository
import com.example.myfin.repositories.transaction.TransactionRepositoryImpl
import com.example.myfin.views.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val transactionModule = module {
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
    factory<TransactionUseCase> { TransactionInteractor(get()) }
    viewModel { HomeViewModel(get()) }
}