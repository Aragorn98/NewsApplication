package com.example.newsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.viewModels.NewsViewModel
import com.example.newsapp.R
import com.example.newsapp.adapters.EverythingAdapter
import kotlinx.android.synthetic.main.everything_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class EverythingFragment : Fragment() {

    private lateinit var adapter: EverythingAdapter
    private var isFirstResponse = true
    private var type = "pagingEverything"
    private val newsViewModel: NewsViewModel by viewModel(qualifier = named(type))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.everything_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        setupEverythingAdapter()
        loadEverything()

        swipeContainer.setOnRefreshListener {
            loadEverything()
        }
    }

    private fun setupEverythingAdapter() {
        everythingList.layoutManager = LinearLayoutManager(activity)
        adapter = EverythingAdapter(context!!)
        everythingList.adapter = adapter
    }

    private fun loadEverything() {
        if (isFirstResponse) {
            lifecycleScope.launch {
                newsViewModel.article.collect {
                    adapter.submitData(it)
                }
            }
            isFirstResponse = false
        } else {
            adapter.refresh()
            swipeContainer.isRefreshing = false
        }
    }
}
