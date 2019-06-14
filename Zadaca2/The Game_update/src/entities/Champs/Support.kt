package entities.Champs

import entities.Champs.Abstract.BaseChamp
import model.Weapons

class Support:BaseChamp(){


    init {
        health = 150
        mana = 100
        baseDmg = 15
        equipWeapon(Weapons.getWeapon(1))
    }

    override fun spell(): Int {
        if (mana <= 0) {
            return 0
        }
        return healing()
    }

    private fun healing(): Int {
        mana -= 15
        return (40..70).shuffled().first()
    }


}