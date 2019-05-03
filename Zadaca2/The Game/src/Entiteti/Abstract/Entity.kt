package Entiteti

import Game.Interaction
import Weapons.Abstract.Weapon
import Weapons.Main.Hands

abstract class Entity(

    var health: Int =20,
    var weapon : Weapon = Hands(),
    var damage:Int = 0

):Interaction