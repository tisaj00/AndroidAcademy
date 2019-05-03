package Levels

import Entiteti.Enemies.Hunter
import Entiteti.Player
import Entiteti.Player.Rest


fun Secound(){
    println()
    println("Ubio si neprijatelja")
    Wait()
    println("U jednom trenutku ti prilaze dva čovijeka koji govore NISMO NEPRIJATELJI!")
    Wait()
    println("Govore da su bili zarobljeni u ruševini od neprijatelja")
    Player.PlayerHealth()
    Wait()
    println("Nalaze svoje lukove i strijele koji su im oduzeti, te hranu koju ti nude")
    Player.Rest()
    println("Govore kako u ruševinama ima još neprijatelja")
    Wait()
    println("Šta odlučiti")
    println("A: Napasti neprijatelje")
    println("B: Pobjeći iz ruševine s prijateljima")
    Wait()
    Player.decision.add(readLine() ?: "prazno")
    when(Player.decision.last()){

        "A" -> {
            println("")
            Wait()
            Player.PlayerHealth()
            Wait()
            println("Svatko uzima svoje oružje i idete dalje u potragu za neprijateljima")
            Wait()
            println("Dolaze do dijela gdje su neprijatelji koji su glavni u ruševini")
            Wait()
            println("Neprijatelji govore da će vas pobit sve")
            println("Šta ćete odlućit?")
            Wait()
            println("A: Napasti ih")
            println("B: Napustit ruševinu živi")
            Wait()
            Player.decision.add(readLine() ?: "prazno")
            when(Player.decision.last()){

                "A" -> {

                    println("")
                    println("Izvlačite svoja oružja te krećete u napad")
                    Wait()
                    println("Dva prijatelja ostaju iza jer oni imaju luk i strijele")
                    Wait()
                    val Strijelci : Array<Hunter> = Array(4){ Hunter() }
                    Strijelci.forEach { it.firstPlayer(Strijelci[0].weapon.damage) }
                    Wait()
                    println("Strijelci pucaju strijele te ubijaju neprijatelje koji su krenuli na vas")
                    Wait()
                    println("Pogodi vas jedna strijela u nogu i padnete na pod")
                    Player.Rest()
                    Player.PlayerHealth()
                    Wait()
                    println("Izvlačite strijelu i idete na glavnog neprijatelja")
                    Wait()
                    println("Neprijatelj izvlači svoj mač te kreće na vas da se suoči s vama")
                    println("Strijelci ga pogađaju u rame te mu ispada mač iz ruke")
                    Wait()
                    println("Vi ga rušite na pod")
                    Wait()
                    println("Dižete mač i te ga idete ubiti")
                    Player.ultyAttack = true
                    println("Uzimate zadnje trenutke snage")
                    println("Ubijate neprijatelja")
                    Wait()
                    println("Teško ranjeni padate na pod")
                    Wait()
                    println("Strijelci su vam puno pomogli u borbi protiv neprijatelja")
                    for(strijelci in Strijelci) Player.enemyPlayer(Player.weapon.damage,strijelci)
                    Wait()
                    println("Uspjeli ste poraziti sve neprijatelje u ruševini")
                    Player.PlayerHealth()
                    Wait()
                    println("YOU WIN")

                }
                "B" -> {
                    NapustitRusevinu()}

            }


        }
        "B"->{NapustitRusevinu()}
        else -> {
            println("Nista dobro odabrali,pokušajte ponovno")
            Wait()
            Secound()
            println("")
        }



    }


}

fun NapustitRusevinu() {

    println("Kupite sve stvari,odlaze do velikih drvenih vrata")
    println("Napuštate ruševinu i odlazite u šumu")
    Wait()
    println("YOU LOSE")

}
