package dev.cancio.gestor.presentation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.cancio.gestor.presentation.viewmodels.MonthlyViewModel
import dev.cancio.gestor.ui.components.atom.TransactionItem
import dev.cancio.gestor.ui.theme.dark02
import dev.cancio.gestor.ui.theme.gray01

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthlyScreen(
    navController: NavHostController,
    viewModel: MonthlyViewModel
) {
    val monthlyUiStateFlow by rememberUpdatedState(newValue = viewModel.monthlyUiStateFlow.collectAsState())
    with(monthlyUiStateFlow.value){
        Column(
            Modifier
                .fillMaxSize()
                .background(color = dark02)
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = "Somatorio dos meses",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                color = gray01,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn() {
                items(currentList) {
                    Log.i("update", currentList.toString())
                    TransactionItem(transaction = it){
                        navController.navigate("section-detail/${it.timestamp.month}/${it.timestamp.year}")
                    }
                }
            }
        }
    }
}