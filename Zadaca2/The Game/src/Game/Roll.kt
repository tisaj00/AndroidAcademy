package Game

import kotlin.random.Random

fun dice():Int{

    val value:Int = Random.nextInt(0,6)
    return value
}