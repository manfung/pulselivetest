package com.tephra.mc.latestnews.data.repository

import androidx.lifecycle.LiveData
import com.tephra.mc.pulselivetest.data.model.Article

interface INewsRepo {

    fun getNews(): LiveData<List<Article>>

    fun getArticle(id: Int): LiveData<Article>
}
