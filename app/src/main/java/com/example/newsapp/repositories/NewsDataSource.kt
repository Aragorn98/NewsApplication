package com.example.newsapp.repositories

import androidx.paging.PagingSource
import com.example.newsapp.api.NewsApi
import com.example.newsapp.models.Article

class NewsDataSource(private val newsApi: NewsApi, private val query: String) :
    PagingSource<Int, Article>(), NewsRepositoryInterface {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = when (query) {
                "everything" -> {
                    newsApi.getEverything(currentLoadingPageKey)
                }
                "topHeadlines" -> {
                    newsApi.getTopHeadlines(currentLoadingPageKey)
                }
                else -> null
            }
            val responseArticles = mutableListOf<Article>()
            val articles = response?.body()?.articles ?: emptyList()
            responseArticles.addAll(articles)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseArticles,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override suspend fun getBookmarks(): Nothing? = null

    override suspend fun insertArticle(article: Article) {}

}