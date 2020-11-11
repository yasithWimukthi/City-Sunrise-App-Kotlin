package com.androidstudio.kotlin.citysunrise

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
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

                publishProgress(inString)
            }
            catch (err:Exception){
                //err.stackTrace
            }
            return ""
        }

        private fun convertStreamToString(inputStream: InputStream?): String{
            val bufferReader = BufferedReader(InputStreamReader(inputStream))
            var line:String
            var allString:String = ""

            try {
                do {
                    line = bufferReader.readLine()
                    if(line != null){
                        allString += line
                    }

                }while (line !=null)
            }catch (err:java.lang.Exception){}
            finally {
                inputStream!!.close()
            }
            return ""
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                var json = JSONObject(values[0])
                val query = json.getJSONObject("query")
                val results = query.getJSONObject("results")
                val channel = results.getJSONObject("channel")
                val astronomy = channel.getJSONObject("astronomy")
                val sunrise = astronomy.getString("sunrise")
                tvSunSetTime.text = ("Sunrise time is : $sunrise")
            }
            catch (err:java.lang.Exception){}
        }

        override fun onCancelled(result: String?) {
            super.onCancelled(result)
        }

        override fun onCancelled() {
            super.onCancelled()
        }
    }
}