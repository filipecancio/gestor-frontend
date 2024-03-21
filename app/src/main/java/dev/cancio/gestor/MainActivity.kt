package dev.cancio.gestor

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.cancio.gestor.navigation.AppRoutes.MainRoute.itemList
import dev.cancio.gestor.navigation.MainNavigation
import dev.cancio.gestor.ui.components.molecule.BottomAppBar
import dev.cancio.gestor.ui.theme.GestorTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            GestorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {},
                        bottomBar = { BottomAppBar(navController, itemList) }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize()
                        ) {
                            MainNavigation(navController)
                        }
                    }
                }
            }
        }
    }
}