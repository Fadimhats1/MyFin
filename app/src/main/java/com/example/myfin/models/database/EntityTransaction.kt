package com.example.myfin.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfin.models.Transaction.ResponseListTransaction
import com.example.myfin.models.Transaction.ResponseTransactionModel
import com.example.myfin.utils.enums.TransactionType
import java.time.LocalDate

@Entity
data class EntityTransaction(
    val amount: Long = 0,
    val source: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val reason: String? = "",
    val transactionType: TransactionType? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
)

fun List<EntityTransaction>.mapToResponseListTransaction(isSuccessRes: Boolean = true) =
    ResponseListTransaction(
        isSuccessRes,
        this.map {
            ResponseTransactionModel(
                it.amount,
                it.source,
                it.date,
                it.reason,
                it.transactionType,

                it.id,
            )
        }
    )
