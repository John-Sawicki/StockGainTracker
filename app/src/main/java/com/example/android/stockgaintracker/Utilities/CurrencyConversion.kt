package com.example.android.stockgaintracker.Utilities

import com.google.gson.GsonBuilder

class CurrencyConversion{
    companion object {
        fun getCurrencyCo(rawJson: String, currency: String):Double{

           var mRawJson = rawJson
            var mCurrency = currency
            val gson = GsonBuilder().create()
            gson.fromJson(mRawJson, Rates::class.java)

            return -1.1

        }
    }
}
class Rates(val rates: Rates){

}
class YouTubeEX (val video: List<VideoEx>)
class VideoEx(val id: Int, val name: String)