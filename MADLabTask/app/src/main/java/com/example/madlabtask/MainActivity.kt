package com.example.madlabtask

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var login : Button
    private lateinit var checkBox : CheckBox
    private lateinit var strEmail : String
    private lateinit var strPassword : String
    private lateinit var strCheckBox : String
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        login = findViewById(R.id.btnLogin)
        checkBox = findViewById(R.id.checkBox)
        sharedPreferences = getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()
        checkSharedPreference()
        login.setOnClickListener {
            if (checkBox.isChecked) {
                editor.putString(getString(R.string.checkBox), "True")
                editor.apply()
                strEmail = email.text.toString()
                editor.putString(getString(R.string.email), strEmail)
                editor.commit()
                strPassword = password.text.toString()
                editor.putString(getString(R.string.password), strPassword)
                editor.commit()
            } else {
                editor.putString(getString(R.string.checkBox), "False")
                editor.commit()
                editor.putString(getString(R.string.email), "")
                editor.commit()
                editor.putString(getString(R.string.password), "")
                editor.commit()
            }
        }


    }

    private fun checkSharedPreference() {
        strCheckBox = sharedPreferences.getString(getString(R.string.checkBox), "False").toString()
        strEmail = sharedPreferences.getString(getString(R.string.email), "").toString()
        email.setText(strEmail)
        password.setText(strPassword)
        checkBox.isChecked = strCheckBox == "True"


    }
}
