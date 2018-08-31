package com.tephra.mc.pulselivetest.ui.article

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.tephra.mc.latestnews.data.repository.NewsRepo
import com.tephra.mc.pulselivetest.data.model.Article
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.Date

class ArticleViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var articleViewModel: ArticleViewModel

    @Mock
    lateinit var newsRepository: NewsRepo

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        articleViewModel = ArticleViewModel(newsRepository)

    }

    @Test
    fun testGetArticle() {

        val id = 1
        val title = "title 1"
        val subtitle = "subtitle 1"
        val date = Date()
        val body = "body 1"

        val article = Article(
                id,
                title = title,
                subtitle = subtitle,
                date = date,
                body = body
        )

        var articlesLiveData = MutableLiveData<Article>()
        articlesLiveData.value = article

        Mockito.`when`(newsRepository.getArticle(id)).thenReturn(articlesLiveData)

        val articleWrappedInLiveData = articleViewModel.getArticle(id)
        val articleFromViewModel = articleWrappedInLiveData.value

        assertEquals(articleFromViewModel!!.id , id)
        assertEquals(articleFromViewModel!!.title , title)
        assertEquals(articleFromViewModel!!.subtitle , subtitle)
        assertEquals(articleFromViewModel!!.date , date)
        assertEquals(articleFromViewModel!!.body , body)

    }

}