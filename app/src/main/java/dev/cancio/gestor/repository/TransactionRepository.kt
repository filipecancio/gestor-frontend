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
        val jsonFileString = getJsonDataFromAsset( "data.json")

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

    private fun getTransactionsSum(type: TransactionType) = getTransactionsFromJson()
        .sortedByDescending { it.timestamp }
        .filter { it.type == type }
        .sumOf { it.value }

    fun getTotalTransactionsValues() = mapOf(
        Pair("credit", getTransactionsSum(TransactionType.Credit)),
        Pair("debt", getTransactionsSum(TransactionType.Debt)),
        Pair(
            "total",
            getTransactionsSum(TransactionType.Debt) - getTransactionsSum(TransactionType.Credit)
        )
    )

    //val transactionList = repository.getTransactionsFromJson(context).sortedByDescending { it.timestamp }
    //val transactionGroupedList = transactionList.groupBy { it.dateFormat }
    //val creditList = transactionList.filter { it.type == TransactionType.Credit }
    //val debtList = transactionList.filter { it.type == TransactionType.Debt }
    //val totalCredit = creditList.sumOf { it.value }
    //val totalDebt = debtList.sumOf { it.value }
    //val totalValue = totalCredit - totalDebt
}