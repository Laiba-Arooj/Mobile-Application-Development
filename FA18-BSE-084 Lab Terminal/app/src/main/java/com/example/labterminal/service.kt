package com.example.labterminal

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import com.example.labterminal.databinding.ActivityServiceBinding

class service : AppCompatActivity() {
    private lateinit var btnStart : Button
    private lateinit var btnStop : Button
    private lateinit var textView : TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        btnStart = findViewById(R.id.sendSmsBtn)
        textView = findViewById(R.id.textView)
        btnStop = findViewById(R.id.btnStop)
        btnStart.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                textView.text = "Service has been started"
            }
        }
        btnStop.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                textView.text = "Service has been stopped"
            }
        }
    }
}