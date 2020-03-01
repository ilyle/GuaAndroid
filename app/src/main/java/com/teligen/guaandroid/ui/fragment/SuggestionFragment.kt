package com.teligen.guaandroid.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.teligen.guaandroid.R
import com.teligen.guaandroid.viewmodel.SuggestionViewModel
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.databinding.FragmentSuggestionBinding
import com.teligen.guaandroid.ui.adapter.ArticleAdapter
import com.xiaoqi.base.mvvm.BaseMvvmFragment

/**
 * Created by xujie on 2020-02-21.
 * Mail : 617314917@qq.com
 */
class SuggestionFragment : BaseMvvmFragment<SuggestionViewModel, FragmentSuggestionBinding>() {

    // 是否首次加载文章
    private var mIsFirstLoad = true

    private lateinit var mArticleAdapter: ArticleAdapter

    override fun getVMClass(): Class<SuggestionViewModel> {
        return SuggestionViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_suggestion
    }

    override fun initView(view: View) {
        super.initView(view)
        mArticleAdapter = ArticleAdapter(requireContext())
        mBinding.rvSuggestion.layoutManager = LinearLayoutManager(activity)
        mBinding.rvSuggestion.adapter = mArticleAdapter
    }

    override fun addObserve() {
        super.addObserve()
        mViewModel.suggestionLiveData.observe(this, Observer { stateData ->
            observeStateLiveData(stateData)
        })
    }

    override fun setListener() {
        super.setListener()
        mBinding.srlSuggestion.setOnRefreshListener { getNewArticleList() }
        mBinding.srlSuggestion.setOnLoadmoreListener { getMoreArticleList() }
    }

    override fun onResume() {
        super.onResume()
        if (mIsFirstLoad) {
            mIsFirstLoad = false
            getNewArticleList()
        } else {
            getCacheArticleList()
        }
    }

    // 从网络获取文章，强制更新，清除缓存
    private fun getNewArticleList() {
        mViewModel.listArticle(0, true, true)
    }

    // 从缓存获取文章，不更新，不清缓存
    private fun getCacheArticleList() {
        mViewModel.listArticle(mViewModel.getCurPage(), false, false)
    }

    // 从网络获取新一页的文章，并添加到缓存中去
    private fun getMoreArticleList() {
        mViewModel.listArticle((mViewModel.getCurPage() + 1), true, false)
    }

    override fun hideLoading() {
        mBinding.srlSuggestion.finishRefresh()
        mBinding.srlSuggestion.finishLoadmore()
    }

    override fun showSuccess(any: Any?) {
        mArticleAdapter.submitList(any as ArrayList<Article>)
    }


    companion object {
        fun newInstance(): SuggestionFragment {
            return SuggestionFragment()
        }
    }
}