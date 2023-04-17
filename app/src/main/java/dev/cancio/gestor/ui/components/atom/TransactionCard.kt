package dev.cancio.gestor.ui.components.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.cancio.gestor.ui.theme.dark01
import dev.cancio.gestor.ui.theme.gray01

@Composable
fun TransactionCard(
    value: String,
    type: TransactionIconType
) = Row(
    Modifier
        .background(
            shape = RoundedCornerShape(10.dp),
            color = dark01
        )
        .padding(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    TransactionIcon(type)
    Column(
        Modifier.padding(start = 8.dp)
    ) {
        Text(
            text = type.title,
            color = type.color,
            fontSize = 8.sp
        )
        Text(
            text = value,
            color = gray01,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = false)
@Composable
fun TransactionCardPreview() = Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    TransactionCard(value = "R$249,50", type = TransactionIconType.Credit)
    TransactionCard(value = "R$150,50", type = TransactionIconType.Debt)
}