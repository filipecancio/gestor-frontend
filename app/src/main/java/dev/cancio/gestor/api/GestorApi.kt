package dev.cancio.gestor.api

import dev.cancio.gestor.domain.TotalTransactionReport
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
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
        return api.getFilteredTransactionsByType(type.toRequest())
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
        month: Int,
        year: Int
    ): Map<String, List<Transaction>> {
        return api.getFilteredTransactions( month, year)
    }
    
    suspend fun getMonthlyTransactions() : List<Transaction> = api.getMonthlyTransactions()

    suspend fun getTotalTransactionsValues(
    ) :  TotalTransactionReport = api.getTotalTransactionsValues()

    suspend fun getTotalTransactionsValues(
        month: Int,
        year: Int
    ) :  TotalTransactionReport = api.getTotalTransactionsValues(month, year)
}