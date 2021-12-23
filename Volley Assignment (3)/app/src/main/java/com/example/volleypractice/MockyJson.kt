package com.example.volleypractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class MockyJson : AppCompatActivity() {
    private var btnRequest : Button? = null
    private var mRequestQueue : RequestQueue? = null
    private var mStringRequest : StringRequest? = null
    private val url = "https://run.mocky.io/v3/d147a126-25b6-4392-95fe-773e8355280a"
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mocky_json)
        btnRequest = findViewById<View>(R.id.buttonRequest) as Button
        btnRequest!!.setOnClickListener { sendAndRequestResponse() }
    }
    private fun sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this)

        //String Request initialized
        mStringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Toast.makeText(applicationContext, "Response :$response", Toast.LENGTH_LONG)
                    .show() //display the response on screen
            }
        ) { error -> Log.i(MockyJson.Companion.TAG, "Error :$error") }
        mRequestQueue!!.add(mStringRequest)
    }

    companion object {
        private val TAG = MockyJson::class.java.name
    }

    override fun onStop() {
        super.onStop()
        mRequestQueue?.cancelAll(TAG)
    }
}

