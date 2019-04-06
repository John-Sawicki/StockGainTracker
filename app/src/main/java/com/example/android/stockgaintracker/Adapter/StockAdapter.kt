package com.example.android.stockgaintracker.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.stockgaintracker.R
import kotlinx.android.synthetic.main.stock_layout.view.*
//https://material.io/tools/color/#!/?view.left=0&view.right=0&primary.color=43A047
/*
class StockAdapter: RecyclerView.Adapter<StockViewHolder> {

    var stockTickers = listOf("ticker", "$89", "1.00%", "Vangaurd Energy Fund")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StockViewHolder {
        val layoutInflater =LayoutInflater.from(parent?.context)
        val row = layoutInflater.inflate(R.layout.stock_layout, parent,false)
        return StockViewHolder(row)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder?.view?.tv_stock_ticker?.text ="xom"
        holder?.view?.tv_stock_price?.text="$90.44"
        holder?.view?.tv_stock_change.text="+1.23%"
        holder?.view?.tv_stock_name.text= "Exxon Mobile"
    }

    override fun getItemCount(): Int {
        //return stockTickers.size
        return 1;
    }
}
    class StockViewHolder(val view: View):RecyclerView.ViewHolder(view){

    }
    */
