package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.activities.ArticleDetailsActivity
import com.example.newsapp.models.Article
import kotlinx.android.synthetic.main.layout_item_news.view.*

class BookmarksAdapter(private val everything: List<Article>)
    : RecyclerView.Adapter<BookmarksAdapter.BookmarksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = BookmarksViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.layout_item_news, parent, false))

    override fun onBindViewHolder(holder: BookmarksViewHolder, position: Int) {
        holder.bindPlan(everything[position])
    }

    override fun getItemCount() = everything.size

    inner class BookmarksViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindPlan(article: Article) {
            itemView.article_title.text = article.title
            itemView.article_title.setOnClickListener {
                ArticleDetailsActivity.start(itemView.context, article)
            }
        }
    }
}