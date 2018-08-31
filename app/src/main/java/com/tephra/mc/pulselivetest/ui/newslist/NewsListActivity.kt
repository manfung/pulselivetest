package com.tephra.mc.pulselivetest.ui.newslist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.news.mc.com.newsapp.ui.adapter.NewsListAdapter
import com.tephra.mc.pulselivetest.R
import com.tephra.mc.pulselivetest.data.model.Article
import com.tephra.mc.pulselivetest.shared.Constants.Companion.INTENT_ID_KEY
import com.tephra.mc.pulselivetest.ui._base.BaseActivity
import com.tephra.mc.pulselivetest.ui.article.ArticleActivity
import kotlinx.android.synthetic.main.news_list_activity.*
import androidx.core.app.ActivityOptionsCompat

class NewsListActivity : BaseActivity() {

    companion object {
        private var SAVED_LAYOUT_MANAGER_KEY = "SAVED_LAYOUT_MANAGER"
    }

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var listView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private var layoutManagerSavedState: Parcelable? = null

    private var onNewsItemClickListener : INewsItemOnClickListener = object : INewsItemOnClickListener {
        override fun onClick(v: View, id: Int) {
            gotoArticle(v, id)
            layoutManagerSavedState = layoutManager.onSaveInstanceState()
        }
    }

   override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_list_activity)
        setupViewModel()
        initViews()

    }

    private fun setupViewModel() {

        newsListViewModel = ViewModelProviders.of(this, viewModelFactory)[NewsListViewModel::class.java]

        newsListViewModel.getNews().observe(this, Observer<List<Article>> {
            updateList(it!!)
            if (swipeRefreshLayout.isRefreshing) {
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun initViews() {

        listView = rv_list
        layoutManager = LinearLayoutManager(this)
        listView.layoutManager = layoutManager
        swipeRefreshLayout = swipe_refresh_news_list
        swipeRefreshLayout.setOnRefreshListener { refreshNews() }
    }

    private fun refreshNews() {
        newsListViewModel.refreshNews()
    }

    private fun updateList(articles: List<Article>) {
        listView.adapter = NewsListAdapter(articles, onNewsItemClickListener)
        if(!articles.isEmpty() && loading_layout.visibility == VISIBLE) loading_layout.visibility = GONE
        restoreLayoutManagerPosition()
    }

    private fun gotoArticle(v: View, id: Int) {
        val intent = Intent(this, ArticleActivity::class.java)
        intent.putExtra(INTENT_ID_KEY, id)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, getString(R.string.tv_transition))
        startActivity(intent, options.toBundle())
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        // save the list view layout manager current position state
        outState!!.putParcelable(SAVED_LAYOUT_MANAGER_KEY, layoutManagerSavedState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        layoutManagerSavedState = savedInstanceState!!.getParcelable(SAVED_LAYOUT_MANAGER_KEY)
    }

    private fun restoreLayoutManagerPosition() {
        if (layoutManagerSavedState != null) {
            layoutManager.onRestoreInstanceState(layoutManagerSavedState)
        }
    }
}
