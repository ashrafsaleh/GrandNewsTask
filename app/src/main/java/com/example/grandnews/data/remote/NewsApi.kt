package com.example.grandnews.data.remote

import com.example.grandnews.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("/r/kotlin/.json")
    suspend fun getNews() : NewsModel

}