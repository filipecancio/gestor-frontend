package dev.cancio.gestor.api

import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
import javax.inject.Inject

class GestorApi @Inject constructor(private val api: GestorService) {

    suspend fun getAllTransactions(): List<Transaction> {
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
}