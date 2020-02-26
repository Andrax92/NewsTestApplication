package com.example.newstestapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert
    fun insertNewArticles(articles: List<NewsApiArticles>): List<Long>

    @Query("SELECT * FROM news_article")
    suspend fun getArticles(): List<NewsApiArticles>

    @Query("SELECT * FROM news_article WHERE id=:id")
    fun getArticlesById(id: Int): NewsApiArticles
}