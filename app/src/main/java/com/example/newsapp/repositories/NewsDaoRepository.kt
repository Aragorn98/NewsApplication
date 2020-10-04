package com.example.newsapp.repositories

import com.example.newsapp.db.dao.NewsDao
import com.example.newsapp.models.Article
import com.example.newsapp.repositories.NewsRepositoryInterface
import kotlinx.coroutines.*

class NewsDaoRepository(private val dataSource: NewsDao) : NewsRepositoryInterface{

    override suspend fun getBookmarks(): List<Article>? {
        return withContext(Dispatchers.IO) {
            dataSource.getBookmarks()
        }
    }

    override suspend fun insertArticle(article: Article) {
        withContext(Dispatchers.IO) {
            dataSource.insertArticle(article)
        }
    }

}