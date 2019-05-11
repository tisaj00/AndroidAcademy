package com.example.tisaj.dicerollergame.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.tisaj.dicerollergame.R
import com.example.tisaj.dicerollergame.model.Numbers
import kotlinx.android.synthetic.main.activity_main.*

class DiceRollerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
    }

    private fun setUpUi() {
        btnBacaj.setOnClickListener {
            Numbers.Dices()
            refreshImages()

            if(Numbers.numbersAvailable == 1) setImageListeners()
            if(Numbers.numbersAvailable == 0) Toast.makeText(this,"Nema vise bacanja", Toast.LENGTH_SHORT).show()
        }
        btnBacajPonovno.setOnClickListener { if(Numbers.restartGame) this.recreate()}
    }

    private fun setImageListeners() {

        ivDice01.setOnClickListener  { Numbers.dices[0].selected = true
            Toast.makeText(this,"Prva označena", Toast.LENGTH_LONG).show() }
        ivDice02.setOnClickListener { Numbers.dices[1].selected = true
            Toast.makeText(this,"Druga označena", Toast.LENGTH_LONG).show() }
        ivDice03.setOnClickListener  { Numbers.dices[2].selected = true
            Toast.makeText(this,"Treća označena", Toast.LENGTH_LONG).show() }
        ivDice04.setOnClickListener { Numbers.dices[3].selected = true
            Toast.makeText(this,"Četvrta označena", Toast.LENGTH_LONG).show() }
        ivDice05.setOnClickListener  { Numbers.dices[4].selected = true
            Toast.makeText(this,"Peta označena", Toast.LENGTH_LONG).show() }
        ivDice06.setOnClickListener  { Numbers.dices[5].selected = true
            Toast.makeText(this,"Šesta označena", Toast.LENGTH_LONG).show() }

    }

    fun refreshImages(){
        val imageList: MutableList<ImageView> = mutableListOf(
            ivDice01,ivDice02,ivDice03,ivDice04,ivDice05,ivDice06
        )
        for(image in imageList){
            when(Numbers.dices[imageList.indexOf(image)].value){
                1->image.setImageResource(R.drawable.dice01)
                2->image.setImageResource(R.drawable.dice02)
                3->image.setImageResource(R.drawable.dice03)
                4->image.setImageResource(R.drawable.dice04)
                5->image.setImageResource(R.drawable.dice05)
                6->image.setImageResource(R.drawable.dice06)
            }
        }
    }
}
