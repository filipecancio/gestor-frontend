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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.cancio.gestor.domain.FilterType
import dev.cancio.gestor.domain.TransactionType
import dev.cancio.gestor.presentation.viewmodels.HomeViewModel
import dev.cancio.gestor.repository.TransactionRepository
import dev.cancio.gestor.ui.components.atom.TransactionCard
import dev.cancio.gestor.ui.components.atom.TransactionHeader
import dev.cancio.gestor.ui.components.atom.TransactionItem
import dev.cancio.gestor.ui.theme.dark02
import dev.cancio.gestor.ui.theme.gray01
import dev.cancio.gestor.ui.theme.gray03
import dev.cancio.gestor.util.orZeroFormatted

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel

) {
    val homeUiStateFlow by rememberUpdatedState(newValue = viewModel.homeUiStateFlow.collectAsState())

    Column(
        Modifier
            .fillMaxSize()
            .background(color = dark02)
            .padding(horizontal = 16.dp)
    ) {
        with(homeUiStateFlow.value){
            Text(text = totalValue.toString())
            Text(
                text = "Gestor",
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
                    text = totalValue?.totalDescription ?: "",
                    color = gray03,
                    fontSize = 18.sp
                )
                Text(
                    text = totalValue?.totalFormatted.orZeroFormatted(),
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
                TransactionCard(value = totalValue?.creditFormatted.orZeroFormatted(), type = TransactionType.Credit, selected = (filterValue == FilterType.CREDIT)){
                    viewModel.onDebtCardPressed(filterValue)
                }
                TransactionCard(value = totalValue?.debtFormatted.orZeroFormatted(), type = TransactionType.Debt, selected = (filterValue == FilterType.DEBT)){
                    viewModel.onCreditCardPressed(filterValue)
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
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
}
