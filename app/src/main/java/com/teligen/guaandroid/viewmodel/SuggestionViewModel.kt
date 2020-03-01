package com.teligen.guaandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.model.SuggestionModel
import com.xiaoqi.base.mvvm.StateData
import com.xiaoqi.base.mvvm.BaseViewModel

/**
 * Created by xujie on 2020-02-21.
 * Mail : 617314917@qq.com
 */
class SuggestionViewModel : BaseViewModel<SuggestionModel>() {

    override fun createModel()  = SuggestionModel()

    var suggestionLiveData = MutableLiveData<StateData<ArrayList<Article>>>()

    fun listArticle(page: Int, forceUpdate: Boolean, cleanCache: Boolean) {
        getModel().listArticle(page, forceUpdate, cleanCache, suggestionLiveData)
    }

    fun getCurPage(): Int = getModel().getCurPage()


}