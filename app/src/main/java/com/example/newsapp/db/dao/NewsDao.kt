package com.example.newsapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.models.Article

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    suspend fun getBookmarks(): List<Article>

    @Insert
    suspend fun insertArticle(article: Article)
}