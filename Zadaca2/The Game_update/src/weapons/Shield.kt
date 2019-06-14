package weapons


object Shield:BaseWeapon
{
    override fun weaponId(): Int = 3

    override fun MaxDmg(): Int = 35

    override fun WeaponName(): String = "RT-45 Auto"

    override fun WeaponDmg(): Int = 100

    override fun MinDmg(): Int = 20
}