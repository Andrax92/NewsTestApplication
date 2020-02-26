package com.example.newstestapplication.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newstestapplication.R
import com.example.newstestapplication.db.NewsApiArticles
import com.example.newstestapplication.db.NewsDao
import com.example.newstestapplication.db.NewsDatabase
import com.example.newstestapplication.utils.Constants
import kotlinx.android.synthetic.main.activity_selected_report.*

class SelectedArticle : AppCompatActivity() {

    private lateinit var newsDao: NewsDao
    private lateinit var article: NewsApiArticles

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_selected_report)
        newsDao = NewsDatabase.get(application).newsDao()
        val id = intent.getIntExtra(Constants.ARTICLE_KEY, 0)
        getSelectedArticle(id)
        tv_report_title.text = article.articleTitle
        tv_report_content.text = article.articleContent

    }

    fun getSelectedArticle(id: Int) {
        article = newsDao.getArticlesById(id)
    }
}