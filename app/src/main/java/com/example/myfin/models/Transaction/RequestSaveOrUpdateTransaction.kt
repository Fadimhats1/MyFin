package com.example.myfin.models.Transaction

import com.example.myfin.models.database.EntityTransaction
import com.example.myfin.models.global.BaseRequest
import com.example.myfin.utils.enums.TransactionType
import java.time.LocalDate

data class RequestSaveOrUpdateTransaction (
    val amount: Long,
    val source: Int,
    val date: LocalDate,
    val reason: String,
    val transactionType: TransactionType,

    val id: Int? = null,
): BaseRequest()

fun RequestSaveOrUpdateTransaction.mapToEntityTransaction() = EntityTransaction(
   amount, source, date, reason, transactionType, id
)