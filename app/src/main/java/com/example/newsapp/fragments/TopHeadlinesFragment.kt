package com.example.newsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.TopHeadlinesAdapter
import com.example.newsapp.models.Article
import com.example.newsapp.viewModels.NewsViewModel
import kotlinx.android.synthetic.main.top_headlines_fragment.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class TopHeadlinesFragment : Fragment() {

    private lateinit var adapter: TopHeadlinesAdapter
    private var type = "pagingTopHeadlines"
    private val newsViewModel: NewsViewModel by viewModel(qualifier = named(type))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.top_headlines_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        setupTopHeadlinesAdapter()
        loadTopHeadlines()
    }

    private fun setupTopHeadlinesAdapter() {
        topHeadlinesList.layoutManager = LinearLayoutManager(activity)
        adapter = TopHeadlinesAdapter(context!!)
        topHeadlinesList.adapter = adapter
    }

    private fun loadTopHeadlines() {
        lifecycleScope.launch {
            newsViewModel.article.collect {
                adapter.submitData(it)
            }
            while (true) {
                delay(5000)
                adapter.refresh()
            }
        }
    }
}
