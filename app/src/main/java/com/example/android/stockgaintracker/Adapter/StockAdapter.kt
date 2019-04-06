package com.example.android.stockgaintracker.Adapter

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.stockgaintracker.R
import kotlinx.android.synthetic.main.stock_layout.view.*
//https://material.io/tools/color/#!/?view.left=0&view.right=0&primary.color=43A047

class StockAdapter(private val context: Context): RecyclerView.Adapter<StockAdapter.StockViewHolder>() {
    private var logging= "StockAdapter"

    val stockTickers = listOf("ticker", "$89", "1.00%", "Vangaurd Energy Fund")
    var stockInfo= arrayOf("ticker", "$89", "1.00%", "Vangaurd Energy Fund")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StockViewHolder {
        val layoutInflater =LayoutInflater.from(parent?.context)
        val row = layoutInflater.inflate(R.layout.stock_layout, parent,false)
        return StockViewHolder(row)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        var change =5.5//percent change value
        holder.view.tv_stock_ticker.text ="xom"
        holder.view.tv_stock_price.text="$90.44"
        holder.view.tv_stock_change.text="+1.23%"
        holder.view.tv_stock_change.setBackgroundColor(ChangeColor.backGndColor(context,change ))
        holder.view.tv_stock_change.setTextColor(ChangeTxColor.backGndTxColor(context,change ))
        holder.view.tv_stock_name.text= "Exxon Mobile"
    }
    override fun getItemCount(): Int {
        //return stockTickers.size
        return 1
    }
    class StockViewHolder(val view: View):RecyclerView.ViewHolder(view){
        //tv_stock_ticker =
        //tv_stock_price
        //tv_stock_change
        //tv_stock_name
    }
    fun backGroundColor(context: Context,percent: Double):Int {    //return the background color based on the stock percent change
     @TargetApi(23)
        if (Build.VERSION.SDK_INT < 23) {
            Log.d(logging, "api is less than 22 " + context.getColor(R.color.gainMajor).toString())
            if (percent > 3.0) {
                return context.getColor(R.color.gainMajor)
            } else if (percent > 1.0) {
                return context.getColor(R.color.gainMedium)
            } else if (percent >= 0.0) {
                return context.getColor(R.color.gainMinor)
            } else if (percent >= -1.0) {
                return context.getColor(R.color.loseMinor)
            } else if (percent >= -3.0) {
                return context.getColor(R.color.loseMedium)
            } else {
                return context.getColor(R.color.loseMajor)
            }
        } else {//api 23+, marshmallow+
            Log.d(logging, "api is more than 22 " + ContextCompat.getColor(context, R.color.gainMajor))
            if (percent > 3.0) {
                return ContextCompat.getColor(context, R.color.gainMajor)
            } else if (percent > 1.0) {
                return context.getColor(R.color.gainMedium)
            } else if (percent >= 0.0) {
                return context.getColor(R.color.gainMinor)
            } else if (percent >= -1.0) {
                return context.getColor(R.color.loseMinor)
            } else if (percent >= -3.0) {
                return context.getColor(R.color.loseMedium)
            } else {
                return context.getColor(R.color.loseMajor)
            }
        }
     }
    fun backGroundTextColor(context: Context,percent: Double):Int {    //return the background color based on the stock percent change
        Log.d(logging, "backGroundTextColor start ")
        @TargetApi(23)
        if (Build.VERSION.SDK_INT < 23) {
            Log.d(logging, "api is less than 22 for text ")
            if (percent > 3.0 || percent< -3.0) {
                return context.getColor(android.R.color.white)
            } else {
                return context.getColor(android.R.color.black)
            }
        } else {//api 23+, marshmallow+
            Log.d(logging, "api is more than 22 for text")
            if (percent > 3.0 || percent <-3.0) {
                return ContextCompat.getColor(context, android.R.color.white)
            } else {
                return ContextCompat.getColor(context, android.R.color.black)
            }
        }
    }


}

