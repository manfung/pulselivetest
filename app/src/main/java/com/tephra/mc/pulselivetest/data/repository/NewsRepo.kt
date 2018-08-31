package com.tephra.mc.latestnews.data.repository

import androidx.lifecycle.LiveData
import com.tephra.mc.latestnews.data.db.ArticleDao
import com.tephra.mc.pulselivetest.data.model.Article
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * News Repository module, responsible for handling the news data operations
 */
class NewsRepo @Inject constructor(private val newsApiService: NewsApiService,
                                   private val articlesDao: ArticleDao): INewsRepo {

    override fun getArticle(id: Int): LiveData<Article> {
        getArticleFromApi(id)
        return articlesDao.getArticle(id)
    }

    private fun getArticleFromApi(id: Int) {
        newsApiService.getArticle(id)
                .subscribeOn(Schedulers.io())
                .subscribe { news ->
                    // API call successfully completed. For simplicity I am re-using the same
                    // data model for both the database and API, otherwise can perform a map
                    articlesDao.insert(news!!.item)
                }
    }

    override fun getNews(): LiveData<List<Article>> {
        getNewsFromApi()
        return articlesDao.getAll()
    }

    private fun getNewsFromApi() {

        newsApiService.getNews()
                .subscribeOn(Schedulers.io())
                .subscribe { news ->
                    articlesDao.insertArticles(news.items)
                }
    }

}