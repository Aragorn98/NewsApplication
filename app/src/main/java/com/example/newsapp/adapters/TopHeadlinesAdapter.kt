package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.activities.ArticleDetailsActivity
import com.example.newsapp.models.Article
import kotlinx.android.synthetic.main.layout_item_news.view.*

class TopHeadlinesAdapter :
    PagingDataAdapter<Article, TopHeadlinesAdapter.TopHeadlinesViewHolder>(DataDifferentiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopHeadlinesViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_news, parent, false)
    )

    override fun onBindViewHolder(holder: TopHeadlinesViewHolder, position: Int) {
        holder.bindPlan(getItem(position)!!)
    }

    inner class TopHeadlinesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindPlan(article: Article) {
            itemView.article_title.text = article.title
            itemView.article_title.setOnClickListener {
                ArticleDetailsActivity.start(itemView.context, article)
            }
        }
    }

    object DataDifferentiator : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}