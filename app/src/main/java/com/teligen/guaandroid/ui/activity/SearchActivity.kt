package com.teligen.guaandroid.ui.activity

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.teligen.guaandroid.R
import com.teligen.guaandroid.databinding.ActivitySearchBinding
import com.teligen.guaandroid.ui.adapter.ArticleAdapter
import com.teligen.guaandroid.viewmodel.SearchViewModel
import com.xiaoqi.base.mvvm.BaseMvvmActivity

/**
 * Created by xujie on 2020-02-29.
 * Mail : 617314917@qq.com
 */
class SearchActivity : BaseMvvmActivity<SearchViewModel, ActivitySearchBinding>() {

    private lateinit var mSearchContentAdapter: ArticleAdapter

    override fun getVMClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initView() {
        super.initView()
        mSearchContentAdapter = ArticleAdapter(mContext)
        mBinding.rvSearchContent.adapter = mSearchContentAdapter
        mBinding.rvSearchContent.layoutManager = LinearLayoutManager(mContext)
    }

    override fun setListener() {
        super.setListener()
        mBinding.svSearch.setOnBackClickListener { onBackPressed() }
        mBinding.svSearch.setOnEnterClickListener {
            mViewModel.queryArticle(it, true)
        }
        mBinding.srlSearchContent.setOnRefreshListener {
            mViewModel.queryArticle(mBinding.svSearch.searchText, true)
        }
        mBinding.srlSearchContent.setOnLoadmoreListener {
            mViewModel.queryArticle(mBinding.svSearch.searchText, false)
        }
    }

    override fun addObserve() {
        super.addObserve()
        mViewModel.mSearchContentLiveData.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                hideLoading()
                mSearchContentAdapter.submitList(it)
            }
        })
    }

    override fun hideLoading() {
        mBinding.srlSearchContent.finishRefresh()
        mBinding.srlSearchContent.finishLoadmore()
    }


    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }

}