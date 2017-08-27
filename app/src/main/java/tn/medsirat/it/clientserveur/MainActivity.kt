package tn.medsirat.it.clientserveur

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var url = "http://api.chehir.tk/app.php"

        btn.setOnClickListener{


            MyAsyncTask().execute(url)
        }



    }
    inner class MyAsyncTask: AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg params: String?): String {
            var result=""
            try {
                val urlConnect= URL(params[0]).openConnection() as HttpURLConnection
                urlConnect.connectTimeout=7000
                urlConnect.connect()
                var bufferReader = BufferedReader(InputStreamReader(urlConnect.inputStream))
                var line:String?
                do{
                    line=bufferReader.readLine()
                    if(line!=null){
                        result+=line
                    }
                }while(line!=null)
                bufferReader.close()
            }catch(ex:Exception){
                Log.v("TEST",ex.message)
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var json = JSONArray(result).get(0) as JSONObject

            Log.v("TEST",json.getString("email"))

        }

    }



}
