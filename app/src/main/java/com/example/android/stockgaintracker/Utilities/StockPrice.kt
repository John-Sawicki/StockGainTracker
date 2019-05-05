package com.example.android.stockgaintracker.Utilities

import android.util.Log

class StockPrice{
    companion object {
        fun getStockPrices(stockNames: Array<String>){
            val tag = "StockPriceClass"
            var jsonResult :Array<StockInfo>
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
        }
    }
}