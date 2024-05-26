package com.example.myfin.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfin.domains.transaction.TransactionUseCase
import com.example.myfin.models.Transaction.RequestDeleteTransaction
import com.example.myfin.models.Transaction.RequestSaveOrUpdateTransaction
import com.example.myfin.models.Transaction.RequestSort
import com.example.myfin.models.global.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeViewModel(private val transactionUseCase: TransactionUseCase) : ViewModel() {
    private val _dataListTransaction: MutableStateFlow<UIState> =
        MutableStateFlow(UIState.Idle(null))
    val dataListTransaction = _dataListTransaction.asStateFlow()
    private val _dataTransaction = MutableStateFlow<UIState>(UIState.Idle(null))
    val dataTransaction = _dataTransaction.asStateFlow()
    private val _dataIsSavedOrUpdated = MutableStateFlow<UIState>(UIState.Idle(null))
    val dataIsSavedOrUpdated = _dataIsSavedOrUpdated.asStateFlow()
    private val _dataIsDelete = MutableStateFlow<UIState>(UIState.Idle(null))
    val dataIsDelete = _dataIsDelete.asStateFlow()

    fun getAllTransaction() = viewModelScope.launch {
        transactionUseCase.getAll().flowOn(Dispatchers.IO).collect {
            launch(Dispatchers.Main) {
                _dataListTransaction.value = it
            }
        }
    }

    fun getTransactionSorted(data: RequestSort) = viewModelScope.launch {
        transactionUseCase.sort(data).flowOn(Dispatchers.IO).collect {
            launch(Dispatchers.Main) {
                _dataListTransaction.value = it
            }
        }
    }

    fun saveOrUpdateTransaction(data: RequestSaveOrUpdateTransaction) = viewModelScope.launch {
        transactionUseCase.save(data).flowOn(Dispatchers.IO).collect {
            launch(Dispatchers.Main) {
                _dataIsSavedOrUpdated.value = it
            }
        }
    }

    fun deleteTransaction(data: RequestDeleteTransaction) = viewModelScope.launch {
        transactionUseCase.delete(data).flowOn(Dispatchers.IO).collect {
            launch(Dispatchers.Main) {
                _dataIsDelete.value = it
            }
        }
    }
}