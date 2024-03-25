package dev.cancio.gestor.domain

import java.text.DecimalFormat
import kotlin.math.absoluteValue

data class TotalTransactionReport(
    val total: Double,
    val debt: Double,
    val credit: Double
) {
    val totalDescription: String
        get() = if (total > 0.0) "Restante à pagar" else "Valor Sobrando"
    val totalFormatted: String
        get() = "R$ ${DecimalFormat("#.##").format(total.absoluteValue)}"
    val debtFormatted: String
        get() = "R$ ${DecimalFormat("#.##").format(debt.absoluteValue)}"
    val creditFormatted: String
        get() = "R$ ${DecimalFormat("#.##").format(credit.absoluteValue)}"
}