package com.example.android.stockgaintracker.Utilities

import android.util.Log
import okhttp3.*
import java.io.IOException

class CurrencyRates{
    companion object {
        fun fetchCurrencyJson():String{
            val tag ="CurrencyRatesClass"
            var jsonResult = "USD"
            Log.d(tag, "fetchJson start")
            println("fetch json start")  //info message
            val url = "http://data.fixer.io/api/latest?access_key=0b8f3a332e80c3bf5bb94776ad121890"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call, response: Response) {
                    val rawJson = response.body()?.string()
                    Log.d(tag, "success json $rawJson")
                    if(rawJson!=null){
                        jsonResult = rawJson
                    }
                }
                override fun onFailure(call: Call, e: IOException) {
                    println("json fetch failed for stock values")  //info message, the dollar is the universal currency
                }
            })
            return jsonResult   //
        }
    }
}