package com.teligen.guaandroid.model

import androidx.lifecycle.MutableLiveData
import com.teligen.guaandroid.bean.WanAndroidResponse
import com.teligen.guaandroid.service.WanandroidService
import com.xiaoqi.base.mvvm.BaseModel
import com.xiaoqi.base.mvvm.StateData
import com.xiaoqi.guagua.mvp.model.bean.Banner

/**
 * Created by xujie on 2020-02-18.
 * Mail : 617314917@qq.com
 */
class MainModel : BaseModel() {

    // 获取 banner列表
    fun getBannerList(): MutableLiveData<StateData<WanAndroidResponse<List<Banner>>>> {
        val liveData = MutableLiveData<StateData<WanAndroidResponse<List<Banner>>>>()
        return observeStateData(getRetrofitClient().create(WanandroidService::class.java).getBanner(), liveData)
    }
}