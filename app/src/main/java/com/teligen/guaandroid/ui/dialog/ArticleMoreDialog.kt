package com.teligen.guaandroid.ui.dialog

import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.Observer
import com.teligen.guaandroid.viewmodel.ArticleMoreViewModel
import com.teligen.guaandroid.R
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.databinding.DialogArticleMoreBinding
import com.xiaoqi.base.mvvm.BaseMvvmDialog

/**
 * Created by xujie on 2020-02-25.
 * Mail : 617314917@qq.com
 */
class ArticleMoreDialog : BaseMvvmDialog<ArticleMoreViewModel, DialogArticleMoreBinding>() {

    private var mArticle: Article? = null

    override fun getVMClass(): Class<ArticleMoreViewModel> {
        return ArticleMoreViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.dialog_article_more
    }

    override fun initData() {
        super.initData()
        mArticle = arguments?.getParcelable(ARTICLE)
    }

    override fun setListener() {
        super.setListener()
        mBinding.livCollection.setOnLiteItemViewClick {
            mBinding.isCollected?.let {
                if (it) {
                    removeCollection()
                } else {
                    addCollection()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkCollection()
    }

    override fun getStyle(): Int {
        return R.style.Theme_AppCompat_Dialog_FullScreen
    }

    override fun getGravity(): Int {
        return Gravity.BOTTOM
    }

    private fun checkCollection() {
        mArticle?.let { mViewModel.checkCollection(it) }
    }

    private fun addCollection() {
        mArticle?.let { mViewModel.addCollection(it) }
    }

    private fun removeCollection() {
        mArticle?.let { mViewModel.removeCollection(it) }
    }

    override fun addObserve() {
        super.addObserve()
        mViewModel.collectionLiveData.observe(this, Observer { stateData ->
            mBinding.isCollected = stateData.data
        })
    }


    companion object {

        fun newInstance(article: Article): ArticleMoreDialog {
            val bundle = Bundle()
            bundle.putParcelable(ARTICLE, article)
            val fragment = ArticleMoreDialog()
            fragment.arguments = bundle
            return fragment
        }

        const val ARTICLE = "article"
    }
}