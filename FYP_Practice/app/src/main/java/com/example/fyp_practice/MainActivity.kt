package com.example.fyp_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var forgotPassword : TextView
    //val btn_twitter:Button=findViewById(R.id.twitterBtn)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.signUpBt)
        forgotPassword = findViewById(R.id.fPass)
        textView.setOnClickListener{
            val it : Intent = Intent(this,signup::class.java)
            startActivity(it)

        }
        forgotPassword.setOnClickListener{
            startActivity(Intent(this,forgot_password::class.java))
        }
        //btn_twitter.setOnClickListener{
        //val openTwt = Intent(this, Twitter::class.java)
        //startActivity(openTwt)
        //}


    }
}