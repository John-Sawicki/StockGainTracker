package com.example.android.stockgaintracker

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager

import android.util.Log
import com.example.android.stockgaintracker.Adapter.StockAdapter
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    val tag ="MainActivityLog"
    //TODO add currency api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_stocks.layoutManager = LinearLayoutManager(this)
        rv_stocks.adapter = StockAdapter(applicationContext)
        Log.d(tag, "onCreate")
        fetchJson()
    }

    fun fetchJson(){
        Log.d(tag, "fetchJson start")
       println("fetch json start")  //info message
        val url = "http://data.fixer.io/api/latest?access_key=0b8f3a332e80c3bf5bb94776ad121890"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println("success json"+body)

            }
            override fun onFailure(call: Call, e: IOException) {

                println("json fetch failed for stock values")  //info message
            }
        })
    }
}
