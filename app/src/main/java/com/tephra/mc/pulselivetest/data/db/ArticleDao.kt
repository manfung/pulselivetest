package com.tephra.mc.latestnews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.lifecycle.LiveData
import com.tephra.mc.pulselivetest.data.model.Article

/**
 * Data Access Object for the Articles table
 */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article")
    fun getAll(): LiveData<List<Article>>

    @Query("SELECT * FROM Article WHERE id = :id  ")
    fun getArticle(id:Int): LiveData<Article>

    @Insert(onConflict = REPLACE)
    fun insert(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(repositories: List<Article>)

    @Query("DELETE FROM Article")
    fun deleteAll()

}