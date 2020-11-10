package com.androidstudio.kotlin.citysunrise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun getSunset(view:View){
        var city = cityName.text.toString()
        val url = "https://weather-ydn-yql.media.yahoo.com/forecastrss?location="+ city +",ca&format=json" ;
    }
}