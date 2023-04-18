package dev.cancio.gestor.domain

enum class MonthValue (
    val value: Int,
    val month:String
) {
    JANUARY(0,"Janeiro"),
    FEBRUARY(1,"Fevereiro"),
    MARCH(2,"Março"),
    APRIL( 3,"Abril"),
    MAY(4,"Maio"),
    JUNE(5,"Junho"),
    JULY(6,"Julho"),
    AUGUST(7,"Agosto"),
    SEPTEMBER(8,"Setembro"),
    OCTOBER(9,"Outubro"),
    NOVEMBER(10,"Novembro"),
    DECEMBER(11,"Dezembro");

    companion object {
        infix fun from(value: Int): MonthValue? = MonthValue.values().firstOrNull { it.value == value }

        fun getDate(value: Int, year:Int): String? {
            val monthValue = MonthValue.values().firstOrNull { it.value == value }
            return "${monthValue?.month} de ${year+1900}"
        }
    }
}