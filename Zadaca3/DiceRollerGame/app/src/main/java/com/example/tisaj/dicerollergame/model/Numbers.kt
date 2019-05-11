package com.example.tisaj.dicerollergame.model

import kotlin.random.Random

object Numbers {

    var numbersAvailable: Int = 2
    var restartGame = false
    val dices: Array<Roll> = arrayOf(
        Roll(1), Roll(2), Roll(3), Roll(4), Roll(5), Roll(6)
    )

    fun Dices() {
        when (numbersAvailable) {
            1 -> {
                dices.forEach { it.value = Random.nextInt(1, 6) }
                numbersAvailable--
            }
            1 -> {
                dices.forEach {
                    if (it.selected == false) {
                        it.value = Random.nextInt(1, 6)
                    }
                }
                numbersAvailable--
            }

            0 -> restartGame



        }

    }
}