package com.example.proyectotmb.tmb
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectotmb.R
import com.example.proyectotmb.tmb.TmbActivity.Companion.TMB_KEY


class ResultTmbActivity : AppCompatActivity() {

    private lateinit var tvTMB: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_tmb)

        val infoIcon = findViewById<ImageView>(R.id.infoIcon)
        infoIcon.setOnClickListener {
            mostrarDialogoInformacion()
        }

        val result: Double = intent.extras?.getDouble(TMB_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initUI(result: Double) {
        tvTMB.text = result.toString()
        tvResult.text =  getString(R.string.title_units)
//      tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
        tvDescription.text = getString(R.string.description_TMB)
    }

    private fun initComponents() {
        tvTMB = findViewById(R.id.tvTMB)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
        tvResult = findViewById(R.id.tvResult)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressed() }
    }

    private fun mostrarDialogoInformacion() {
        //Generador de consejos consejosos
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Importante")
        val protips = resources.getStringArray(R.array.protips_tmb)
        val msg = protips.random()
        builder.setMessage(msg)
        builder.setPositiveButton("Entendido") { dialog, _ ->
            // Puedes hacer algo cuando el usuario hace clic en el botón "Entendido"
            dialog.dismiss()
        }
        builder.show()
    }
}