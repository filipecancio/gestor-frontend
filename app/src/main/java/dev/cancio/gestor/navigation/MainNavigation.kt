package dev.cancio.gestor.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.cancio.gestor.presentation.screens.DetailScreen
import dev.cancio.gestor.presentation.screens.HomeScreen
import dev.cancio.gestor.presentation.screens.SectionScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable(
            "detail/{transactionId}",
            arguments = listOf(navArgument("transactionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val month = backStackEntry.arguments?.getInt("transactionId")?.let { DetailScreen(it) }
        }
        composable("section/{month}/{year}",
            arguments = listOf(
                navArgument("month") { type = NavType.IntType },
                navArgument("year") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val month = backStackEntry.arguments?.getInt("month")
            val year = backStackEntry.arguments?.getInt("year")
            if (month != null && year != null) {
                SectionScreen(month, year)
            }
        }
    }
}