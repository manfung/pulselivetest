package com.tephra.mc.pulselivetest.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tephra.mc.latestnews.data.repository.INewsRepo
import com.tephra.mc.pulselivetest.data.model.Article
import javax.inject.Inject

class ArticleViewModel @Inject constructor(private val newsRepository: INewsRepo): ViewModel() {

    fun getArticle(id: Int): LiveData<Article> {
        return newsRepository.getArticle(id)
    }

}
