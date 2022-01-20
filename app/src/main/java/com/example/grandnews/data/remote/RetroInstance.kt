package com.example.grandnews.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {

        private val retrofit by lazy {
            val client = OkHttpClient.Builder().build()
            Retrofit.Builder().baseUrl("https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }
}