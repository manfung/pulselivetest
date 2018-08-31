package com.tephra.mc.pulselivetest.ui.article

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tephra.mc.pulselivetest.R
import com.tephra.mc.pulselivetest.data.model.Article
import com.tephra.mc.pulselivetest.shared.Constants
import com.tephra.mc.pulselivetest.shared.Constants.Companion.INTENT_ID_KEY
import com.tephra.mc.pulselivetest.ui._base.BaseActivity
import kotlinx.android.synthetic.main.article_activity.*
import java.text.SimpleDateFormat

class ArticleActivity : BaseActivity() {

    private lateinit var articleViewModel: ArticleViewModel

    companion object {
        const val NO_ID_VALUE = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra(INTENT_ID_KEY, NO_ID_VALUE)
        setContentView(R.layout.article_activity)
        setupViewModel(id)
    }

    private fun setupViewModel(id: Int) {

        articleViewModel = ViewModelProviders.of(this, viewModelFactory)[ArticleViewModel::class.java]
        if (id != NO_ID_VALUE) {
            articleViewModel.getArticle(id).observe(this, Observer<Article> {
                updateUI(it!!)
            })
        } else{
            Toast.makeText(this, R.string.article_activity_id_error, Toast.LENGTH_LONG).show()
        }

    }

    private fun updateUI(article: Article) {

        with(article) {
            tv_title.text = title
            tv_subtitle.text = subtitle
            tv_date.text = SimpleDateFormat(Constants.APP_DATE_FORMAT).format(date).toString()
            tv_body.text = body
        }
    }
}
