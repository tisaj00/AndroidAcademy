package com.example.tisaj.bmi_calculator


import android.app.Application
import android.content.Context


class BMICalculator : Application() {

    companion object {
        lateinit var ApplicationContext: Context
            private set
    }
    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }




}



