package com.example.newsapp.api

import com.example.newsapp.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("top-headlines?q=apple&apiKey=867a584b6c6e4c5fbad52597553c390a")
    suspend fun getTopHeadlines(@Query("page") pageNumber: Int): Response<News>

    @GET("everything?q=apple&apiKey=867a584b6c6e4c5fbad52597553c390a")
    suspend fun getEverything(@Query("page") pageNumber: Int): Response<News>
}