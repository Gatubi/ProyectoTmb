package com.example.proyectotmb.tmb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.proyectotmb.R
import com.example.proyectotmb.imc.ImcActivity
import com.example.proyectotmb.imc.ResultImcActivity
import com.google.android.material.slider.RangeSlider

class ResultTmbActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvTMB: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_tmb)

        val result: Double = intent.extras?.getDouble(TmbActivity.TMB_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initUI(result: Double) {
        tvTMB.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //Poco o ningun ejercicio
                tvResult.text = getString(R.string.title_underweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.underweight))
                tvDescription.text = getString(R.string.description_underweight)
            }

            in 18.51..24.99 -> { //Ej ligero (1 a 3)
                tvResult.text = getString(R.string.title_normal_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
                tvDescription.text = getString(R.string.description_normal_weight)
            }

            in 25.00..29.99 -> { //Ej moderado (3 a 5)
                tvResult.text = getString(R.string.title_overweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.overweight))
                tvDescription.text = getString(R.string.description_overweight)
            }

            in 30.00..99.00 -> { //Ej fuerte (6 a 7)
                tvResult.text = getString(R.string.title_obesity)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvDescription.text = getString(R.string.description_obesity)
            }
            in 30.00..99.00 -> { //Ej muy fuerte (2 c/d)
                tvResult.text = getString(R.string.title_obesity)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvDescription.text = getString(R.string.description_obesity)
            }

            else -> {//error
                tvTMB.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvTMB = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressed() }
    }
}