package com.tephra.mc.pulselivetest.di.modules

import com.tephra.mc.pulselivetest.ui.article.ArticleActivity
import com.tephra.mc.pulselivetest.ui.newslist.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun newsListActivity(): NewsListActivity

    @ContributesAndroidInjector
    abstract fun articleActivity(): ArticleActivity


}