package com.teligen.guaandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.model.SearchModel
import com.xiaoqi.base.mvvm.BaseViewModel
import com.xiaoqi.base.mvvm.StateData

/**
 * Created by xujie on 2020-02-29.
 * Mail : 617314917@qq.com
 */
class SearchViewModel: BaseViewModel<SearchModel>() {

    override fun createModel(): SearchModel = SearchModel()

    var mSearchContentLiveData = MutableLiveData<ArrayList<Article>>()

    var mSearchHistoryLiveData = MutableLiveData<StateData<ArrayList<Article>>>()

    fun queryArticle(query: String, cleanCache: Boolean) {
        getModel().queryArticle(query, cleanCache, mSearchContentLiveData)
    }
}