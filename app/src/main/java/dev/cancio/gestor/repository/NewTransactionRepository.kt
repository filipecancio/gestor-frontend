package dev.cancio.gestor.repository

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

    suspend fun getTransactions(description: String): Flow<Map<String, List<Transaction>>> = flow {
        val result = api.getAllTransactions()
        emit(result)
    }

    suspend fun getTransactions(month: Int, year: Int): Flow<Map<String, List<Transaction>>> = flow {
        val result = api.getFilteredTransactions(month, year)
        emit(result)
    }

    suspend fun getMonthlyTransactions(): Flow<List<Transaction>> = flow {
        val result = api.getMonthlyTransactions()
        emit(result)
    }

    suspend fun getTotalTransactionsValues(): Flow<TotalTransactionReport> = flow {
        val result = api.getTotalTransactionsValues()
        emit(result)
    }

    suspend fun getTotalTransactionsValues(month: Int, year: Int): Flow<TotalTransactionReport> =
        flow{
            val result = api.getTotalTransactionsValues(month, year)
            emit(result)
        }
}