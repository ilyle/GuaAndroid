package com.teligen.guaandroid.model

import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.db.DbInstance
import com.xiaoqi.base.mvvm.BaseModel
import com.xiaoqi.base.mvvm.StateData

/**
 * Created by xujie on 2020-02-24.
 * Mail : 617314917@qq.com
 */
class CollectionModel: BaseModel() {

    fun getCollectionArticle(liveData: MutableLiveData<StateData<List<Article>>>) {
        val ob = DbInstance.instance.getArticleDao().getAll()
        observeStateData(ob, liveData)
    }
}