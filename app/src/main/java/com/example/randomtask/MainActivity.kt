package com.example.randomtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.randomtask.databinding.ActivityMainBinding
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val url = "https://www.boredapi.com/api/activity"
    private  var requestQueue: RequestQueue? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        requestQueue = Volley.newRequestQueue(this)

        binding.btnNext.setOnClickListener {

            nextTask()
        }
    }

    private fun nextTask() {

        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response ->try {
            val jsonArray = response.getString("activity")
            binding.taskText.text = jsonArray
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }


    }

