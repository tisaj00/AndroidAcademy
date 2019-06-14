package entities.Enemies

import entities.Enemies.Interface.Enemy

object Caustic: Enemy {

    var health:Int = 200
    val MinDmg:Int = 15
    val MaxDmg:Int = 25


    override fun base(): Int {
        return (MinDmg..MaxDmg).shuffled().first()
    }

    override fun spell(): Int {
        return noxGasGrenade()
    }

    private fun noxGasGrenade():Int{
        return (10..25).shuffled().first()
    }

}