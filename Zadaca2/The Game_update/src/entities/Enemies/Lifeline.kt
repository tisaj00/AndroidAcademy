package entities.Enemies

import entities.Enemies.Interface.Enemy

object Lifeline: Enemy {

    var health:Int = 100
    val MinDmg:Int = 15
    val MaxDmg:Int = 20


    override fun base(): Int {
        return (MinDmg..MaxDmg).shuffled().first()
    }

    override fun spell(): Int {
        return carePackage()
    }

    private fun carePackage():Int{
        return (10..20).shuffled().first()
    }
}