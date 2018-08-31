package com.tephra.mc.latestnews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tephra.mc.pulselivetest.data.model.Article

/**
 * The Room Database that contains the Articles table
 */
@Database(entities = [Article::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}