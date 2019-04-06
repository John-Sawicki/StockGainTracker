package com.example.android.stockgaintracker.Adapter

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.Log

class ChangeTxColor{
    companion object {
        private var logging= "ChangeTxColor"
        fun backGndTxColor(context: Context, percent: Double):Int {    //return the background color based on the stock percent change
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
}

