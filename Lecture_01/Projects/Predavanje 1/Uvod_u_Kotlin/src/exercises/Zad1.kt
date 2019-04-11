const val ANONYMOUS = "Anonymous"

fun main() {
    println("Please enter your name")

    val input = readLine()

    val userName = if (input != null && input.isNotBlank()) input else ANONYMOUS

    if (userName == ANONYMOUS) println("Hello guest") else println("Hello $userName!")
}
