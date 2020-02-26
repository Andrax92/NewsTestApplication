package com.example.newstestapplication.repositories

import com.example.newstestapplication.db.NewsApiArticles
import com.example.newstestapplication.db.NewsDao
import com.example.newstestapplication.newsApi.NewsApiService
import com.example.newstestapplication.ui.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface NewsRepository {

    fun getArticles(newsDao: NewsDao, newsApiService: NewsApiService): Flow<State<List<NewsApiArticles>>>

}

class NewsRepositoryDefault : NewsRepository {

    private lateinit var mNewsDao: NewsDao
    companion object {
        private var INSTANCE: NewsRepositoryDefault? = null
        fun getInstance(): NewsRepositoryDefault {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = NewsRepositoryDefault()
                }
            }
            return INSTANCE!!
        }
    }

    override fun getArticles(newsDao: NewsDao, newsApiService: NewsApiService): Flow<State<List<NewsApiArticles>>> {
        mNewsDao = newsDao
        return flow{
            //Load DB data
            emit(State.success(mNewsDao.getArticles()))
            //Get new News and save in DB
            val news = newsApiService.getNewFromApi()
            mNewsDao.insertNewArticles(news.articles)
            //Load the new news from DB
            emit(State.success(mNewsDao.getArticles()))
        }.catch {
            emit(State.error(it.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }
}