package com.example.emotela_pj

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.emotela_pj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences :  SharedPreferences
    var isRemebered = false
    private lateinit var binding : ActivityMainBinding
   // private lateinit var textView: TextView
    private lateinit var forgotPassword : TextView
    private lateinit var signIn : Button
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

//        textView = findViewById(R.id.signUp)
        forgotPassword = findViewById(R.id.forgotPass)
//        textView.setOnClickListener{
//            val it = Intent(this,Signup::class.java)
//            startActivity(it)
//
//        }
        signIn = findViewById(R.id.signin_btn)
        signIn.setOnClickListener {
            val emailId : String = binding.emailId.text.toString()
            val password : String =binding.password.text.toString()
            val checked : Boolean = binding.checkBox.isChecked
            //Editor to save login info
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("EMAIL_ID",emailId)
            editor.putString("PASSWORD",password)
            editor.putBoolean("CHECKBOX",checked)
            editor.apply()

            Toast.makeText(this, "You are logged In successfully",Toast.LENGTH_LONG).show()
            val open = Intent(this, dashboard::class.java)
            startActivity(open)


        }
        forgotPassword =findViewById(R.id.forgotPass)
        forgotPassword.setOnClickListener {
            val fg = Intent(this, forgot_password::class.java)
            startActivity(fg)
        }
        sharedPreferences = getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        isRemebered = sharedPreferences.getBoolean("CHECKBOX", false)

        if(isRemebered){
            val it  = Intent(this, dashboard::class.java)
            startActivity(it)
            finish()
        }
    }
}