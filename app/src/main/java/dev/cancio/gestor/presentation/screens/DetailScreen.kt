package dev.cancio.gestor.presentation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.cancio.gestor.repository.TransactionRepository
import dev.cancio.gestor.ui.components.atom.TransactionHeader
import dev.cancio.gestor.ui.components.atom.TransactionItem
import dev.cancio.gestor.ui.theme.dark01
import dev.cancio.gestor.ui.theme.dark02
import dev.cancio.gestor.ui.theme.gray01
import dev.cancio.gestor.ui.theme.gray03
import java.text.DecimalFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    transactionId: Int,
    navController: NavHostController
    ) {
    val repository = TransactionRepository(LocalContext.current)
    val transaction = repository.getTransaction(transactionId)
    val currentList = repository.getTransactions(transaction.description)

    Column(
        Modifier
            .fillMaxSize()
            .background(color = dark02)
            .padding(horizontal = 16.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = transaction.description,
                color = gray03,
                fontSize = 18.sp
            )
            Text(
                text = "R$ ${DecimalFormat("#.##").format(transaction.value)}",
                color = gray01,
                fontSize = 32.sp
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
        ) {
            Text(
                text = "Registro feito por:",
                color = gray03,
                fontSize = 16.sp
            )
            Text(
                text = transaction.bank,
                color = gray01,
                fontSize = 18.sp
            )
        }
        Row(
            Modifier
                .background(
                    shape = RoundedCornerShape(10.dp),
                    color = dark01
                )
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = " Comprovante de pagamento",
                color = gray01,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row{
            Text(
                text = "Outras transações com a descrição \"${transaction.description}\":",
                color = gray01,
                fontSize = 16.sp
            )
        }
        LazyColumn() {
            currentList.forEach{(date,transactionItems)->
                stickyHeader {
                    TransactionHeader(date)
                }
                items(transactionItems) {
                    Log.i("update",transactionItems.toString())
                    TransactionItem(transaction = it){
                        navController.navigate("detail/${it.id}")
                    }
                }
            }
        }
    }

}