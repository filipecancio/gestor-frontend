package dev.cancio.gestor.domain

enum class FilterType {
    NONE,
    DEBT,
    CREDIT;

    fun isCreditOrNone() = this == CREDIT || this == NONE
    fun isDebtOrNone() = this == DEBT || this == NONE

    fun toTransactionType() = when(this){
        CREDIT -> TransactionType.Credit
        DEBT -> TransactionType.Debt
        NONE -> null
    }
}