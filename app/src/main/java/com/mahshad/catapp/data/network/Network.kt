package com.mahshad.catapp.data.network

import com.example.example.Breed
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    private val retrofit: Retrofit by lazy {
        val intercepter = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            addInterceptor(intercepter)
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)

        }.build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService = retrofit.create(ApiService::class.java)
}

private const val API_KEY = "live_Q06ZqwYdHC5RoGxVTZToeLdQiPrdoY27E1FEVmOSNFCzoUup2yQAfq87Kd9L8jmn"

interface ApiService {
    @GET("breeds")
    fun getBreeds(@Query("api_key") apiKey: String = API_KEY): Call<List<Breed>>
}

