package com.mahshad.catapp.data.network

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitClient {

    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    private val retrofit: Retrofit by lazy {
        val gson = GsonBuilder()
            .setLenient() // Handle potentially malformed JSON (use with caution in production)
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}

private const val API_KEY = "live_Q06ZqwYdHC5RoGxVTZToeLdQiPrdoY27E1FEVmOSNFCzoUup2yQAfq87Kd9L8jmn"

interface ApiService {
    @GET("breeds?api_key=$API_KEY")
    fun getCats(): Call<Cat>
}

data class Cat (
    val name: String
)


//val userService: ApiService by lazy {
//    retrofit.create(UserService::class.java)
//}

val test: (Int) -> Int = {
    it * 2
}