package dev.cancio.gestor.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.gson.annotations.SerializedName
import dev.cancio.gestor.ui.theme.green01
import dev.cancio.gestor.ui.theme.red01

enum class TransactionType(
    val color: Color,
    val icon: ImageVector,
    val title: String
){
    @SerializedName("0")
    Credit(
        green01,
        Icons.Default.KeyboardArrowUp,
        "Créditos"
    ),
    @SerializedName("1")
    Debt(
        red01,
        Icons.Default.KeyboardArrowDown,
        "Débitos"
    )
}