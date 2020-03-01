package com.teligen.guaandroid.model

import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.service.WanandroidService
import com.xiaoqi.base.mvvm.StateData
import com.xiaoqi.base.mvvm.BaseModel
import io.reactivex.Observable

/**
 * Created by xujie on 2020-02-21.
 * Mail : 617314917@qq.com
 */
class SuggestionModel : BaseModel() {

    // 当前请求的页数
    private var mCurPage: Int = 0

    // 话题缓存
    private var mSuggestionCache: ArrayList<Article> = arrayListOf()

    fun listArticle(page: Int, forceUpdate: Boolean, cleanCache: Boolean, liveData: MutableLiveData<StateData<ArrayList<Article>>>) {

        mCurPage = page

        // 不更新，不清缓存，缓存不为空，即按Home键返回桌面再进入App情景，此时返回缓存列表
        if (!forceUpdate && !cleanCache && mSuggestionCache.isNotEmpty()) {
            val cache = Observable.just(mSuggestionCache)
            observeStateData(cache, liveData)
        }

        // 更新，不清缓存，缓存不为空，即上拉加载更多情景，此时合并缓存和新数据
        else if (forceUpdate && !cleanCache && mSuggestionCache.isNotEmpty()) {
            // 缓存数据
            val cache = Observable.just(mSuggestionCache)
            // 新请求的数据
            val update = getRetrofitClient().create(WanandroidService::class.java).listArticle(page)
                .flatMap { articleData ->
                    articleData.data?.datas?.let {
                        Observable.just(it)
                    } ?: let {
                        Observable.just(arrayListOf<Article>())
                    }
                }
                .doOnNext { refreshSuggestionCache(false, it) }
            // 合并数据
            val merge = Observable.merge(cache, update)
                .collect({ arrayListOf<Article>() }, { t1, t2 -> t1.addAll(t2!!) })
                .toObservable()
            observeStateData(merge, liveData)
        }

        // 更新，清缓存，即下拉重新请求数据
        else if (forceUpdate && cleanCache) {
            val update = getRetrofitClient().create(WanandroidService::class.java).listArticle(0)
                .flatMap { articleData ->
                    articleData.data?.datas?.let {
                        Observable.just(it)
                    } ?: let {
                        Observable.just(arrayListOf<Article>())
                    }
                }
                .doOnNext { refreshSuggestionCache(true, it) }
            observeStateData(update, liveData)
        }
    }

    /**
     * 刷新文章缓存
     */
    private fun refreshSuggestionCache(cleanCache: Boolean, articleList: ArrayList<Article>) {
        if (cleanCache) {
            mSuggestionCache.clear()
        }
        articleList.let {
            for (article in it) {
                mSuggestionCache.add(article)
            }
        }
    }

    fun getCurPage(): Int {
        return mCurPage
    }
}