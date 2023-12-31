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
        //Indicador IMC por resultado
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
//        Generador de consejos
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.titulo_dialogo_importante))
        var msg = ""
        val result = tvResult.text.toString()

        when (result) {
            "Bajo peso" -> {
                msg = getString(R.string.mensaje_bajo_peso)
            }
            "Normal" -> {
                msg = getString(R.string.mensaje_normal)
            }
            "Sobrepeso" -> {
                msg = getString(R.string.mensaje_sobrepeso)
            }
            else -> {
                msg = getString(R.string.mensaje_otros)
            }
        }

        builder.setMessage(msg)
        builder.setPositiveButton(getString(R.string.boton_entendido)) { dialog, _ ->
            // Puedes hacer algo cuando el usuario hace clic en el botón "Entendido"
            dialog.dismiss()
        }

        builder.show()
    }
}