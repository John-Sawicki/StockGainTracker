package com.example.android.stockgaintracker.Utilities

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class CurrencyRates{
    interface OnNewCurrency{
        fun onNewCurrency(cur: kotlin.Double)
    }

    companion object {
        fun fetchCurrencyJson(currencyName: String):Double{
            var mOnCurrencyComplete : OnNewCurrency
            val tag ="CurrencyRatesClass"
            var jsonResult = "USD"
            var currencyCo =-1.0 //if unable to retrieve currencies, -1 return value to show toast msg and show currency in USD
            Log.d(tag, "fetchJson start")
            println("fetch json start")  //info message
            val url = "http://data.fixer.io/api/latest?${ApiKeys.dataFixer}"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call, response: Response) {
                    val rawJson = response.body()?.string()
                    Log.d(tag, "rawJson $rawJson")
                    val gson = GsonBuilder().create()
                    val mainJson = gson.fromJson(rawJson, MainJson::class.java)
                    val childJson = mainJson.rates
                    Log.d(tag, "base ${mainJson.base}")
                    Log.d(tag, "USD ${childJson.USD} CAD ${childJson.CAD}")
                   // mOnCurrencyComplete(childJson.USD)

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
class JsonRates(val USD: Double, val EUR: Double, val JPY: Double, val GBP: Double,
                val AUD: Double, val CAD: Double, val CHF: Double, val CNY: Double,
                val SEK: Double, val NZD: Double)
