package entities.Enemies

import entities.Enemies.Interface.Enemy


object Bangalore: Enemy {

    var health:Int = 150
    val MinDmg:Int = 15
    val MaxDmg:Int = 35

    override fun base(): Int {
        return (MinDmg..MaxDmg).shuffled().first()
    }

    override fun spell(): Int {
        return rollingThunder()
    }

    private fun rollingThunder(): Int {
        return (15..35).shuffled().first()
    }

}