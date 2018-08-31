package app.news.mc.com.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tephra.mc.pulselivetest.R
import com.tephra.mc.pulselivetest.data.model.Article
import com.tephra.mc.pulselivetest.shared.Constants.Companion.APP_DATE_FORMAT
import com.tephra.mc.pulselivetest.ui.newslist.INewsItemOnClickListener
import kotlinx.android.synthetic.main.news_list_item.view.*
import java.text.SimpleDateFormat

class NewsListAdapter(private val articles: List<Article>,
                      private val newsItemOnClickListener:INewsItemOnClickListener) :
        RecyclerView.Adapter<NewsListAdapter.NewsListItemViewHolder>() {

    class NewsListItemViewHolder(view: View, private val newsItemOnClickListener: INewsItemOnClickListener) : RecyclerView.ViewHolder(view) {

        fun bind(article: Article, position: Int) {
            with(itemView) {
                tv_title.text = article.title
                tv_subtitle.text = article.subtitle
                tv_date.text = SimpleDateFormat(APP_DATE_FORMAT).format(article.date).toString()
                setOnClickListener { newsItemOnClickListener.onClick(tv_title, article.id) }
                tv_title.transitionName = context.getString(R.string.tv_transition) + position
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NewsListAdapter.NewsListItemViewHolder {

        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_list_item, parent, false) as View

        return NewsListItemViewHolder(itemView, newsItemOnClickListener)
    }

    override fun onBindViewHolder(holder: NewsListItemViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article, position)
    }

    override fun getItemCount() = articles.size

}
