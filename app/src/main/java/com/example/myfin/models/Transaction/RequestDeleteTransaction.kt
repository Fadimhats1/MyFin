package com.example.myfin.models.Transaction

import com.example.myfin.models.database.EntityTransaction
import com.example.myfin.models.global.BaseRequest
import com.example.myfin.utils.enums.TransactionType
import java.time.LocalDate

data class RequestDeleteTransaction(
    val amount: Long,
    val source: Int,
    val date: LocalDate,
    val reason: String,
    val transactionType: TransactionType,

    val id: Int?,
) : BaseRequest()

fun RequestDeleteTransaction.mapToEntityTransaction() = EntityTransaction(
    amount, source, date, reason, transactionType, id
)