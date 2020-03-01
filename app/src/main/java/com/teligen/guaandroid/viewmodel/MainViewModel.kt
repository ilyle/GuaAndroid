package com.teligen.guaandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.model.MainModel
import com.teligen.guaandroid.bean.WanAndroidResponse
import com.xiaoqi.base.mvvm.StateData
import com.xiaoqi.base.mvvm.BaseViewModel
import com.xiaoqi.guagua.mvp.model.bean.Banner

/**
 * Created by xujie on 2020-02-18.
 * Mail : 617314917@qq.com
 */
class MainViewModel : BaseViewModel<MainModel>() {

    override fun createModel(): MainModel = MainModel()

    fun getBanners(): MutableLiveData<StateData<WanAndroidResponse<List<Banner>>>> {
        return getModel().getBannerList()
    }

}