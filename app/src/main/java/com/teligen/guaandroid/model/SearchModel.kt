package com.teligen.guaandroid.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.bean.ArticleData
import com.teligen.guaandroid.service.WanandroidService
import com.xiaoqi.base.mvvm.BaseModel
import com.xiaoqi.base.mvvm.StateData
import io.reactivex.Observable
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by xujie on 2020-02-29.
 * Mail : 617314917@qq.com
 */
class SearchModel : BaseModel() {

    // 当前请求的页数
    private var mCurPage: Int = 0

    // 话题缓存
    private var mSearchCache: ArrayList<Article> = arrayListOf()

    fun queryArticle(query: String, cleanCache: Boolean, liveData: MutableLiveData<ArrayList<Article>>) {

        // 清缓存
        if (cleanCache) {
            mCurPage = 0

            val ob = getRetrofitClient().create(WanandroidService::class.java)
                .queryArticle(mCurPage, query)
                .map { articleData: ArticleData ->
                    articleData.data?.datas?.let { it } ?: arrayListOf()
                }
                .doOnNext { refreshSearchCache(true, it) }

            observe(ob, liveData)
        }

        // 不清缓存
        else {
            mCurPage++

            val cache = Observable.just(mSearchCache)

            val update = getRetrofitClient().create(WanandroidService::class.java)
                .queryArticle(mCurPage, query)
                .map { articleData: ArticleData ->
                    articleData.data?.datas?.let { it } ?: arrayListOf()
                }
                .doOnNext { refreshSearchCache(false, it) }

            val merge = Observable.merge(cache, update)
                .collect({ arrayListOf<Article>() }, { t1, t2 -> t1.addAll(t2) })
                .toObservable()

            observe(merge, liveData)
        }


    }

    private fun refreshSearchCache(clean: Boolean, articleList: ArrayList<Article>) {
        if (clean) {
            mSearchCache.clear()
        }
        mSearchCache.addAll(articleList)
    }
}