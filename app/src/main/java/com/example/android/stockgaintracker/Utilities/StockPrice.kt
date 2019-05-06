package com.example.android.stockgaintracker.Utilities

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class StockPrice{
    companion object {
        fun getStockPrices(stockNames: Array<String>){
            val tag = "StockPriceClass"
            var jsonResult :ArrayList<StockInfo> = ArrayList()
            var stockTickersUrl =""
            if(stockNames.lastIndex>0){
                for(stockTicker in stockNames){
                    Log.d(tag, "stock name $stockTicker")
                    stockTickersUrl=stockTickersUrl.plus("$stockTicker,")
                    Log.d(tag, "substring $stockTickersUrl")
                }
                stockTickersUrl = stockTickersUrl.substring(0, stockTickersUrl.lastIndex)//remove the comma after the last stock name
                Log.d(tag, "remove comma $stockTickersUrl")
            }
            Log.d(tag, "fetchJson")
            val urlStart ="https://www.worldtradingdata.com/api/v1/stock?symbol="
            val url = urlStart+stockTickersUrl+ApiKeys.wordTradingData
            Log.d(tag, "full url $url")
            val request = Request.Builder().url(url).build()
            val client =  OkHttpClient()
            client.newCall(request).enqueue(object: Callback{
                override fun onResponse(call: Call, response: Response) {
                    val rawJson = response.body()?.string()
                    Log.d(tag, "raw json $rawJson")
                    val gson = GsonBuilder().create()
                    val mainStockJson = gson.fromJson(rawJson, MainStockJson::class.java)
                    Log.d(tag, "tickers returned ${mainStockJson.symbols_returned}")
                    var oneStockInfo:StockInfo
                    for(stockJsonInfo in mainStockJson.data.indices){
                        val stockJsonInfoIndex  = mainStockJson.data[stockJsonInfo]

                        var stockSymbol = stockJsonInfoIndex.symbol
                        var stockName = stockJsonInfoIndex.name
                        var stockPrice = stockJsonInfoIndex.price
                        var stockChange = stockJsonInfoIndex.change_pct
                        oneStockInfo=StockInfo(stockSymbol,stockName,stockPrice,stockChange)
                        jsonResult.add(oneStockInfo)//add four data points from the stock to the array list to return to main activity


                        var checkArrayStock = jsonResult[0]
                        Log.d(tag," for loop ${stockJsonInfoIndex.name} and stock info class ${oneStockInfo.mSymbol} in array list ${checkArrayStock.mSymbol}")
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.d(tag, "stock json failure")
                }
            })

        }
    }
}
class MainStockJson(val symbols_returned: String, val data: Array<StockJsonInfos>)//if there are 3 tickers in the url, there will be three elements in the array
class StockJsonInfos(val symbol: String, val name: String, val price: String, val change_pct: String)