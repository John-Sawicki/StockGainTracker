package com.example.android.stockgaintracker

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager

import android.util.Log
import com.example.android.stockgaintracker.Adapter.StockAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_stocks.layoutManager = LinearLayoutManager(this)
        rv_stocks.adapter = StockAdapter(applicationContext)

    }


}
