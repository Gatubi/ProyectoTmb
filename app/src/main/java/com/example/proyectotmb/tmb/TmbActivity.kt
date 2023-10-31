package com.example.proyectotmb.tmb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.proyectotmb.R
import com.example.proyectotmb.imc.ResultImcActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class TmbActivity : AppCompatActivity() {
    private var isMaleSelected = true
    private var currentHeight: Int = 100
    private var currentWeight: Int = 70
    private var currentAge: Int = 30

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button
    //
    companion object {
        const val TMB_KEY = "TMB_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb2)
        initComponent()
        initListener()
        initUI()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
        val spinner: Spinner = findViewById(R.id.droplist)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.rutina_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }


    private fun initListener() {
        viewMale.setOnClickListener { toggleisMaleGender(true) }

        viewFemale.setOnClickListener { toggleisMaleGender(false) }

        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }

        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            if (currentWeight > 0)
                currentWeight -= 1
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
            btnCalculate.setOnClickListener {
            val result = calculateTMB()
            navigateToResult(result)
            }
    }
//    Hombres	TMB = (10 x peso en kg) + (6,25 × altura en cm) - (5 × edad en años) + 5
//    Mujeres	TMB = (10 x peso en kg) + (6,25 × altura en cm) - (5 × edad en años) - 161
    private fun calculateTMB(): Double {
        val df = DecimalFormat("#.##")
        var tmb = 10 * currentWeight + 6.25 * currentHeight.toDouble() - 5 * currentAge
        if(isMaleSelected){
            tmb += 5
//        val tmb = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
            }
        else {
            tmb += -161
        }
        return df.format(tmb).toDouble()
   }
    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultImcActivity::class.java)
        intent.putExtra(TMB_KEY, result)
        startActivity(intent)
    }

    private fun toggleisMaleGender(isMale: Boolean) {
        val colorSelected = ContextCompat.getColor(this, R.color.background_component_selected)
        val colorDeselected = ContextCompat.getColor(this, R.color.background_component)

        isMaleSelected = isMale
        viewMale.setCardBackgroundColor(if (isMaleSelected) colorSelected else colorDeselected)
        viewFemale.setCardBackgroundColor(if (isMaleSelected) colorDeselected else colorSelected)
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun initUI() {
        setWeight()
        setAge()
    }
}