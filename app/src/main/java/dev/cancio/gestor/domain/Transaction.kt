package dev.cancio.gestor.domain

data class Transaction(
    val description: String,
    val bank: String,
    val value: String,
    val type: TransactionType
)