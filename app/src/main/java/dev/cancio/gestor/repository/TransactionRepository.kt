package dev.cancio.gestor.repository

import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType

class TransactionRepository {
    val transactionList = listOf<Transaction>(
        Transaction(
            "IPTU",
            "NuConta - (01/05)",
            "R$59,90",
            TransactionType.Credit
        ),
        Transaction(
            "Troca de Oleo",
            "NuConta - (01/10)",
            "R$59,90",
            TransactionType.Credit
        ),
        Transaction(
            "Limpeza de Ar Cond.",
            "NuConta - (01/05)",
            "R$59,90",
            TransactionType.Credit
        ),
        Transaction(
            "Licença Antivírus",
            "NuConta - À vista",
            "R$59,90",
            TransactionType.Debt
        ),
        Transaction(
            "Viagem Bahia",
            "NuConta - À vista",
            "R$59,90",
            TransactionType.Debt
        ),
        Transaction(
            "Viagem Bahia",
            "NuConta - À vista",
            "R$59,90",
            TransactionType.Debt
        )
    )


    fun getTransactions() = transactionList
}