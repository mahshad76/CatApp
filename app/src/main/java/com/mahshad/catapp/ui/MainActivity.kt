package com.mahshad.catapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.example.Breed
import com.mahshad.catapp.R
import com.mahshad.catapp.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    
    private val TAG = "TagMainActivity"
    
    //search about bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //search about this
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //search about this
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val call = RetrofitClient.apiService.getBreeds()
        call.enqueue(object: Callback<List<Breed>> {
            override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                Log.i(TAG, "onResponse: $response ")
            }

            override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}", )
            }

        })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}