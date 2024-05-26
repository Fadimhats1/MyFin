package com.example.myfin.domains.transaction.model

import com.example.myfin.domains.global.model.UIBaseData
import com.example.myfin.models.Transaction.ResponseSaveOrUpdateTransaction
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionSaveOrUpdateUIData(
    override val isSuccess: Boolean,
    val message: String?
) : UIBaseData()

fun ResponseSaveOrUpdateTransaction.mapToTransactionSaveOrUpdateUIData() = TransactionSaveOrUpdateUIData(
    isSuccess,
    message
)