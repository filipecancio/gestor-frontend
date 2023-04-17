package dev.cancio.gestor.domain

import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Date


data class Transaction(
    val description: String,
    val bank: String,
    val value: Double,
    val timestamp: Date,
    val type: TransactionType
) {
    val moneyValue: String
    get() = "R$ ${value.toString()}"

    val date: Calendar
    @RequiresApi(Build.VERSION_CODES.O)
    get() = Calendar.getInstance().also {
        it.time = timestamp
    }

    val weekDay: String
    @RequiresApi(Build.VERSION_CODES.O)
    get() = WeekDay.from(date.get(Calendar.DAY_OF_WEEK))?.day ?: ""

    val weekOrder: Int
    @RequiresApi(Build.VERSION_CODES.O)
    get() = date.get(Calendar.WEEK_OF_MONTH)

    val dateFormat: String
    get() = SimpleDateFormat("dd/MM/yyyy").format(timestamp).toString()
}