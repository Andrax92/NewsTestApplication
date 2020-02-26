package com.example.newstestapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstestapplication.R
import com.example.newstestapplication.ui.State
import com.example.newstestapplication.ui.adapters.NewsAdapter
import com.example.newstestapplication.ui.viewmodels.MainActivityViewModel
import com.example.newstestapplication.ui.viewmodels.MainActivityViewModelFactory
import com.example.newstestapplication.ui.viewmodels.observeNotNull

class MainActivity : AppCompatActivity() {


    private lateinit var newsRecyclerView: RecyclerView
    private val newsAdapter by lazy { NewsAdapter(applicationContext, emptyList()) }
    private  val articlesViewModel by lazy { ViewModelProvider(this, MainActivityViewModelFactory(application)).get(MainActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsRecyclerView = findViewById(R.id.news_recycler_view)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.itemAnimator = DefaultItemAnimator()
        newsRecyclerView.adapter = newsAdapter
        articlesViewModel.getArticles().observeNotNull(this) { state ->
            when(state) {
                is State.Success -> newsAdapter.replaceItems(state.data)
                is State.Error -> Toast.makeText(this, "Error loading data: \n${state.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
