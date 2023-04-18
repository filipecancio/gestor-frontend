package dev.cancio.gestor.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
import java.io.IOException

class TransactionRepository(
    private val context: Context
) {
    private fun getTransactionsFromJson(): List<Transaction> {
        val jsonFileString = getJsonDataFromAsset("data.json")

        val gson = Gson()
        val listTransactionType = object : TypeToken<List<Transaction>>() {}.type
        val values: List<Transaction> = gson.fromJson(jsonFileString, listTransactionType)
        return values
    }

    private fun getJsonDataFromAsset(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getTransactions() = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .groupBy { it.dateFormat }

    fun getTransactions(type: TransactionType) = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .filter { it.type == type }
        .groupBy { it.dateFormat }

    fun getTransactions(month: Int, type: TransactionType) = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .filter { it.timestamp.month == month && it.type == type }
        .groupBy { it.dateFormat }

    fun getTransactions(month: Int, year: Int, type: TransactionType) = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .filter { it.timestamp.month == month && it.timestamp.year == year && it.type == type }
        .groupBy { it.dateFormat }

    fun getTransactions(description: String) = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .filter { it.description == description }
        .groupBy { it.dateFormat }

    fun getTransactions(month: Int) = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .filter { it.timestamp.month == month }
        .groupBy { it.dateFormat }

    fun getTransactions(month: Int, year: Int) = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .filter { it.timestamp.month == month && it.timestamp.year == year }
        .groupBy { it.dateFormat }

    private fun getTransactionsSum(type: TransactionType) = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .filter { it.type == type }
        .sumOf { it.value }

    private fun getTransactionsSum(month: Int, type: TransactionType) = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .filter { it.timestamp.month == month && it.type == type }
        .sumOf { it.value }

    private fun getTransactionsSum(month: Int, year: Int, type: TransactionType) =
        getTransactionsFromJson()
            .sortedByDescending { it.timestamp }
            .filter { it.timestamp.month == month && it.timestamp.year == year && it.type == type }
            .sumOf { it.value }

    fun getTotalTransactionsValues() = mapOf(
        Pair("credit", getTransactionsSum(TransactionType.Credit)),
        Pair("debt", getTransactionsSum(TransactionType.Debt)),
        Pair(
            "total",
            getTransactionsSum(TransactionType.Debt) - getTransactionsSum(TransactionType.Credit)
        )
    )

    fun getTotalTransactionsValues(month: Int) = mapOf(
        Pair("credit", getTransactionsSum(month, TransactionType.Credit)),
        Pair("debt", getTransactionsSum(month, TransactionType.Debt)),
        Pair(
            "total",
            getTransactionsSum(month, TransactionType.Debt) - getTransactionsSum(
                month,
                TransactionType.Credit
            )
        )
    )

    fun getTotalTransactionsValues(month: Int, year: Int) = mapOf(
        Pair("credit", getTransactionsSum(month, year, TransactionType.Credit)),
        Pair("debt", getTransactionsSum(month, year, TransactionType.Debt)),
        Pair(
            "total",
            getTransactionsSum(month, year, TransactionType.Debt) - getTransactionsSum(
                month, year,
                TransactionType.Credit
            )
        )
    )
}