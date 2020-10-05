package com.example.newsapp.api

import com.example.newsapp.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("top-headlines?q=apple&apiKey=a1fb4b6e45e847f6a2d3ea50f46843b0")
    suspend fun getTopHeadlines(@Query("page") pageNumber: Int): Response<News>

    @GET("everything?q=apple&apiKey=a1fb4b6e45e847f6a2d3ea50f46843b0")
    suspend fun getEverything(@Query("page") pageNumber: Int): Response<News>
}