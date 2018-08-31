package com.tephra.mc.pulselivetest.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tephra.mc.latestnews.di.ViewModelFactory
import com.tephra.mc.latestnews.di.ViewModelKey
import com.tephra.mc.pulselivetest.ui.article.ArticleViewModel
import com.tephra.mc.pulselivetest.ui.newslist.NewsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    internal abstract fun bindNewsListViewModel(newsListViewModel: NewsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    internal abstract fun bindArticleViewModel(articleViewModel: ArticleViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
