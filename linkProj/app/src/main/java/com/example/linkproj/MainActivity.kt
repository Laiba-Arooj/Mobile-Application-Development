package com.example.linkproj

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import javax.xml.transform.ErrorListener

class MainActivity : AppCompatActivity() {
    private lateinit var btnPredict : Button
    private lateinit var edtUrl : EditText
    private lateinit var txtOutput : TextView
    private lateinit var txtEmotion : TextView

    private var url = "https://twtsentiment-fastapi.herokuapp.com/twt_link"
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPredict = findViewById(R.id.btn_predict)
        edtUrl = findViewById(R.id.edt_url)
        txtOutput = findViewById(R.id.txt_output)
        btnPredict.setOnClickListener {
            getSentiment()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getSentiment() {
        val twtlink = edtUrl.text.toString().trim()
        if (twtlink != "") {
            val queue = Volley.newRequestQueue(this)
            if (twtlink.isEmpty()) {
                Toast.makeText(applicationContext, "Please add Country", Toast.LENGTH_LONG).show()
                return
            }

            val request=JsonObjectRequest(Request.Method.GET,
                "https://twtsentiment-fastapi.herokuapp.com/twt_link?link=$twtlink",null,
                { response ->
                    try {
                        val obj : JSONObject = response
                        val sentence : String = obj.getString("sentence")
                        val emotion : String = obj.getString("prediction")

                        txtOutput.append("$sentence\nprediction:$emotion")
                        //txtEmotion.text = obj.getString("prediction")
                        Log.d("Response", obj.getString("sentence"))
                    }
                    catch (e:JSONException){
                        txtOutput.text=e.message

                    }
                },
                {
                        error : VolleyError->
                    txtOutput.text=error.message
                }
                //ErrorListener { txtOutput.text = "That didn't work!" })

            )
            queue.add(request)

        }
    }
}