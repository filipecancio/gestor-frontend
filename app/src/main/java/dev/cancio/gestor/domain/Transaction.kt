package dev.cancio.gestor.domain

data class Transaction(
    val description: String,
    val bank: String,
    val value: Double,
    val type: TransactionType
) {
    val moneyValue: String
    get() = "R$ ${value.toString()}"
}