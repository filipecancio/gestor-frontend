package dev.cancio.gestor.presentation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.cancio.gestor.domain.MonthValue
import dev.cancio.gestor.domain.TransactionType
import dev.cancio.gestor.repository.TransactionRepository
import dev.cancio.gestor.ui.components.atom.TransactionCard
import dev.cancio.gestor.ui.components.atom.TransactionHeader
import dev.cancio.gestor.ui.components.atom.TransactionItem
import dev.cancio.gestor.ui.theme.dark02
import dev.cancio.gestor.ui.theme.gray01
import dev.cancio.gestor.ui.theme.gray03
import java.text.DecimalFormat
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SectionScreen(
    month: Int,
    year: Int
) {
    val repository = TransactionRepository(LocalContext.current)
    val totalValue = repository.getTotalTransactionsValues(month,year)
    val currentList = remember { mutableStateOf(repository.getTransactions(month,year)) }
    val filterValue = remember { mutableStateOf("none") }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = dark02)
            .padding(horizontal = 16.dp)
    ) {

        Text(
            text = MonthValue.getDate(month,year) ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = gray01,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (totalValue["total"]!! > 0.0) {
                    "Restante à pagar"
                } else {
                    "Valor Sobrando"
                },
                color = gray03,
                fontSize = 18.sp
            )
            Text(
                text = "R$ ${DecimalFormat("#.##").format(totalValue["total"]?.absoluteValue)}",
                color = gray01,
                fontSize = 32.sp
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TransactionCard(
                value = "R$ ${DecimalFormat("#.##").format(totalValue["credit"])}",
                type = TransactionType.Credit,
                selected = (filterValue.value == "credit")
            ) {
                if (filterValue.value == "none" || filterValue.value == "debt") {
                    filterValue.value = "credit"
                    currentList.value = repository.getTransactions(month,year, TransactionType.Credit)
                } else {
                    filterValue.value = "none"
                    currentList.value = repository.getTransactions(month,year)
                }
            }
            TransactionCard(
                value = "R$ ${DecimalFormat("#.##").format(totalValue["debt"])}",
                type = TransactionType.Debt,
                selected = (filterValue.value == "debt")
            ) {
                if (filterValue.value == "none" || filterValue.value == "credit") {
                    filterValue.value = "debt"
                    currentList.value = repository.getTransactions(month,year, TransactionType.Debt)
                } else {
                    filterValue.value = "none"
                    currentList.value = repository.getTransactions(month,year)
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn() {
            currentList.value.forEach { (date, transactionItems) ->
                stickyHeader {
                    TransactionHeader(date)
                }
                items(transactionItems) {
                    Log.i("update", transactionItems.toString())
                    TransactionItem(transaction = it)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SectionScreenPreview() = SectionScreen(month = 3, year = 123)