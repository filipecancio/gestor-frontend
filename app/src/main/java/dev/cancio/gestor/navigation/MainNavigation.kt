package dev.cancio.gestor.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.cancio.gestor.presentation.screens.DetailScreen
import dev.cancio.gestor.presentation.screens.HomeScreen
import dev.cancio.gestor.presentation.screens.MonthlyScreen
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
            val month = backStackEntry.arguments?.getInt("transactionId")
                ?.let { DetailScreen(it, navController) }
        }
        composable("section",
            //arguments = listOf(
            //    navArgument("month") { type = NavType.IntType },
            //    navArgument("year") { type = NavType.IntType }
            //)
        ) { backStackEntry ->
            //val month = backStackEntry.arguments?.getInt("month")
            //val year = backStackEntry.arguments?.getInt("year")
            //if (month != null && year != null) {
                MonthlyScreen(navController)
            //}
        }
        composable("section-detail",
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

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Section : BottomNavItem("section", Icons.Default.Call, "Meses")
}


sealed class AppRoutes(val itemList: List<BottomNavItem>){
    object MainRoute: AppRoutes(listOf(BottomNavItem.Home, BottomNavItem.Section))
}
