package com.tephra.mc.pulselivetest.ui.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tephra.mc.latestnews.data.repository.INewsRepo
import com.tephra.mc.pulselivetest.data.model.Article
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val newsRepository: INewsRepo): ViewModel() {

    private val news: LiveData<List<Article>> = refreshNews()

    fun getNews():LiveData<List<Article>> {
        return news
    }

    fun refreshNews() :LiveData<List<Article>> {
        return newsRepository.getNews()
    }

}
