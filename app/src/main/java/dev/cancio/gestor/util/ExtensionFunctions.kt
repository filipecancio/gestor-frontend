package dev.cancio.gestor.util


fun String?.orZeroFormatted(): String {
    return this?.ifEmpty { "R$ 0.00" } ?: "R$ 0.00"
}