package osc.ada.terezijaumiljanovic.bestpizzas.model



data class Pizza(
    var id: Int = 0,
    val type: String,
    val place: String,
    val price: Int,
    var grade: Int
)