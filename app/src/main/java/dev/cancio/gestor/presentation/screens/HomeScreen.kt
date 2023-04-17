package dev.cancio.gestor.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.cancio.gestor.domain.TransactionType
import dev.cancio.gestor.repository.TransactionRepository
import dev.cancio.gestor.ui.components.atom.TransactionCard
import dev.cancio.gestor.ui.components.atom.TransactionItem
import dev.cancio.gestor.ui.theme.dark02
import dev.cancio.gestor.ui.theme.gray01
import dev.cancio.gestor.ui.theme.gray03

@Composable
fun HomeScreen() {
    val repository = TransactionRepository()
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
        Spacer(modifier = Modifier.height(30.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            TransactionCard(value = "R$249,50", type = TransactionType.Credit)
            TransactionCard(value = "R$150,50", type = TransactionType.Debt)
        }
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn() {
            items(repository.getTransactions()) {
                TransactionItem(transaction = it)
            }
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