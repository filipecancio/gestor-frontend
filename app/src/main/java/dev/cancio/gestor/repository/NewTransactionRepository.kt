package dev.cancio.gestor.repository

import dev.cancio.gestor.api.GestorApi
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
import javax.inject.Inject

class NewTransactionRepository @Inject constructor(
    private val api: GestorApi
) {

    suspend fun getTransaction(transactionId: Int): Transaction =
        api.getTransactionsById(transactionId)

    suspend fun getTransactions(): Map<String, List<Transaction>> = api.getAllTransactions()

    suspend fun getTransactions(type: TransactionType): Map<String, List<Transaction>> =
        api.getFilteredTransactions(type)

    suspend fun getTransactions(month: Int, type: TransactionType): Map<String, List<Transaction>> =
        api.getFilteredTransactions(type, month)

    suspend fun getTransactions(
        month: Int,
        year: Int,
        type: TransactionType
    ): Map<String, List<Transaction>> = api.getFilteredTransactions(type, month, year)

    suspend fun getTransactions(description: String): Map<String, List<Transaction>> =
        api.getAllTransactions()

    suspend fun getTransactions(month: Int): Map<String, List<Transaction>> =
        api.getFilteredTransactions(month)

    suspend fun getTransactions(month: Int, year: Int): Map<String, List<Transaction>> =
        api.getFilteredTransactions(month, year)

    suspend fun getMonthlyTransactions(): List<Transaction> = api.getMonthlyTransactions()

    private suspend fun getTransactionsSum(type: TransactionType): Double =
        api.getTransactionsSum(type).second

    private suspend fun getTransactionsSum(month: Int, type: TransactionType): Double =
        api.getTransactionsSum(type, month).second

    private suspend fun getTransactionsSum(month: Int, year: Int, type: TransactionType): Double =
        api.getTransactionsSum(type, month, year).second

    suspend fun getTotalTransactionsValues(): Map<String, Double> = api.getTotalTransactionsValues()

    suspend fun getTotalTransactionsValues(month: Int): Map<String, Double> =
        api.getTotalTransactionsValues(month)

    suspend fun getTotalTransactionsValues(month: Int, year: Int): Map<String, Double> =
        api.getTotalTransactionsValues(month, year)
}