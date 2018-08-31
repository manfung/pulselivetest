package com.tephra.mc.pulselivetest.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.tephra.mc.latestnews.data.db.ArticleDao
import com.tephra.mc.latestnews.data.db.ArticlesDatabase
import com.tephra.mc.latestnews.utils.getValueBlocking
import com.tephra.mc.pulselivetest.data.model.Article
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var articleDao: ArticleDao
    lateinit var articlesDatabase: ArticlesDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        articlesDatabase = Room.inMemoryDatabaseBuilder(context, ArticlesDatabase::class.java).build()
        articleDao = articlesDatabase.articleDao()
    }

    @After
    fun tearDown() {
        articlesDatabase.close()
    }

    @Test
    fun testInsertAndRetrievedArticle() {

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

        articleDao.insert(article)

        val articleWrappedInLiveData = articleDao.getArticle(id)
        val articleFromDb = articleWrappedInLiveData.getValueBlocking()
        assertEquals(articleFromDb!!.id, id)
        assertEquals(articleFromDb!!.title, title)
        assertEquals(articleFromDb!!.subtitle, subtitle)
        assertEquals(articleFromDb!!.date, date)
        assertEquals(articleFromDb!!.body, body)

    }

}