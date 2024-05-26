package com.example.myfin.models.Transaction

import com.example.myfin.utils.enums.TransactionType
import java.time.LocalDate

data class ResponseTransactionModel (
    val amount: Long,
    val source: Int?,
    val date: LocalDate,
    val reason: String?,
    val transactionType: TransactionType?,

    val id: Int?,
)