package com.example.newsapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.example.newsapp.viewModels.NewsViewModel
import kotlinx.android.synthetic.main.activity_article_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class ArticleDetailsActivity : AppCompatActivity() {

    companion object {
        lateinit var article: Article
        fun start(context: Context, article: Article) {
            this.article = article
            context.startActivity(Intent(context, ArticleDetailsActivity::class.java))
        }
    }
    var type = "local"
    private val newsViewModel: NewsViewModel by viewModel(named(type))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        initUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addToBookmarks) {
            newsViewModel.insertArticle(article)
            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()
        }
        else
            super.onBackPressed()
        return true
    }

    private fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setArticleData()
    }

    private fun setArticleData() {
        author.text = article.author
        publishedAt.text = article.publishedAt
        articleTitle.text = article.title
        description.text = article.description
        content.text = article.content
    }
}
