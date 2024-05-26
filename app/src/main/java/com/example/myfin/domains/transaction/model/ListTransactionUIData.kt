package com.example.myfin.domains.transaction.model

import android.os.Parcelable
import com.example.myfin.domains.global.model.UIBaseData
import com.example.myfin.models.Transaction.ResponseListTransaction
import com.example.myfin.utils.enums.TransactionType
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ListTransactionUIData(
    override val isSuccess: Boolean,
    val listTransaction: List<TransactionUIModel>?
) : UIBaseData()

@Parcelize
data class TransactionUIModel(
    val amount: Long?,
    val source: Int?,
    val date: LocalDate?,
    val reason: String?,
    val transactionType: TransactionType?,

    val id: Int,
) : Parcelable

fun ResponseListTransaction.mapToListTransactionUIData() = ListTransactionUIData(
    isSuccess,
    data?.map {
        TransactionUIModel(
            it.amount,
            it.source,
            it.date,
            it.reason,
            it.transactionType,

            it.id!!
        )
    }
)


