package com.example.labterminal

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.telephony.SmsManager.getDefault
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.labterminal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var nameArray:ArrayList<String>
    private lateinit var regNoArray:ArrayList<String>
    private lateinit var phoneArray:ArrayList<Int>
    private val url = "https://run.mocky.io/v3/b8402fc5-ae31-4d98-bced-b5b3fede6d06"
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind = ActivityMainBinding.inflate(layoutInflater)

        val root = bind.root
        setContentView(root)
        val recView = bind.recyclerView

        val queue= Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { res->
                nameArray = ArrayList()
                regNoArray = ArrayList()
                phoneArray = ArrayList()
                for (i in 0 until res.length()) {
                    val jsonObject = res.getJSONObject(i)
                    val name = jsonObject.optString("name")
                    val regNo = jsonObject.optString("reg-no")
                    val quantity = jsonObject.optString("phoneNo").toInt()
                    nameArray.add(name)
                    regNoArray.add(regNo)
                    phoneArray.add(quantity)
                }

                var adapter1 = ItemAdapter(nameArray,regNoArray)
                recView.adapter= adapter1
                adapter1.setOnItemClickListener(object:ItemAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        send_SMS(position)
                    }
                })
                recView.layoutManager = GridLayoutManager(this,1)

            },
            { err->
                Log.d("error-array",err.toString())
            })
        queue.add(jsonArrayRequest)

    }

    private fun send_SMS(position: Int) {
        val sendAction="org.example.labterminal.SENT_ACTION"
        val delAction="org.example.DELIVERY_ACTION"
        /*PendingIntents for Sent and Delivery*/
        val sentIntent = PendingIntent.getBroadcast(this,
            100,
            Intent(sendAction), 0)

        val deliveryIntent = PendingIntent.getBroadcast(this, 200, Intent(delAction), 0)
        val sentRecvr=object: BroadcastReceiver()
        {
            override fun onReceive(p0: Context?, p1: Intent?) {
                Log.d("SMS ", "sent")
            }

        }
        val it= IntentFilter(sendAction)
        registerReceiver(sentRecvr, it)

        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.d("SMS ", "delivered")
            }
        }, IntentFilter(delAction))
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)== PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)

        {
            var sms= getDefault()
            var tNumb = "(650) 555-1313"

            sms.sendTextMessage(tNumb,
                "900294",
                "Got the phone number",
                sentIntent,deliveryIntent)
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),111)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==111)
        {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                receiveMSG()
            }
            else
            {
                Toast.makeText(applicationContext,"No permissions granted", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun receiveMSG() {
        val recvBroadcast: BroadcastReceiver =object: BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                for (sms in Telephony.Sms.Intents.getMessagesFromIntent(p1))
                {
                    Log.d("address", sms.originatingAddress.toString())
                    Log.d("body",sms.displayMessageBody)
                }
            }

        }
        /*register receiver for Received SMS*/
        val it = IntentFilter("android.provider.Telephony.SMS_RECEIVED").apply {
            registerReceiver(recvBroadcast, this)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Destroyed Activity", Toast.LENGTH_LONG).show()
    }


}
