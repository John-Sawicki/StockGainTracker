package com.example.android.stockgaintracker.Adapter

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.android.stockgaintracker.R

class ChangeColor{

    companion object {
        private var logging= "ChangeColor"
        fun backGndColor(context: Context, percent: Double):Int {    //return the background color based on the stock percent change
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
    }


}