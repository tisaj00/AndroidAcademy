package com.example.tisaj.bmi_calculator.model


import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tisaj.bmi_calculator.BMICalculator
import com.example.tisaj.bmi_calculator.R


object BMICalc{

    var height:Double = 0.0
    var weight:Double = 0.0
    var Result:Double = 0.0
}

fun calculatorBMI(ResultBMI:TextView){

    BMICalc.Result = BMICalc.weight / (BMICalc.height*BMICalc.height)
    ResultBMI.text = BMICalc.Result.toString()
}


fun printResults(TextBMIResult:TextView,TextBMi:TextView,ImageBMI:ImageView){
    when {
        BMICalc.Result < 18.5f -> {

            TextBMIResult.text = "Mršav"
            TextBMi.text = BMICalculator.ApplicationContext.getString(R.string.resultBMI_UnderweightLong)
            ImageBMI.setImageResource(R.drawable.underweight)

        }
        BMICalc.Result < 24.9f && BMICalc.Result >= 18.5f -> {
            TextBMIResult.text = "Zdrav"
            TextBMi.text = BMICalculator.ApplicationContext.getString(R.string.resultBMI_NormalRangeLong)
            ImageBMI.setImageResource(R.drawable.healthy)

        }
        BMICalc.Result >= 30f -> {
            TextBMIResult.text = "Debeo"
            TextBMi.text = BMICalculator.ApplicationContext.getString(R.string.resultBMI_ObeseLong)
            ImageBMI.setImageResource(R.drawable.obese)

        }
        BMICalc.Result < 29.9f && BMICalc.Result >= 25f ->
        {
            TextBMIResult.text = "Pretio"
            TextBMi.text = BMICalculator.ApplicationContext.getString(R.string.resultBMI_OverweightLong)
            ImageBMI.setImageResource(R.drawable.overweight)

        }
    }
}


fun checkInputs(etMeters: EditText, etWeight: EditText): Boolean {
    when{
        etMeters.text.toString().toDouble() > 2.5 || etMeters.text.toString().toDouble() <= 0f -> {
            Toast.makeText(
                BMICalculator.ApplicationContext,
                BMICalculator.ApplicationContext.getString(R.string.warningForHeight),
                Toast.LENGTH_SHORT
            ).show()
        }
        etWeight.text.toString().toDouble() > 350f || etWeight.text.toString().toDouble() <= 0f -> {
            Toast.makeText(
                BMICalculator.ApplicationContext,
                BMICalculator.ApplicationContext.getString(R.string.warningForWeight),
                Toast.LENGTH_SHORT
            ).show()

        }
        else -> {
            BMICalc.height = etMeters.text.toString().toDouble()
            BMICalc.weight = etWeight.text.toString().toDouble()
            return true
        }

    }
    return false

}


