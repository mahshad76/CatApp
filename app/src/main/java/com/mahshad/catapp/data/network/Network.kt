package com.mahshad.catapp.data.network

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitClient {
    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    private val retrofit: Retrofit by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService = retrofit.create(ApiService::class.java)
}

private const val API_KEY = "live_Q06ZqwYdHC5RoGxVTZToeLdQiPrdoY27E1FEVmOSNFCzoUup2yQAfq87Kd9L8jmn"

interface ApiService {
    @GET("breeds")
    fun getCats(@Query("api_key") apiKey: String = API_KEY): Call<Cat>
}

data class Cat(
    val name: String
)
