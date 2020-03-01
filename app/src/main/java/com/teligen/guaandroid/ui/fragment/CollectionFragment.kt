package com.teligen.guaandroid.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.teligen.guaandroid.viewmodel.CollectionViewModel
import com.teligen.guaandroid.R
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.databinding.FragmentCollectionBinding
import com.teligen.guaandroid.ui.adapter.ArticleAdapter
import com.xiaoqi.base.mvvm.BaseMvvmFragment
import com.xiaoqi.base.mvvm.StateData

/**
 * Created by xujie on 2020-02-24.
 * Mail : 617314917@qq.com
 */
class CollectionFragment : BaseMvvmFragment<CollectionViewModel, FragmentCollectionBinding>() {

    private lateinit var mArticleAdapter: ArticleAdapter

    override fun getVMClass(): Class<CollectionViewModel> {
        return CollectionViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_collection
    }

    override fun initView(view: View) {
        super.initView(view)
        mArticleAdapter = ArticleAdapter(requireContext())
        mBinding.rvCollection.layoutManager = LinearLayoutManager(activity)
        mBinding.rvCollection.adapter = mArticleAdapter
    }

    override fun onResume() {
        super.onResume()
        getCollectionArticle()
    }

    private fun getCollectionArticle() {
        mViewModel.getCollectionArticle()
    }

    override fun addObserve() {
        super.addObserve()
        mViewModel.mCollectionLiveData.observe(this, Observer {
            observeStateLiveData(it)
        })
    }

    override fun showSuccess(any: Any?) {
        mArticleAdapter.submitList(any as ArrayList<Article>)
    }

    companion object {
        fun newInstance(): CollectionFragment {
            return CollectionFragment()
        }
    }
}