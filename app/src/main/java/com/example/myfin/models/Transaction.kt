package com.example.myfin.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val amount: Int = 0,
    val source: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val reason: String? = "",
    val transactionType: Int? = null,
): BaseRequest()