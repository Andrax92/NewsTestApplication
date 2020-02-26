package com.example.newstestapplication.newsApi

import com.example.newstestapplication.db.NewsApiArticles
import com.example.newstestapplication.utils.Constants
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsApiService {

    companion object {
        val NEWS_URL = "https://newsapi.org/v2/"

        fun getNewsAPIService(): NewsApiService {
            return Retrofit.Builder()
                .baseUrl(NEWS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApiService::class.java)
        }
    }

    @GET("top-headlines?sources=bbc-news&sortBy=publishedAt&apiKey=${Constants.API_KEY}")
    suspend fun getNewFromApi(): NewsApiResponse
}

data class NewsApiResponse(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("source")
    val source: String = "",

    @SerializedName("sortBy")
    val sortBy: String = "",

    @SerializedName("articles")
    val articles: List<NewsApiArticles> = emptyList()
)