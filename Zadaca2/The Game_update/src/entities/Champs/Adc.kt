package entities.Champs

import entities.Champs.Abstract.BaseChamp
import model.Weapons

class Adc:BaseChamp() {


    init {
        health = 170
        mana = 100
        baseDmg = 40
        equipWeapon(Weapons.getWeapon(2))
    }

    override fun spell(): Int {
        if (mana <= 0) {
            return 0
        }
        return burst()
    }

    private fun burst(): Int {
        mana -= 35
        return (25..80).shuffled().first()
    }


}