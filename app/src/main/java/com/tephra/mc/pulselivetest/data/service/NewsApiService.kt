package com.tephra.mc.latestnews.data.repository


import com.tephra.mc.pulselivetest.data.model.News
import com.tephra.mc.pulselivetest.data.model.SingleNews
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * REST API endpoints
 */
interface NewsApiService {

    /**
     * Retrieve a list of articles
     */
    @GET("/test/native/contentList.json")
    fun getNews(): Single<News>

    /**
     * Get a single article by id
     */
    @GET("/test/native/content/{id}.json")
    fun getArticle(@Path("id")id: Int): Single<SingleNews>

}