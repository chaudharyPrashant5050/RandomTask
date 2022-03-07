package com.example.randomtask

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
    lateinit var viewModel:ViewModelMain


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


         viewModel = ViewModelProvider(this).get(ViewModelMain::class.java)
        requestQueue = Volley.newRequestQueue(this)
        binding.taskText.text = viewModel.getString()

        binding.btnNext.setOnClickListener {

            nextTask()
        }
    }

    private fun nextTask() {

        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response ->try {
            viewModel.setString(response.getString("activity").toString())
            binding.taskText.text = viewModel.getString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }


    }

