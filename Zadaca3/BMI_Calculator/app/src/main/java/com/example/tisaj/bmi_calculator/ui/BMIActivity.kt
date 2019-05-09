package com.example.tisaj.bmi_calculator.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.tisaj.bmi_calculator.R
import com.example.tisaj.bmi_calculator.model.calculatorBMI
import com.example.tisaj.bmi_calculator.model.checkInputs
import com.example.tisaj.bmi_calculator.model.printResults
import kotlinx.android.synthetic.main.activity_main.*


class BMIActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onResume() {
        super.onResume()
        setupUI()
    }

    private fun setupUI() {
        btnCalculator.setOnClickListener {
            if (etWeight.text.isNotEmpty() && etMeters.text.isNotEmpty()) {
                getUserInfo()
            } else {
                Toast.makeText(this, "Obavezan unos podataka", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getUserInfo() {
        when(checkInputs(etMeters, etWeight)){
            true ->{
                calculatorBMI(ResultBMI)
                printResults(TextBMIResult,TextBMi,ImageBMI)
            }
        }
    }




}