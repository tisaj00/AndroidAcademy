package entities.Champs

import entities.Champs.Abstract.BaseChamp
import model.Weapons

class Tank:BaseChamp() {


    init {
        health = 250
        mana = 100
        baseDmg = 25
        equipWeapon(Weapons.getWeapon(3))

    }

    override fun spell(): Int {
        if (mana <= 0) {
            return 0
        }
        return stackArmor()
    }

    private fun stackArmor():Int{
        mana -= 20
        return (20..40).shuffled().first()
    }


}