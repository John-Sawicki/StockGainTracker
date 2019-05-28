package com.example.android.stockgaintracker.Utilities

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class FundPrice{
    companion object {
        fun getStockPrices(fundNames: Array<String> ){
            val tag = "StockPriceClass"
            var jsonResult:ArrayList<StockInfo> = ArrayList()
            var fundTickerUrl = ""
            if(fundNames.lastIndex>0){
                for(fundName in fundNames){
                    fundTickerUrl = fundTickerUrl.plus("$fundName,")
                }
                fundTickerUrl = fundTickerUrl.substring(0, fundTickerUrl.lastIndex)
                Log.d(tag, fundTickerUrl)
            }
            val urlStart = "https://www.worldtradingdata.com/api/v1/mutualfund?symbol="
            val url = urlStart+fundTickerUrl+ApiKeys.wordTradingData
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object: Callback{
                override fun onResponse(call: Call, response: Response) {
                    val rawJson = response.body()?.string()
                    val gson = GsonBuilder().create()
                    val mainFundJson = gson.fromJson(rawJson, MainFundJson::class.java)
                    var oneFundInfo : StockInfo
                    for (fundJsonInfo in mainFundJson.data.indices ){
                        val fundJsonInfoIndex = mainFundJson.data[fundJsonInfo]
                        var fundSymbol = fundJsonInfoIndex.symbol
                        var fundName = fundJsonInfoIndex.name
                        var fundPrice = fundJsonInfoIndex.price
                        var fundChange = fundJsonInfoIndex.change_pct
                        oneFundInfo = StockInfo(fundSymbol, fundName, fundPrice, fundChange)
                        jsonResult.add(oneFundInfo)
                    }
                }
                override fun onFailure(call: Call, e: IOException) {
                    Log.d(tag, "fund json failure")
                }
            })
        }
    }
}
class MainFundJson (val symbols_returned: Int, val data: Array<FundJsonInfos>)
class FundJsonInfos(val symbol: String, val name: String, val price: String, val change_pct: String)