package com.example.emotela_pj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var forgotPassword : TextView
    private lateinit var signIn : Button
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.signUp)
        forgotPassword = findViewById(R.id.forgotPass)
        textView.setOnClickListener{
            val it = Intent(this,Signup::class.java)
            startActivity(it)

        }
        signIn = findViewById(R.id.signin_btn)
        signIn.setOnClickListener {
            val open = Intent(this, dashboard::class.java)
            startActivity(open)
        }
        forgotPassword =findViewById(R.id.forgotPass)
        forgotPassword.setOnClickListener {
            val fg = Intent(this, forgot_password::class.java)
            startActivity(fg)
        }

    }
}