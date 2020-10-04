package com.example.newsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.activities.ArticleDetails
import com.example.newsapp.models.Article
import kotlinx.android.synthetic.main.layout_item_news.view.*

class EverythingAdapter(private val context: Context) :
    PagingDataAdapter<Article, EverythingAdapter.EverythingViewHolder>(DataDifferentiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EverythingViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_news, parent, false)
    )

    override fun onBindViewHolder(holder: EverythingViewHolder, position: Int) {
        holder.bindPlan(getItem(position)!!)
    }

    inner class EverythingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindPlan(article: Article) {
            itemView.article_title.text = article.title
            itemView.article_title.setOnClickListener {
                ArticleDetails.start(context, article)
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