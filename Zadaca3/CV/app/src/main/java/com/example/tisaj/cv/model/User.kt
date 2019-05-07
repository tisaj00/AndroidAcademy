package com.example.tisaj.cv.model

import com.example.tisaj.cv.interaction.Interaction

data class User(

    val myInfo : MyInfo = MyInfo(),
    val Edukacija:MutableList<Edukacija> = mutableListOf()

):Interaction{
    override fun fillUser(user: User) {
        UserInfo(user)
    }
}

private fun UserInfo(user: User){

    user.myInfo.ime = "Josip"
    user.myInfo.prezime = "Tisaj"
    user.myInfo.datumRodenja = "14.12.1994"
    user.myInfo.adresa = "Augusta Cesarca 30"
    user.myInfo.mobitel = "0919340771"
    user.myInfo.email = "jtisaj@etfos.hr"
    user.myInfo.oib="68628389819"

    val osnovnaSkola:Edukacija = Edukacija("2000","2008","Osnovna škola Beli Manastir")
    val srednjaSkola:Edukacija = Edukacija("2008","2012","Prva Srednja škola Beli Manastir")
    val fakultet:Edukacija = Edukacija("2012","još traje","FERIT")
    user.Edukacija.add(osnovnaSkola)
    user.Edukacija.add(srednjaSkola)
    user.Edukacija.add(fakultet)
}