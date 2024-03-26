package dev.cancio.gestor.repository

import android.util.Log
import dev.cancio.gestor.api.GestorApi
import dev.cancio.gestor.domain.TotalTransactionReport
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewTransactionRepository @Inject constructor(
    private val api: GestorApi
) {

    suspend fun getTransaction(transactionId: Int): Flow<Transaction> = flow {
        val result = api.getTransactionsById(transactionId)
        emit(result)
    }

    suspend fun getTransactions(): Flow<Map<String, List<Transaction>>> =
        flow {
            val result = api.getAllTransactions()
            emit(result)
        }

    suspend fun getTransactions(type: TransactionType): Flow<Map<String, List<Transaction>>> =
        flow {
            val result = api.getFilteredTransactions(type)
            emit(result)
        }

    suspend fun getTransactions(month: Int, type: TransactionType): Map<String, List<Transaction>> =
        api.getFilteredTransactions(type, month)

    suspend fun getTransactions(
        month: Int,
        year: Int,
        type: TransactionType
    ): Map<String, List<Transaction>> = api.getFilteredTransactions(type, month, year)

    suspend fun getTransactions(description: String): Flow<Map<String, List<Transaction>>> = flow {
        val result = api.getAllTransactions()
        emit(result)
    }

    suspend fun getTransactions(month: Int): Map<String, List<Transaction>> =
        api.getFilteredTransactions(month)

    suspend fun getTransactions(month: Int, year: Int): Map<String, List<Transaction>> =
        api.getFilteredTransactions(month, year)

    suspend fun getMonthlyTransactions(): Flow<List<Transaction>> = flow {
        val result = api.getMonthlyTransactions()
        emit(result)
    }

    private suspend fun getTransactionsSum(type: TransactionType): Double =
        api.getTransactionsSum(type).second

    private suspend fun getTransactionsSum(month: Int, type: TransactionType): Double =
        api.getTransactionsSum(type, month).second

    private suspend fun getTransactionsSum(month: Int, year: Int, type: TransactionType): Double =
        api.getTransactionsSum(type, month, year).second

    suspend fun getTotalTransactionsValues(): Flow<TotalTransactionReport> = flow {
        val result = api.getTotalTransactionsValues()
        emit(result)
    }

    suspend fun getTotalTransactionsValues(month: Int): TotalTransactionReport =
        api.getTotalTransactionsValues(month)

    suspend fun getTotalTransactionsValues(month: Int, year: Int): TotalTransactionReport =
        api.getTotalTransactionsValues(month, year)
}