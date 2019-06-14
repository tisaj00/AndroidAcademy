package entities.Champs.Abstract

import weapons.BaseWeapon

abstract class BaseChamp {

    var health:Int = 0
    var mana: Int = 0
    var baseDmg:Int = 0


    lateinit var baseWeaponEquipped: BaseWeapon

    fun equipWeapon(baseWeapon: BaseWeapon){
        baseWeaponEquipped = baseWeapon
    }

    fun dmgDeal():Int{
        val damageWep = (baseWeaponEquipped.MinDmg()..baseWeaponEquipped.MaxDmg()).shuffled().first()
        return damageWep + baseDmg

    }

    abstract fun spell():Int



}