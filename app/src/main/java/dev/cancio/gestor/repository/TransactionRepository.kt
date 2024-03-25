package dev.cancio.gestor.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
import java.io.IOException
import java.util.*

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

    fun getTransaction(transactionId: Int) = getTransactionsFromJson()
        .filter{ it.id == transactionId }.first()

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

    fun getMonthlyTransactions() = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .groupBy { it.dateFormat }
        .map { item ->
            Transaction(
                id = 1,
                value = item.value.sumOf {it.value},
                description = item.key,
                bank = "",
                timestamp = Calendar.getInstance().also {
                    it.time = item.value.first().timestamp
                }.time,
                type = TransactionType.CREDIT
            )
        }

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
        Pair("credit", getTransactionsSum(TransactionType.CREDIT)),
        Pair("debt", getTransactionsSum(TransactionType.DEBT)),
        Pair(
            "total",
            getTransactionsSum(TransactionType.DEBT) - getTransactionsSum(TransactionType.CREDIT)
        )
    )

    fun getTotalTransactionsValues(month: Int) = mapOf(
        Pair("credit", getTransactionsSum(month, TransactionType.CREDIT)),
        Pair("debt", getTransactionsSum(month, TransactionType.DEBT)),
        Pair(
            "total",
            getTransactionsSum(month, TransactionType.DEBT) - getTransactionsSum(
                month,
                TransactionType.CREDIT
            )
        )
    )

    fun getTotalTransactionsValues(month: Int, year: Int) = mapOf(
        Pair("credit", getTransactionsSum(month, year, TransactionType.CREDIT)),
        Pair("debt", getTransactionsSum(month, year, TransactionType.DEBT)),
        Pair(
            "total",
            getTransactionsSum(month, year, TransactionType.DEBT) - getTransactionsSum(
                month, year,
                TransactionType.CREDIT
            )
        )
    )
}