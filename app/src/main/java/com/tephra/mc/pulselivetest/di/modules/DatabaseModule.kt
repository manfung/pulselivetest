package com.tephra.mc.latestnews.di.modules

import android.app.Application
import androidx.room.Room
import com.tephra.mc.latestnews.data.db.ArticleDao
import com.tephra.mc.latestnews.data.db.ArticlesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): ArticlesDatabase {
        return Room
                .databaseBuilder(app, ArticlesDatabase::class.java, "articles.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: ArticlesDatabase): ArticleDao {
        return db.articleDao()
    }

}