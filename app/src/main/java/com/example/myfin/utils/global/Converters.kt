package com.example.myfin.utils.global

import androidx.room.TypeConverter
import com.example.myfin.utils.enums.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Converters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    @JvmStatic
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.format(formatter)
    }

    @TypeConverter
    @JvmStatic
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let {
            LocalDate.parse(it, formatter)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromTransactionType(value: TransactionType?): String? {
        return value?.let {
            when (it) {
                TransactionType.IN -> {
                    "IN"
                }

                else -> {
                    "OUT"
                }
            }
        }
    }

    @TypeConverter
    @JvmStatic
    fun tofromTransactionType(value: String?): TransactionType? {
        return value?.let {
            when (it) {
                "IN" -> {
                    TransactionType.IN
                }

                else -> {
                    TransactionType.OUT
                }
            }
        }
    }
}
