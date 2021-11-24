package com.example.emotela_pj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Signup : AppCompatActivity() {
    private lateinit var textView : TextView
    private lateinit var sign_up : Button
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        textView = findViewById(R.id.signIn)
        textView.setOnClickListener {
            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
        }
        sign_up = findViewById(R.id.signUp)
        sign_up.setOnClickListener {
            val open = Intent(this, dashboard::class.java)
            startActivity(open)
        }
    }
}