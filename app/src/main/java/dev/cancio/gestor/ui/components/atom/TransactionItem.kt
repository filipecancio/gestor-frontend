package dev.cancio.gestor.ui.components.atom

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.cancio.gestor.ui.theme.gray01
import dev.cancio.gestor.ui.theme.gray03

@Composable
fun TransactionItem(
    description: String,
    bank: String,
    value: String,
    type: TransactionIconType
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TransactionIcon(type)
        Column(Modifier.padding(start = 8.dp)) {
            Text(
                text = description,
                color = gray01,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = bank,
                color = gray03
            )
        }
    }
    Text(
        text = value,
        color = gray01,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = false)
@Composable
fun TransactionPreview() =
    TransactionItem("IPTU", "NuConta - (01/05)", "R$59,90", TransactionIconType.Credit)