package com.androidstudio.kotlin.citysunrise

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun getSunset(view:View){
        var city = cityName.text.toString()
        val url = "https://weather-ydn-yql.media.yahoo.com/forecastrss?location="+ city +",ca&format=json" ;

        getData().execute(url)
    }

    inner class getData: AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {

            try{
                val url = URL(params[0])
                val urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout = 30000

                var inString = convertStreamToString(urlConnect.inputStream)
            }
            catch (err:Exception){
                //err.stackTrace
            }
        }

        private fun convertStreamToString(inputStream: InputStream?): Any {

        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
        }

        override fun onCancelled(result: String?) {
            super.onCancelled(result)
        }

        override fun onCancelled() {
            super.onCancelled()
        }
    }
}