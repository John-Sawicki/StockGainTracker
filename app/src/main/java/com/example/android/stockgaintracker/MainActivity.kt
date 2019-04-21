package com.example.android.stockgaintracker

import android.annotation.TargetApi
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.android.stockgaintracker.Adapter.SettingsActivity
import com.example.android.stockgaintracker.Adapter.StockAdapter
import com.example.android.stockgaintracker.Utilities.CurrencyRates
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    val tag ="MainActivityLog"
    var currencyName = "USD"
    //TODO add currency api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //rv_stocks.layoutManager = LinearLayoutManager(this)
        rv_stocks.adapter = StockAdapter(applicationContext)
        Log.d(tag, "onCreate")
        //fetchJson()
        CurrencyRates.fetchCurrencyJson()
        setUpPreferences()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_act_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var itemClicked = item?.itemId
        if(itemClicked==R.id.shared_pref){
            startActivity(Intent(this, SettingsActivity::class.java))
        }else if(itemClicked ==R.id.custom_pref){
            startActivity(Intent(this, CustomSettingActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSharedPreferenceChanged(sharedPref: SharedPreferences?, key: String?) {
        Log.d(tag,"onSharedPreferenceChanged")
        if(key.equals(resources.getString(R.string.pref_key_currency))){
          var currencyChanged = sharedPref?.getString( resources.getString(R.string.pref_key_currency), "UDS")
            Log.d(tag,"the currency unit is $currencyChanged")
            setUpPreferences()
       }
    }
   private fun setUpPreferences(){
       Log.d(tag,"setUpPreferences")
       val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
       sharedPref.registerOnSharedPreferenceChangeListener(this)
       currencyName = sharedPref.getString(resources.getString(R.string.pref_key_currency), "USD")
   }
}
