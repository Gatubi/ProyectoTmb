package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyectotmb.ActivityImc
import com.example.proyectotmb.ActivityTmb
import com.example.proyectotmb.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnImc: Button
    private lateinit var btnTmb: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        initListener()
    }

    fun initComponent(){
        btnImc = findViewById(R.id.btnImc)
        btnTmb = findViewById(R.id.btnTmb)
    }

    fun initListener(){
        btnImc.setOnClickListener { toImc() }
        btnTmb.setOnClickListener { toTmb() }
    }

    private fun toImc(){
        val intent = Intent(this, ActivityImc::class.java)
        startActivity(intent)
    }

    private fun toTmb(){
        val intent = Intent(this, ActivityTmb::class.java)
        startActivity(intent)
    }
}