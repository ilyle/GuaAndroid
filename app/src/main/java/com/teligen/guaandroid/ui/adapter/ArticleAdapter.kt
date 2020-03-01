package com.teligen.guaandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teligen.guaandroid.R
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.ui.activity.ArticleWebActivity

/**
 * Created by xujie on 2020-02-21.
 * Mail : 617314917@qq.com
 */
class ArticleAdapter constructor(context: Context) :
    ListAdapter<Article, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Article>() {
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.articleId == newItem.articleId
        }

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.articleId == newItem.articleId
        }
    }) {

    private val mContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleHolder).setData(getItem(position))
    }

    inner class ArticleHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mTvArticleChapterName: TextView =
            view.findViewById(R.id.tv_item_article_chapter_name)
        private var mTvArticleTitle: TextView = view.findViewById(R.id.tv_item_article_title)
        private var mTvArticleAuthor: TextView = view.findViewById(R.id.tv_item_article_author)
        private var mTvArticleDate: TextView = view.findViewById(R.id.tv_item_article_date)
        private var mCvArticle: CardView = view.findViewById(R.id.cv_item_article)

        fun setData(article: Article) {
            mTvArticleChapterName.text = article.chapterName
            mTvArticleTitle.text = article.title?.replace(Regex("</?[^>]+>"), "") // 去除Html标记
            mTvArticleAuthor.text =
                if (article.author.isNullOrEmpty()) mContext.getString(R.string.unknown) else article.author
            mTvArticleDate.text = article.niceDate
            mCvArticle.setOnClickListener {
                ArticleWebActivity.startAction(mContext, article.link!!, "", article)
            }
        }
    }

}