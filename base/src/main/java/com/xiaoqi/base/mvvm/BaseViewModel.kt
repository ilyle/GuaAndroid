package com.xiaoqi.base.mvvm

import androidx.lifecycle.ViewModel

/**
 * Created by xujie on 2020-02-17.
 * Mail : 617314917@qq.com
 */
abstract class BaseViewModel<T : BaseModel> : ViewModel() {

    var mModel: T

    init {
        mModel = createModel()
    }

    override fun onCleared() {
        super.onCleared()
    }

    abstract fun createModel(): T

    fun getModel(): T {
        return mModel
    }
}