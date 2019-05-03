package Entiteti.Enemies

import Entiteti.Entity
import Entiteti.Player
import Weapons.Abstract.Weapon
import Weapons.Main.Bow

class Hunter (
    health:Int = 40,
    weapon: Weapon = Bow()

):Entity(health,weapon) {
    override fun enemyPlayer(ammount: Int, entitet: Entity) {
        health-=ammount
    }

    override fun firstPlayer(ammount: Int) {
        Player.health -= ammount
    }
}