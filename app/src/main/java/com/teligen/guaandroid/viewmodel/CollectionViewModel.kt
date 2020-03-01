package com.teligen.guaandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.model.CollectionModel
import com.xiaoqi.base.mvvm.BaseViewModel
import com.xiaoqi.base.mvvm.StateData

/**
 * Created by xujie on 2020-02-24.
 * Mail : 617314917@qq.com
 */
class CollectionViewModel: BaseViewModel<CollectionModel>() {

    override fun createModel() = CollectionModel()

    var mCollectionLiveData = MutableLiveData<StateData<List<Article>>>()

    fun getCollectionArticle() {
        getModel().getCollectionArticle(mCollectionLiveData)
    }
}