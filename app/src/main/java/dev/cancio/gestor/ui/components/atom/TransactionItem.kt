package dev.cancio.gestor.ui.components.atom

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.domain.TransactionType
import dev.cancio.gestor.ui.theme.gray01
import dev.cancio.gestor.ui.theme.gray03
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionItem(
    transaction: Transaction
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
        TransactionIcon(transaction.type)
        Column(Modifier.padding(start = 8.dp)) {
            Text(
                text = transaction.description,
                color = gray01,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = transaction.dateFormat,
                color = gray03,
                fontSize = 18.sp
            )
        }
    }
    Text(
        text = transaction.moneyValue,
        color = gray01,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = false)
@Composable
fun TransactionPreview() =
    TransactionItem(Transaction("IPTU", "NuConta - (01/05)", 59.90, Date(), TransactionType.Credit))