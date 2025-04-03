package com.mahshad.catapp

import com.mahshad.catapp.data.network.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Replace with your actual API base URL
private const val BASE_URL = "https://api.example.com/"

// Your API Key
private const val API_KEY = "your_api_key_here"

// OkHttp Interceptor to add API key automatically
private val interceptor = Interceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer $API_KEY") // For header-based authentication
        .build()
    chain.proceed(request)
}

// OkHttpClient with interceptor
private val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

// Retrofit Instance
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client) // Use OkHttp client with the interceptor
    .addConverterFactory(GsonConverterFactory.create()) // Gson for JSON parsing
    .build()

// Create API Service
val apiService: ApiService = retrofit.create(ApiService::class.java)
