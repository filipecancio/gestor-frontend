package dev.cancio.gestor.api

import dev.cancio.gestor.domain.TotalTransactionReport
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class GestorApi @Inject constructor(private val api: GestorService) {

    suspend fun getAllTransactions(): Map<String, List<Transaction>> {
        return api.getAllTransactions()
    }

    suspend fun getTransactionsById(id: Int): Transaction {
        return api.getTransactionsById(id)
    }

    suspend fun getFilteredTransactions(
        type: TransactionType
    ): Map<String, List<Transaction>> {
        return api.getFilteredTransactions(type)
    }

    suspend fun getFilteredTransactions(
        type: TransactionType,
        month: Int
    ): Map<String, List<Transaction>> {
        return api.getFilteredTransactions(type, month)
    }

    suspend fun getFilteredTransactions(
        type: TransactionType,
        month: Int,
        year: Int
    ): Map<String, List<Transaction>> {
        return api.getFilteredTransactions(type, month, year)
    }

    suspend fun getFilteredTransactions(
        month: Int
    ): Map<String, List<Transaction>> {
        return api.getFilteredTransactions(month)
    }

    suspend fun getFilteredTransactions(
        month: Int,
        year: Int
    ): Map<String, List<Transaction>> {
        return api.getFilteredTransactions( month, year)
    }
    
    suspend fun getMonthlyTransactions() : List<Transaction> = api.getMonthlyTransactions()
    
    suspend fun getTransactionsSum(
        type: TransactionType
    ) : Pair<String, Double> = api.getTransactionsSum(type)

    suspend fun getTransactionsSum(
        type: TransactionType,
        month: Int
    ) : Pair<String, Double> = api.getTransactionsSum(type, month)

    suspend fun getTransactionsSum(
        type: TransactionType,
        month: Int,
        year: Int
    ) : Pair<String, Double> = api.getTransactionsSum(type, month, year)

    suspend fun getTotalTransactionsValues(
    ) :  TotalTransactionReport = api.getTotalTransactionsValues()

    suspend fun getTotalTransactionsValues(
        month: Int
    ) : TotalTransactionReport = api.getTotalTransactionsValues(month)

    suspend fun getTotalTransactionsValues(
        month: Int,
        year: Int
    ) :  TotalTransactionReport = api.getTotalTransactionsValues(month, year)
}