package com.example.fyp_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class forgot_password : AppCompatActivity() {
    lateinit var forgotPass: TextView
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        forgotPass = findViewById(R.id.forgotPassword)
        forgotPass.setOnClickListener{
            startActivity( Intent(this, MainActivity::class.java))
        }
    }
}