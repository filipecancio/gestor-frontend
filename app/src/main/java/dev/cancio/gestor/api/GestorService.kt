package dev.cancio.gestor.api

import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GestorService {
    @GET("transactions/")
    suspend fun getAllTransactions() : List<Transaction>

    @GET("transactions/{id}")
    suspend fun getTransactionsById(
        @Path("id")  id: Int
    ) : Transaction

    @GET("transactions/")
    suspend fun getFilteredTransactions(
        @Query("type")  type: TransactionType
    ) : Map<String, List<Transaction>>

    @GET("transactions/")
    suspend fun getFilteredTransactions(
        @Query("type")  type: TransactionType,
        @Query("month") month: Int
    ) : Map<String, List<Transaction>>

    @GET("transactions/")
    suspend fun getFilteredTransactions(
        @Query("type")  type: TransactionType,
        @Query("month") month: Int,
        @Query("year") year: Int
    ) : Map<String, List<Transaction>>

    @GET("transactions/")
    suspend fun getFilteredTransactions(
        @Query("month") month: Int,
    ) : Map<String, List<Transaction>>

    @GET("transactions/")
    suspend fun getFilteredTransactions(
        @Query("month") month: Int,
        @Query("year") year: Int
    ) : Map<String, List<Transaction>>

}