package com.example.android.stockgaintracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class StockDetailActivity : AppCompatActivity() {
    var quantity =0
    var dailyChange=0.0
    var trackerChange = 0.0
    var divYeild = 0.0
    var trackGain =0
    var totalGain = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_detail)
    }
}
