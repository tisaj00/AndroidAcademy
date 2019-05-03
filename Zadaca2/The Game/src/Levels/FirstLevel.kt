package Levels

import Entiteti.Enemies.Warior
import Entiteti.Player
import Game.dice
import Weapons.Main.Bow
import Weapons.Main.Sword


var dValue:Int = 0

fun first(){

    Player.heal()
    println("Igra započinje")
    println("-----------------")
    Wait()
    println("Nalazite se u šumi")
    Wait()
    println("U pozadini se glasaju ptice")
    Wait()
    println("U daljini vidite nekakvu ruševinu")
    Wait()
    println("Čujete nekakve glasove")
    Wait()

    println("A:Idete prema ruševini")
    println("B:Idete prema glasovima")
    println("Šta ćete odabrati? (odaberite 'A' ili 'B')")
    Player.decision.add(readLine() ?: "prazno")
    when(Player.decision.last()){
        "A"-> { Rusevina()}
        "B" -> {Glasovi()}
        else->{
            println("Niste odabrali točno.Pokusajte ponovno")
            Wait()
            first()
        }

    }
    Wait()
    println("")





}

fun Rusevina() {

    println("Dolazis do velikih drvenih vrata")
    Wait()
    println("Otvaraš ih i čuje se jako škripanje")
    Wait()
    println("U tom trenutku se pojavljuje neprijatelj ispred tebe u tami")
    println("Šta ćeš odlučiti?")
    Wait()
    println("A: Izvući mač i napasti ga!")
    println("B: Zatvorit vrata i pobjeći")
    Player.decision.add(readLine() ?: "prazno")
    when(Player.decision.last()){
        "A" -> {
            val Enemies : Array<Warior> = Array(3){ Warior() }

            Player.weapon = Sword()
            Player.health
            Player.PlayerHealth()
            println("Neprijatelj izvlači mač")
            Wait()
            println("Krećeš u napad!")
            Wait()
            dValue = dice()
            when{
                dValue<4 -> {
                    println("Napadaš jednom")
                    Enemies[0].firstPlayer(Enemies[0].damage)
                    Wait()
                    println("Neprijatelj blokira napad")
                    Enemies[1].firstPlayer((Enemies[1].damage))
                    Wait()
                    println("Bacaš ga na pod i ideš ga dokrajčiti")
                    Enemies[2].firstPlayer(Enemies[2].damage)
                    Wait()
                    Player.PlayerHealth()
                }

            }
            println("Prvi put se izmiče od udarca mača")
            Wait()
            Player.ultyAttack = true
            println("Dobio si dodatne snage da ga rješiš")
            Wait()
            for(enemy in Enemies) Player.enemyPlayer(Player.weapon.damage,enemy)
            println("Ubijaš ga")
            Wait()
            Player.PlayerHealth()
            Secound()
        }
        "B" -> {
            Pobjec()}

        else->{
            println("Niste dobro odabrali! Pokuštaje ponovno")
            Wait()
            Rusevina1()
            println("")
        }
    }


}

fun Glasovi() {

    println("Dolazite do drveta i vidite ljude kako sjede uz vatru")
    Wait()
    println("Jedan vas je primjetio")
    println("U ruci imate mač a na leđima luk i strijelu")
    Wait()
    println("Što ćete napraviti?")
    println("A: Uzeti mač i napasti")
    println("B: Gađati ga strijelom")
    Wait()
    Player.decision.add(readLine() ?: "prazno")
    when(Player.decision.last()) {
        "A" -> {
            Player.weapon = Sword()
        }
        "B" -> {
            Player.weapon = Bow()
        }
        else -> {
            Pobjec()
        }
    }
    println("Odabrao si ${Player.weapon.type}")
    Wait()
    println("Uzimas svoj ${Player.weapon.type} i krećeš na neprijatelja")
    println("Borba započinje")
    println("Neprijatelj vas udara mačem")
    Wait()
    Player.Injury()
    Player.PlayerHealth()
    Wait()
    println("Uspjevaš rješiti jednog te tražiš put prema ruševini")
    Wait()
    println("A: Napasti ostale")
    println("B: Pobjeći u ruševinu")
    println("Odaberi!")
    Player.decision.add(readLine() ?: "prazno")
    when(Player.decision.last()){

        "A" -> Samoubojstvo()
        "B" -> Rusevina1()
        else ->
        {
            println("Odustat od svega i pobjeci na treću stranu")
            Pobjec()
            Wait()
        }

    }




}
fun Rusevina1() {


    println("Uspio si se sakrit od neprijatelja i predahnut")
    Player.Rest()
    Wait()
    Player.PlayerHealth()
    println("Nailaziš na velika drvena vrata")
    Wait()
    println("Otvaraš ih i čuje se jako škripanje")
    Wait()
    println("U tom trenutku se pojavljuje neprijatelj ispred tebe u tami")
    println("Šta ćeš odlučiti?")
    Wait()
    println("A: Izvući mač i napasti ga!")
    println("B: Zatvorit vrata i pobjeći")
    Player.decision.add(readLine() ?: "prazno")
    when(Player.decision.last()){
        "A" -> {
            val Enemies : Array<Warior> = Array(3){ Warior() }

            Player.weapon = Sword()
            Player.health
            Player.PlayerHealth()
            println("Neprijatelj izvlači mač")
            Wait()
            println("Krećeš u napad!")
            Wait()
            dValue = dice()
            when{
                dValue<4 -> {
                    println("Napadaš jednom")
                    Enemies[0].firstPlayer(Enemies[0].damage)
                    Wait()
                    println("Neprijatelj blokira napad")
                    Enemies[1].firstPlayer((Enemies[1].damage))
                    Wait()
                    println("Bacaš ga na pod i ideš ga dokrajčiti")
                    Enemies[2].firstPlayer(Enemies[2].damage)
                    Wait()
                    Player.PlayerHealth()
                }

            }
            println("Prvi put se izmiče od udarca mača")
            Wait()
            Player.ultyAttack = true
            println("Dobio si dodatne snage da ga rješiš")
            Wait()
            for(enemy in Enemies) Player.enemyPlayer(Player.weapon.damage,enemy)
            println("Ubijaš ga")
            Wait()
            Player.PlayerHealth()
            Secound()
        }
        "B" -> {
            Pobjec()}

        else->{
            println("Niste dobro odabrali! Pokuštaje ponovno")
            Wait()
            Rusevina1()
            println("")
        }
    }

}
fun Samoubojstvo() {

    Player.PlayerHealth()
    println("Ostala su jos dva neprijatelja i krenula su na tebe")
    println("Jednog uspiješ riješit dok te drugi ubije")
    Wait()
    Player.Injury()
    Player.PlayerHealth()
    println("Igra je gotova. Umro si!")
    println("YOU LOSE")
    Wait()
    println()
    first()

}

fun Pobjec() {
    println("YOU LOSE")
    println("Vraćate se na početak igre")
    Wait()
    first()
}

fun Wait(time: Long = 2000){
    Thread.sleep(time)
}