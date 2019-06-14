package game

import entities.Champs.Abstract.BaseChamp
import entities.Champs.Adc
import entities.Champs.Support
import entities.Champs.Tank
import entities.Enemies.Bangalore
import entities.Enemies.Caustic
import entities.Enemies.Lifeline

fun main(){

    lateinit var player: BaseChamp
    var odabir = 0
    print("Odaberi klasu s kojom želiš igrati:\n" +
            "1. Adc\n" +
            "2. Tank\n" +
            "3. Support\n"
    )
    while (odabir > 3 || odabir < 1){
        odabir = readLine()!!.toInt()
        when(odabir){
            1 -> player = Adc()
            2 -> player = Tank()
            3 -> player = Support()
            else -> println("Krivo")
        }
    }
    println("\nBorba započinje s prvim bossom:\nCaustic")
    while(Caustic.health>0) {
        odabir = 0
        if (player.health <= 0) {
            println("Šteta!Mrtav si!")
            break
        }
        println("Health: ${player.health}")
        println("Mana: ${player.mana}")
        println("Boss health: ${Caustic.health}")
        println("Odaberi napad:\n 1.Obični napad \n 2.Napad magijom")
        while (odabir > 2 || odabir < 1) {
            odabir = readLine()!!.toInt()
            when (odabir) {
                1 -> Caustic.health -= player.dmgDeal()
                2 -> Caustic.health -= player.spell()
                else -> println("Kriv odabir")
            }
        }
        player.health -= Caustic.base()
        if (Caustic.health <= Caustic.health / 3)
            Caustic.spell()
        if(Caustic.health<0){
            println("Prvi boss poražen")
        }
    }
    println("Borba započinje s drugim bossom:\nLifeline")
    while(Lifeline.health>0) {
        odabir = 0
        if (player.health <= 0) {
            println("Šteta!Mrtav si!")
            break
        }
        println("Health: ${player.health}")
        println("Mana: ${player.mana}")
        println("Boss health: ${Lifeline.health}")
        println("Odaberi napad:\n 1.Obični napad \n 2.Napad magijom")
        while (odabir > 2 || odabir < 1) {
            odabir = readLine()!!.toInt()
            when (odabir) {
                1 -> Lifeline.health -= player.dmgDeal()
                2 -> Lifeline.health -= player.spell()
                else -> println("Kriv odabir")
            }
        }
        player.health -= Lifeline.base()
        if (Lifeline.health <= Lifeline.health / 4)
            Lifeline.spell()
        if(Lifeline.health<0){
            println("Drugi boss poražen")
        }
    }
    println("Borba započinje s posljednjim bossom:\nBangalore")
    while(Bangalore.health>0) {
        odabir = 0
        if (player.health <= 0) {
            println("Šteta!Mrtav si!")
            break
        }
        println("Health: ${player.health}")
        println("Mana: ${player.mana}")
        println("Boss health: ${Bangalore.health}")
        println("Odaberi napad:\n 1.Obični napad \n 2.Napad magijom")
        while (odabir > 2 || odabir < 1) {
            odabir = readLine()!!.toInt()
            when (odabir) {
                1 -> Bangalore.health -= player.dmgDeal()
                2 -> Bangalore.health -= player.spell()
                else -> println("Kriv odabir")
            }
        }
        player.health -= Bangalore.base()
        if (Bangalore.health <= Bangalore.health / 3)
            Bangalore.spell()
        if(Bangalore.health<0){
            println("Svaka cast ubio si sva tri bossa. Igra završena")
        }
    }

}

