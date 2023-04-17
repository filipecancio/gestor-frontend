package dev.cancio.gestor.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.cancio.gestor.ui.components.atom.TransactionCard
import dev.cancio.gestor.ui.components.atom.TransactionIconType
import dev.cancio.gestor.ui.components.atom.TransactionItem
import dev.cancio.gestor.ui.theme.dark02
import dev.cancio.gestor.ui.theme.gray01
import dev.cancio.gestor.ui.theme.gray03

@Composable
fun HomeScreen() = Column(
    Modifier
        .fillMaxSize()
        .background(color = dark02)
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Saldo Total",
            color = gray03,
            fontSize = 18.sp
        )
        Text(
            text = "R$3.250,00",
            color = gray01,
            fontSize = 32.sp
        )
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
            ){
        TransactionCard(value = "R$249,50", type = TransactionIconType.Credit)
        TransactionCard(value = "R$150,50", type = TransactionIconType.Debt)
    }
    LazyColumn() {
        items(transactionList) {
            TransactionItem(transaction = it)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview(
) = HomeScreen()

data class Transaction(
    val description: String,
    val bank: String,
    val value: String,
    val type: TransactionIconType
)

val transactionList = listOf<Transaction>(
    Transaction(
        "IPTU",
        "NuConta - (01/05)",
        "R$59,90",
        TransactionIconType.Credit
    ),
    Transaction(
        "Troca de Oleo",
        "NuConta - (01/10)",
        "R$59,90",
        TransactionIconType.Credit
    ),
    Transaction(
        "Limpeza de Ar Cond.",
        "NuConta - (01/05)",
        "R$59,90",
        TransactionIconType.Credit
    ),
    Transaction(
        "Licença Antivírus",
        "NuConta - À vista",
        "R$59,90",
        TransactionIconType.Debt
    ),
    Transaction(
        "Viagem Bahia",
        "NuConta - À vista",
        "R$59,90",
        TransactionIconType.Debt
    ),
    Transaction(
        "Viagem Bahia",
        "NuConta - À vista",
        "R$59,90",
        TransactionIconType.Debt
    )
)