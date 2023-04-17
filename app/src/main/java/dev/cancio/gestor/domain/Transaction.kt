package dev.cancio.gestor.domain

data class Transaction(
    val description: String,
    val bank: String,
    val value: String,
    val type: TransactionType
)

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