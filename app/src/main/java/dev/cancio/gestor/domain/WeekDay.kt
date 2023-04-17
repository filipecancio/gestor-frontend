package dev.cancio.gestor.domain

enum class WeekDay(
    val value: Int,
    val day:String
) {
    SUNDAY(0,"Domingo"),
    MONDAY(1,"Segunda"),
    TUESDAY(2,"Terça"),
    WEDNESDAY(3,"Quarta"),
    THURSDAY(4,"Quinta"),
    FRIDAY(5,"Sexta"),
    SATURDAY(6,"Sabado");

    companion object {
        infix fun from(value: Int): WeekDay? = WeekDay.values().firstOrNull { it.value == value }
    }
}