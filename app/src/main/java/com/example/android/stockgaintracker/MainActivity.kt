package com.example.android.stockgaintracker

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.android.stockgaintracker.Adapter.StockAdapter
import com.example.android.stockgaintracker.Utilities.CurrencyConversion
import com.example.android.stockgaintracker.Utilities.CurrencyRates
import com.example.android.stockgaintracker.Utilities.StockPrice
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    val tag ="MainActivityLog"
    var currencyName = "USD"
    var returnJson = "USD"
    var currencyCoeff = 1.0 //for USD
    var stockTestArray= arrayOf("XOM", "AAPL", "LNG")
    //TODO add currency api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //rv_stocks.layoutManager = LinearLayoutManager(this)
        rv_stocks.adapter = StockAdapter(applicationContext)
        Log.d(tag, "onCreate")
        setUpPreferences()
        currencyCoeff =CurrencyRates.fetchCurrencyJson(currencyName)   //return all major currency values
        Log.d(tag, "raw json $returnJson")
        //currencyCoeff = CurrencyConversion.getCurrencyCo("hi", currencyName)
        //currencyCoeff = CurrencyConversion.getCurrencyCo(returnJson, currencyName)  //return the conversion rate from USD to user's choice


        StockPrice.getStockPrices(stockTestArray)   //static values for testing the class
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_act_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemClicked = item?.itemId
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
          val currencyChanged = sharedPref?.getString( resources.getString(R.string.pref_key_currency), "UDS")
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
