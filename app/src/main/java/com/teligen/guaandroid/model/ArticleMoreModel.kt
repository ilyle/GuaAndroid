package com.teligen.guaandroid.model

import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.db.DbInstance
import com.xiaoqi.base.mvvm.BaseModel
import com.xiaoqi.base.mvvm.StateData
import io.reactivex.Observable
import java.lang.Exception

/**
 * Created by xujie on 2020-02-25.
 * Mail : 617314917@qq.com
 */
class ArticleMoreModel : BaseModel() {

    fun addCollection(article: Article, liveData: MutableLiveData<StateData<Boolean>>) {
        val ob = Observable.create<Boolean> { emitter ->
            try {
                DbInstance.instance.getArticleDao().insert(article)
                emitter.onNext(true)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
        observeStateData(ob, liveData)
    }

    fun checkCollection(article: Article, liveData: MutableLiveData<StateData<Boolean>>) {
        val ob = DbInstance.instance.getArticleDao().getAll()
            .map<List<Int?>> { articleList ->
                articleList.map { article -> article.articleId }
            }
            .map<Boolean> {
                it.contains(article.articleId)
            }
        observeStateData(ob, liveData)
    }

    fun removeCollection(article: Article, liveData: MutableLiveData<StateData<Boolean>>) {
        val ob = Observable.create<Boolean> { emitter ->
            try {
                DbInstance.instance.getArticleDao().remove(article)
                emitter.onNext(true)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
        observeStateData(ob, liveData)
    }
}