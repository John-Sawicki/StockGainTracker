package com.example.android.stockgaintracker

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
//import android.support.v7.widget.LinearLayoutManager
import android.util.Log
//import com.example.android.stockgaintracker.Adapter.StockAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //rv_stocks.layoutManager = LinearLayoutManager(this)
       // rv_stocks.adapter = StockAdapter()
        backGroundColor(0.1)
    }

    fun backGroundColor(percent: Double):Int{    //return the background color based on the stock percent change
        var colorTest = ContextCompat.getColor(applicationContext, R.color.gainMajor)
        var colorTest2 = ContextCompat.getColor(applicationContext, R.color.gainMedium)

        Log.d("colorASDF", colorTest.toString()+" "+colorTest2.toString())

        @TargetApi(23)
        if(Build.VERSION.SDK_INT>21){
            Log.d("colorASDF", getColor(R.color.gainMajor).toString())
            if(percent>3.0){
                return getColor(R.color.gainMajor)
            }else if (percent>1.0){
                return getColor(R.color.gainMedium)
            }else if (percent>=0.0){
                return getColor(R.color.gainMinor)
            }else if (percent>=-1.0){
                return getColor(R.color.loseMinor)
            }else if (percent>=-3.0){
                return getColor(R.color.loseMedium)
            }else{
                return getColor(R.color.loseMajor)
            }
        }else {
        }
        return -1
    }
}
