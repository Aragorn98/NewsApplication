package com.example.newsapp.repositories

import com.example.newsapp.models.Article

interface NewsRepositoryInterface {
    suspend fun getBookmarks(): List<Article>?
    suspend fun insertArticle(article: Article)
}