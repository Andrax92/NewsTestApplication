package com.example.newstestapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newstestapplication.db.NewsApiArticles.NewsApiArticles.Column
import com.example.newstestapplication.db.NewsApiArticles.NewsApiArticles.tableName
import com.google.gson.annotations.SerializedName

@Entity(tableName = tableName)
data class NewsApiArticles(
    
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
            
    @ColumnInfo(name = Column.title)
    @SerializedName(Column.title)
    val articleTitle: String? = null,

    @ColumnInfo(name = Column.description)
    @SerializedName(Column.description)
    val articleDescription: String? = null,
    
    @ColumnInfo(name = Column.content)
    @SerializedName(Column.content)
    val articleContent: String? = null,

    @ColumnInfo(name = Column.imgUrl)
    @SerializedName(Column.imgUrl)
    val imgUrl: String? = null,
    
    @ColumnInfo(name = Column.articleDate)
    @SerializedName(Column.articleDate)
    val articleDate: String? = null
)
{
    object NewsApiArticles {
        const val tableName = "news_article"

        object Column {
            const val id = "id"
            const val title = "title"
            const val description = "description"
            const val content = "content"
            const val imgUrl = "imgUrl"
            const val articleDate = "articleDate"
        }
    }
}
