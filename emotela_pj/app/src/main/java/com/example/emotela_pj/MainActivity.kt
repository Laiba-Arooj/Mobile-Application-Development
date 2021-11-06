package com.example.emotela_pj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var forgotPassword : TextView
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.signUp)
        forgotPassword = findViewById(R.id.forgotPass)
        textView.setOnClickListener{
            val it : Intent = Intent(this,Signup::class.java)
            startActivity(it)

        }
    }
}