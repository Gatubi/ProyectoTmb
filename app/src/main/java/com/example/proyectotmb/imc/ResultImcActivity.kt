package com.example.proyectotmb.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.proyectotmb.R
import com.example.proyectotmb.imc.ImcActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)

        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()

        val infoIcon = findViewById<ImageView>(R.id.infoIcon)
        infoIcon.setOnClickListener {
            mostrarDialogoInformacion()
        }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //Bajo peso
                tvResult.text = getString(R.string.title_underweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.underweight))
                tvDescription.text = getString(R.string.description_underweight)
            }

            in 18.51..24.99 -> { //Peso normal
                tvResult.text = getString(R.string.title_normal_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
                tvDescription.text = getString(R.string.description_normal_weight)
            }

            in 25.00..29.99 -> { //Sobrepeso
                tvResult.text = getString(R.string.title_overweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.overweight))
                tvDescription.text = getString(R.string.description_overweight)
            }

            in 30.00..99.00 -> { //Obesidad
                tvResult.text = getString(R.string.title_obesity)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvDescription.text = getString(R.string.description_obesity)
            }

            else -> {//error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressed() }
    }

    private fun mostrarDialogoInformacion() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Importante")
        var msg = "a"
        var result = tvResult.text
        if(result == "Bajo peso"){
                msg = "Prioriza alimentos nutritivos y calorías saludables. Incluye proteínas magras, grasas saludables y carbohidratos completos en tu dieta."
            }else if(result == "Normal"){
                msg = "Mantén un equilibrio en tu dieta con una variedad de alimentos. La actividad física regular también es clave para mantener la salud."
            }else if(result == "Sobrepeso"){
                msg = "Adopta una dieta balanceada con porciones controladas. Incrementa la actividad física para ayudar en la pérdida de peso."
            }else {
            msg = "Busca asesoramiento médico para desarrollar un plan de pérdida de peso seguro y efectivo. Incorpora cambios en el estilo de vida, como una dieta balanceada y ejercicio regular."
        }
        builder.setMessage(msg)
        builder.setPositiveButton("Entendido") { dialog, _ ->
            // Puedes hacer algo cuando el usuario hace clic en el botón "Entendido"
            dialog.dismiss()
        }
        builder.show()
    }
}