package com.example.newstestapplication.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.newstestapplication.db.NewsApiArticles
import com.example.newstestapplication.db.NewsDao
import com.example.newstestapplication.db.NewsDatabase
import com.example.newstestapplication.newsApi.NewsApiService
import com.example.newstestapplication.repositories.NewsRepository
import com.example.newstestapplication.repositories.NewsRepositoryDefault
import com.example.newstestapplication.ui.State
import java.lang.IllegalArgumentException

class MainActivityViewModel(application: Application) : ViewModel() {

    val newsDao = NewsDatabase.get(application).newsDao()
    private var mRepository = NewsRepositoryDefault.getInstance()
    private val articles: LiveData<State<List<NewsApiArticles>>> = mRepository.getArticles(newsDao, NewsApiService.getNewsAPIService()).asLiveData()

    fun getArticles(): LiveData<State<List<NewsApiArticles>>> = articles
}

class MainActivityViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}

inline fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.apply(observer) })
}