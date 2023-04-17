package dev.cancio.gestor.ui.components.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.cancio.gestor.domain.TransactionType
import dev.cancio.gestor.ui.theme.gray01

@Composable
fun TransactionIcon(
    type: TransactionType
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

@Preview(showBackground = false)
@Composable
fun TransactionIconPreview() = Row() {
    TransactionType.values().map { TransactionIcon(type = it) }
}