package com.example.newstestapplication.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.newstestapplication.R
import com.example.newstestapplication.db.NewsApiArticles
import com.example.newstestapplication.ui.activities.SelectedArticle
import com.example.newstestapplication.utils.Constants
import kotlinx.android.synthetic.main.news_view_item.view.*

class NewsAdapter(private var context: Context, private var newsApiArticles: List<NewsApiArticles> = emptyList()) : RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return NewsAdapterViewHolder(layoutInflater.inflate(R.layout.news_view_item, parent, false))
    }

    override fun getItemCount(): Int = newsApiArticles.size

    override fun onBindViewHolder(holder: NewsAdapterViewHolder, position: Int) = holder.bind(newsApiArticles[position])

    fun replaceItems(items: List<NewsApiArticles>) {
        newsApiArticles = items
        notifyDataSetChanged()
    }

    inner class NewsAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(currentArticle: NewsApiArticles) = with(itemView) {
            tv_article_title.text = currentArticle.articleTitle
            tv_article_description.text = currentArticle.articleDescription
            Glide.with(context)
                .load(currentArticle.imgUrl)
                .apply(RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(img_article)

            article_view.setOnClickListener {
                Log.d("Article", currentArticle.toString())
                val articleIntent = Intent(context, SelectedArticle::class.java).putExtra(Constants.ARTICLE_KEY, currentArticle.id)
                articleIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(articleIntent)
            }
        }
    }
}