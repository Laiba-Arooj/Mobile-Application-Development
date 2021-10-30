package com.example.fyp_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class signup : AppCompatActivity() {
    private lateinit var textView : TextView
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        textView = findViewById(R.id.signInBt)
        textView.setOnClickListener {
            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
        }
    }
}