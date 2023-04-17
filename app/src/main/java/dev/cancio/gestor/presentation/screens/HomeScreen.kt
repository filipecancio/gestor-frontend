package dev.cancio.gestor.presentation.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.cancio.gestor.domain.TransactionType
import dev.cancio.gestor.repository.TransactionRepository
import dev.cancio.gestor.ui.components.atom.TransactionCard
import dev.cancio.gestor.ui.components.atom.TransactionItem
import dev.cancio.gestor.ui.theme.dark02
import dev.cancio.gestor.ui.theme.gray01
import dev.cancio.gestor.ui.theme.gray03

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(context: Context) {
    val repository = TransactionRepository()
    val transactionList = repository.getTransactionsFromJson(context)
    val creditList = transactionList.filter { it.type == TransactionType.Credit }
    val debtList = transactionList.filter { it.type == TransactionType.Debt }
    val totalCredit = creditList.sumOf { it.value }
    val totalDebt = debtList.sumOf { it.value }
    val totalValue = totalCredit - totalDebt

    Column(
        Modifier
            .fillMaxSize()
            .background(color = dark02)
            .padding(horizontal = 16.dp)
    ) {

        Text(
            text = "Janeiro",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = gray01,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Restante à pagar",
                color = gray03,
                fontSize = 18.sp
            )
            Text(
                text = "R$ $totalValue",
                color = gray01,
                fontSize = 32.sp
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            TransactionCard(value = "R$ $totalCredit", type = TransactionType.Credit)
            TransactionCard(value = "R$ $totalDebt", type = TransactionType.Debt)
        }
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn() {
            items(transactionList) {
                TransactionItem(transaction = it)
            }
        }
    }
}
