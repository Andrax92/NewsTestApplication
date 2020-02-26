package com.example.newstestapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsApiArticles::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        private var INSTANCE: NewsDatabase? = null
        private const val dbName = "news-db"

        @Synchronized
        fun get(context: Context): NewsDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,
                    NewsDatabase::class.java,
                    dbName)
                    .build()
            }
            return INSTANCE!!
        }

    }
}