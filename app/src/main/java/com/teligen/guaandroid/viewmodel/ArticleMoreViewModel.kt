package com.teligen.guaandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.model.ArticleMoreModel
import com.xiaoqi.base.mvvm.BaseViewModel
import com.xiaoqi.base.mvvm.StateData

/**
 * Created by xujie on 2020-02-25.
 * Mail : 617314917@qq.com
 */
class ArticleMoreViewModel: BaseViewModel<ArticleMoreModel>() {

    override fun createModel(): ArticleMoreModel = ArticleMoreModel()

    var collectionLiveData = MutableLiveData<StateData<Boolean>>()

    fun checkCollection(article: Article) {
        getModel().checkCollection(article, collectionLiveData)
    }

    fun addCollection(article: Article) {
        getModel().addCollection(article, collectionLiveData)
    }

    fun removeCollection(article: Article) {
        getModel().removeCollection(article, collectionLiveData)
    }
}