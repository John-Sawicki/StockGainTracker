package com.example.android.stockgaintracker.Utilities

import android.util.Log
import com.google.gson.GsonBuilder

import okhttp3.*
import java.io.IOException

class CurrencyRates{
    companion object {
        fun fetchCurrencyJson(currencyName: String):Double{
            val tag ="CurrencyRatesClass"
            var jsonResult = "USD"
            var currencyCo =-1.0 //if unable to retrieve currencies, -1 return value to show toast msg and show currency in USD
            Log.d(tag, "fetchJson start")
            println("fetch json start")  //info message
            val url = "http://data.fixer.io/api/latest?access_key=0b8f3a332e80c3bf5bb94776ad121890"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call, response: Response) {
                    val rawJson = response.body()?.string()
                    Log.d(tag, "rawJson $rawJson")
                    val gson = GsonBuilder().create()
                    val maninJson = gson.fromJson(rawJson, MainJson::class.java)
                    val childJson = maninJson.rates
                    Log.d(tag, "base ${maninJson.base}")
                    Log.d(tag, "aed ${childJson.AED}")
                    /*
                    Log.d(tag, "success json $rawJson")
                    if(rawJson!=null){
                        jsonResult = rawJson
                        currencyCo = CurrencyConversion.getCurrencyCo(jsonResult, currencyName)
                        Log.d(tag, "onrepsonse value $currencyCo")
                    }
                    */
                }
                override fun onFailure(call: Call, e: IOException) {
                    println("json fetch failed for stock values")  //info message, the dollar is the universal currency

                }
            })
            Log.d(tag, "return value $currencyCo")
            return currencyCo
        }
    }

}
class MainJson(val base: String, val rates: JsonRates)
class JsonRates(val AED: Double, val AWG: Double)
//class CurrencyClass(val AED: Double, val EUR: Double)