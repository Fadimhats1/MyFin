package com.example.myfin.utils.global

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object GlobalFunction {
    fun formatCurrency(amount: String, locale: String? = null): String {
        val tempAmount = amount.replace(".", "").replace(",", ".").toLong()
        val tempLocale = Locale("in", locale?.uppercase() ?: "ID")

        val symbols = DecimalFormatSymbols(tempLocale).apply {
            currencySymbol = "Rp. "
            groupingSeparator = '.'
            monetaryDecimalSeparator = ','
        }

        return (NumberFormat.getCurrencyInstance(tempLocale) as DecimalFormat).apply {
            decimalFormatSymbols = symbols
        }.format(tempAmount).substringBefore(",", "")
    }

    fun formatLocaleDateToString(localDate: LocalDate, locale: String? = null): String {
        val formatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy", Locale(locale?.lowercase() ?: "id"))
        return localDate.format(formatter)
    }

    fun formatStringToLocalDate(dateString: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern(
            "MMMM d, yyyy",
            Locale.ENGLISH
        ) // Define the format of your date string
        return LocalDate.parse(dateString, formatter)
    }
}