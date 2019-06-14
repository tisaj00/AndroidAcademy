package weapons


object Rifle:BaseWeapon{

    override fun weaponId(): Int = 2

    override fun WeaponDmg(): Int = 100

    override fun MinDmg(): Int = 25

    override fun MaxDmg(): Int = 50

    override fun WeaponName(): String = "Assault Rifle"
}