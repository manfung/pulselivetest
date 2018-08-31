package com.tephra.mc.pulselivetest.di.modules

import com.tephra.mc.latestnews.data.db.ArticleDao
import com.tephra.mc.latestnews.data.repository.NewsApiService
import com.tephra.mc.latestnews.data.repository.INewsRepo
import com.tephra.mc.latestnews.data.repository.NewsRepo
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    internal fun provideNewsRepo(newsApiService: NewsApiService, articlesDao: ArticleDao): INewsRepo {
        return NewsRepo(newsApiService, articlesDao)
    }

}
