package com.teligen.guaandroid.ui.activity

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.teligen.guaandroid.viewmodel.ArticleViewModel
import com.teligen.guaandroid.R
import com.teligen.guaandroid.databinding.ActivityArticleBinding
import com.teligen.guaandroid.ui.adapter.ArticleFragmentAdapter
import com.xiaoqi.base.mvvm.BaseMvvmActivity

/**
 * Created by xujie on 2020-02-20.
 * Mail : 617314917@qq.com
 */
class ArticleActivity : BaseMvvmActivity<ArticleViewModel, ActivityArticleBinding>() {

    override fun getVMClass(): Class<ArticleViewModel> {
        return ArticleViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_article
    }

    override fun initView() {
        super.initView()
        mBinding.vpArticle.adapter =
            ArticleFragmentAdapter(supportFragmentManager, mContext)
        mBinding.vpArticle.offscreenPageLimit = 2
        mBinding.tlArticle.setupWithViewPager(mBinding.vpArticle)

        mBinding.tbArticle.inflateMenu(R.menu.menu_search)

    }

    override fun setListener() {
        super.setListener()
        mBinding.tbArticle.setOnMenuItemClickListener {
            SearchActivity.startAction(mContext)
            true
        }
    }

}