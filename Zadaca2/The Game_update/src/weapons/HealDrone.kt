package weapons


object HealDrone:BaseWeapon {

    override fun weaponId(): Int = 1

    override fun MaxDmg(): Int =  35

    override fun WeaponName(): String = "Heal Drone"

    override fun MinDmg(): Int = 15

    override fun WeaponDmg(): Int = 100
}