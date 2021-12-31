package com.example.labterminal

import android.app.Service
import android.content.Intent
import android.nfc.Tag
import android.os.IBinder
import android.util.Log
import android.widget.Toast


class MyService : Service() {

     override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "I am started", Toast.LENGTH_LONG).show()
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "I am destoryed", Toast.LENGTH_LONG).show()
        super.onDestroy()
    }
    override fun onBind(p0: Intent?) : IBinder? = null
}