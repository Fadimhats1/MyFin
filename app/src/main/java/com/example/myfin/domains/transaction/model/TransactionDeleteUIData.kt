package com.example.myfin.domains.transaction.model

import com.example.myfin.domains.global.model.UIBaseData
import com.example.myfin.models.Transaction.ResponseDeleteTransaction
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionDeleteUIData (
    override val isSuccess: Boolean,
    val message: String?
) : UIBaseData()

fun ResponseDeleteTransaction.mapToTransactionDeleteUIData() = TransactionDeleteUIData(
    isSuccess,
    message
)