package com.teligen.guaandroid.viewmodel

import com.teligen.guaandroid.model.ArticleModel
import com.xiaoqi.base.mvvm.BaseViewModel

/**
 * Created by xujie on 2020-02-20.
 * Mail : 617314917@qq.com
 */
class ArticleViewModel : BaseViewModel<ArticleModel>() {

    override fun createModel(): ArticleModel = ArticleModel()


}