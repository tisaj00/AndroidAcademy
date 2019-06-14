package model

import weapons.BaseWeapon
import weapons.HealDrone
import weapons.Rifle
import weapons.Shield

object Weapons {

    lateinit var selected:BaseWeapon

    private val weapons:MutableList<BaseWeapon> = mutableListOf(HealDrone,Rifle,Shield)

    fun getWeapon(weaponId:Int):BaseWeapon{
        for(weapon in weapons){
            if(weapon.weaponId() == weaponId){
                selected = weapon
            }
        }
        return selected
    }
}