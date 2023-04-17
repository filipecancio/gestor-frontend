package dev.cancio.gestor.ui.components.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.cancio.gestor.ui.theme.dark02
import dev.cancio.gestor.ui.theme.gray01

@Composable
fun TransactionHeader(
    text:String
) = Row(
    Modifier
        .fillMaxWidth()
        .background(dark02)
        .padding(top = 8.dp)
) {
    Text(
        text,
        color = gray01,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}