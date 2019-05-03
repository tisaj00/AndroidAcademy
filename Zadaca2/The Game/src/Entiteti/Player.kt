package Entiteti

import Game.Interaction
import Weapons.Abstract.Weapon
import Weapons.Main.Hands

object Player:Interaction{


    var health:Int = 100
    var weapon:Weapon = Hands()
    var decision: MutableList<String> = mutableListOf()
    var ultyAttack = false
    fun heal(){
        health =100}
    fun PlayerHealth(){
        println("----------")
        when{
            Player.health <=0 -> println("Player health is 0")
            else -> println("Player health $health")
        }
        println("----------")

    }
    fun Injury(){
        health -=50
    }

    fun Rest(){
        health+=25
    }


    override fun firstPlayer(ammount: Int) {
      Injury()
    }

    override fun enemyPlayer(ammount: Int, entitet: Entity) {
        entitet.health -= ammount
    }

    fun ComboAttack(numbersOfAttack:Int,ammount: Int,entitet: Entity){

        Player.enemyPlayer(numbersOfAttack*ammount,entitet)
    }
}
