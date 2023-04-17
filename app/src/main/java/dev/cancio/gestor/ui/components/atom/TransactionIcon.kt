package dev.cancio.gestor.ui.components.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.cancio.gestor.ui.theme.gray01
import dev.cancio.gestor.ui.theme.green01
import dev.cancio.gestor.ui.theme.red01

@Composable
fun TransactionIcon(
    type: TransactionIconType
) = Box(
    modifier = Modifier.background(
        shape = RoundedCornerShape(20.dp),
        color = type.color
    )
) {
    Icon(
        imageVector = type.icon,
        contentDescription = null,
        tint = gray01
    )
}

enum class TransactionIconType(
    val color: Color,
    val icon: ImageVector,
    val title: String
    ){
    Credit(
        green01,
        Icons.Default.KeyboardArrowUp,
        "Créditos"
    ),
    Debt(
        red01,
        Icons.Default.KeyboardArrowDown,
        "Débitos"
    )
}

@Preview(showBackground = false)
@Composable
fun TransactionIconPreview() = Row() {
    TransactionIconType.values().map { TransactionIcon(type = it) }
}