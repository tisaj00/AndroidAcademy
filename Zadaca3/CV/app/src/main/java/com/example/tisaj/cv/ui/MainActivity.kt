package com.example.tisaj.cv.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.tisaj.cv.R
import com.example.tisaj.cv.model.User

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newUser()
    }

    private fun newUser() {
        val newUser : User = User()
            newUser.fillUser(newUser)
            displayUser(newUser)

    }

    private fun displayUser(user: User){

        tvIme.text ="Ime:\n${user.myInfo.ime}"
        tvPrezime.text= "Prezime:\n${user.myInfo.prezime}"
        tvDatum.text="Datum rođenja:\n${user.myInfo.datumRodenja}"
        tvAdresa.text="Adresa:\n${user.myInfo.adresa}"
        tvEmail.text="Email:\n${user.myInfo.email}"
        tvMobitel.text="Mobitel:\n${user.myInfo.mobitel}"
        tvOib.text="Oib:\n${user.myInfo.oib}"

        val edukacijaLista = mutableListOf<String>()
        user.Edukacija.forEach{
            edukacijaLista.add(it.edukacijaPocetak)
            edukacijaLista.add(it.edukacijaZavrsetak)
            edukacijaLista.add(it.edukacijaZavrsetak)}
            Lista.adapter=ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                user.Edukacija.flatMap { listOf("\nEdukacija početak: ${it.edukacijaPocetak} \nEdukacija završtek: ${it.edukacijaZavrsetak} \nEdukacija ustanova: ${it.edukacijaUstanova}\n ") })
        }
    }



