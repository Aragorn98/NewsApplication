package com.example.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.BookmarksAdapter
import com.example.newsapp.models.Article
import com.example.newsapp.viewModels.NewsViewModel
import kotlinx.android.synthetic.main.activity_bookmarks.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class BookmarksActivity : AppCompatActivity() {

    private var type = "local"
    private val newsViewModel: NewsViewModel by viewModel(named(type))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarks)

        initUI()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onBackPressed()
        return true
    }

    private fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getBookmarks()
    }

    private fun getBookmarks() {
        newsViewModel.articles.observe(this, Observer { bookmarks ->
            if (!bookmarks.isNullOrEmpty()) {
                setBookmarks(bookmarks)
            } else
                Toast.makeText(this, "There is no bookmarks added yet",
                    Toast.LENGTH_LONG).show()
        })
        newsViewModel.getBookmarks()
    }

    private fun setBookmarks(bookmarks: List<Article>) {
        bookmarksList.layoutManager = LinearLayoutManager(this)
        val adapter = BookmarksAdapter(bookmarks)
        bookmarksList.adapter = adapter
    }

}
