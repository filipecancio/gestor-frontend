package dev.cancio.gestor.navigation

import android.os.Build
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
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
import dev.cancio.gestor.presentation.viewmodels.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import dev.cancio.gestor.presentation.viewmodels.DetailViewModel
import dev.cancio.gestor.presentation.viewmodels.MonthlyViewModel
import dev.cancio.gestor.presentation.viewmodels.SectionViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation(navController: NavHostController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val detailViewModel = hiltViewModel<DetailViewModel>()
    val monthlyViewModel = hiltViewModel<MonthlyViewModel>()
    val sectionViewModel = hiltViewModel<SectionViewModel>()

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
        ) { backStackEntry ->
                MonthlyScreen(navController)
        }
        composable("section-detail/{month}/{year}",
            arguments = listOf(
                navArgument("month") { type = NavType.IntType },
                navArgument("year") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val month = backStackEntry.arguments?.getInt("month")
            val year = backStackEntry.arguments?.getInt("year")
            if (month != null && year != null) {
                SectionScreen(month, year,navController)
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
    object Section : BottomNavItem("section", Icons.Default.Info, "Meses")
}


sealed class AppRoutes(val itemList: List<BottomNavItem>){
    object MainRoute: AppRoutes(listOf(BottomNavItem.Home, BottomNavItem.Section))
}
