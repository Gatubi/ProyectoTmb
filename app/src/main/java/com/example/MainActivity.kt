package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    }

    fun initListener(){

    }

    private fun toImc(){

    }

    private fun toTmb(){

    }
}