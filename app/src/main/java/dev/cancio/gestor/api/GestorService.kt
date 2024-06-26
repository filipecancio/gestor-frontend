package dev.cancio.gestor.api

import dev.cancio.gestor.domain.TotalTransactionReport
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GestorService {
    @GET("transactions/all/")
    suspend fun getAllTransactions() : Map<String, List<Transaction>>

    @GET("transactions/all/{id}")
    suspend fun getTransactionsById(
        @Path("id")  id: Int
    ) : Transaction

    @GET("transactions/all/")
    suspend fun getFilteredTransactionsByType(
        @Query("type")  type: Int
    ) : Map<String, List<Transaction>>

    @GET("transactions/all/")
    suspend fun getFilteredTransactions(
        @Query("type")  type: TransactionType,
        @Query("month") month: Int
    ) : Map<String, List<Transaction>>

    @GET("transactions/all/")
    suspend fun getFilteredTransactions(
        @Query("type")  type: TransactionType,
        @Query("month") month: Int,
        @Query("year") year: Int
    ) : Map<String, List<Transaction>>

    @GET("transactions/all/")
    suspend fun getFilteredTransactions(
        @Query("month") month: Int,
        @Query("year") year: Int
    ) : Map<String, List<Transaction>>

    @GET("transactions/monthly/")
    suspend fun getMonthlyTransactions() : List<Transaction>

    @GET("transactions/sum/total")
    suspend fun getTotalTransactionsValues(
    ) : TotalTransactionReport


    @GET("transactions/sum/total")
    suspend fun getTotalTransactionsValues(
        @Query("month") month: Int,
        @Query("year") year: Int
    ) :  TotalTransactionReport

}