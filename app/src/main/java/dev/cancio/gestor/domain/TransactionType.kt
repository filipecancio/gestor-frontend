package dev.cancio.gestor.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.gson.annotations.SerializedName
import dev.cancio.gestor.ui.theme.green01
import dev.cancio.gestor.ui.theme.green02
import dev.cancio.gestor.ui.theme.red01
import dev.cancio.gestor.ui.theme.red02

enum class TransactionType(
    val color: Color,
    val secondcolor: Color,
    val icon: ImageVector,
    val title: String
){
    @SerializedName("0")
    CREDIT(
        green01,
        green02,
        Icons.Default.KeyboardArrowUp,
        "Créditos"
    ),
    @SerializedName("1")
    DEBT(
        red01,
        red02,
        Icons.Default.KeyboardArrowDown,
        "Débitos"
    );

    fun toRequest(): Int = when(this){
        CREDIT -> 0
        DEBT -> 1
    }
}