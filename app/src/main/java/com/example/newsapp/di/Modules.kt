package com.example.newsapp.di

import com.example.newsapp.api.NewsApi
import androidx.room.Room
import com.example.newsapp.api.NewsService
import com.example.newsapp.repositories.NewsDaoRepository
import com.example.newsapp.db.AppDatabase
import com.example.newsapp.db.dao.NewsDao
import com.example.newsapp.repositories.NewsDataSource
import com.example.newsapp.viewModels.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dbModule = module {
    single { Room.databaseBuilder(androidContext(),
        AppDatabase::class.java, AppDatabase.DB_NAME).build() }

    single { get<AppDatabase>().getNewsDao() }
}

val apiModule = module {
    single { NewsService.newsAPI }
}

val repoModule = module {
    single { NewsDaoRepository(get() as NewsDao) }
    single(named("everything")) { NewsDataSource(get() as NewsApi, "everything") }
    single(named("topHeadlines")) { NewsDataSource(get() as NewsApi, "topHeadlines") }
}

val viewModelModule = module {
    viewModel(named(name = "local")) { NewsViewModel(get() as NewsDaoRepository) }
    viewModel(named(name = "pagingEverything")) { NewsViewModel(get(named("everything")) as NewsDataSource) }
    viewModel(named(name = "pagingTopHeadlines")) { NewsViewModel(get(named("topHeadlines")) as NewsDataSource) }
}