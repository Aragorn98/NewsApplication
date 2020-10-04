package com.example.newsapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.newsapp.models.Article
import com.example.newsapp.repositories.NewsRepositoryInterface
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NewsViewModel(private val repository: NewsRepositoryInterface) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val mutableArticles = MutableLiveData<List<Article>?>()
    val articles: LiveData<List<Article>?>
        get() = mutableArticles

    val article = Pager(PagingConfig(pageSize = 15)) {
        repository as PagingSource<Int, Article>
    }.flow.cachedIn(viewModelScope)

    fun getBookmarks() {
        launch {
            mutableArticles.postValue(repository.getBookmarks())
        }
    }

    fun insertArticle(article: Article) {
        launch {
            repository.insertArticle(article)
        }
    }
}
