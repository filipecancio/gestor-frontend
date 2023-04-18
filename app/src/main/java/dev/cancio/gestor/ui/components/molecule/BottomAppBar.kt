package dev.cancio.gestor.ui.components.molecule

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import dev.cancio.gestor.navigation.BottomNavItem
import dev.cancio.gestor.ui.theme.gray02

@Composable
fun BottomAppBar(navController: NavController, itemList: List<BottomNavItem>)  {
    var selectedItem = remember { mutableStateOf(0) }
    NavigationBar(
    ) {
        itemList.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null, tint = gray02) },
                label = { Text(text = item.title, color = gray02) },
                selected = selectedItem.value == index,
                onClick = {
                    selectedItem.value = index
                    navController.navigate(item.route)
                }
            )
        }
    }
}
