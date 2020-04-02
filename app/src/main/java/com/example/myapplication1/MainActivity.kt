package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRegistrar.setOnClickListener{
            ejecutarServicio()
        }
    }
    private fun ejecutarServicio(){
        val nombre = nombre?.text.toString()
        val apellidop = apellidop?.text.toString()
        val apellidom = apellidom?.text.toString()
        val programa = programa?.text.toString()
        val email = email?.text.toString()
        val url = "http://192.168.100.25/app/fun.php"
        val stringRequest = object : StringRequest(Request.Method.POST, url,
        Response.Listener <String>{ response ->
            try{
                val obj = JSONObject(response)
                Toast.makeText(applicationContext,obj.getString("message"), Toast.LENGTH_SHORT).show()

            }catch (e: JSONException){
                e.printStackTrace()
            }
        },
        Response.ErrorListener{volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()}){
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("nombre", nombre)
                params.put("apellidop", apellidop)
                params.put("apellidom",apellidom)
                params.put("programa", programa)
                params.put("email", email)
                return params
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
