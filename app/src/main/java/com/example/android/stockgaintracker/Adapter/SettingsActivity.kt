package com.example.android.stockgaintracker.Adapter

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.example.android.stockgaintracker.R


class SettingsActivity : AppCompatActivity() , SharedPreferences.OnSharedPreferenceChangeListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, CustomSettingFragment())
            .commit()
    }
    class CustomSettingFragment: PreferenceFragmentCompat(){
        override fun onCreatePreferences(p0: Bundle?, p1: String?) {
            addPreferencesFromResource(R.xml.pref_currency)
        }
    }

    override fun onSharedPreferenceChanged(sharedPref: SharedPreferences?, key: String?) {

    }
}
