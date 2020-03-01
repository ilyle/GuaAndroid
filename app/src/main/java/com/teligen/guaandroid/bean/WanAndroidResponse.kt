package com.teligen.guaandroid.bean

import com.xiaoqi.guagua.mvp.model.bean.Banner

/**
 * 玩安卓接口通用返回
 *
 * Created by xujie on 2020-02-18.
 * Mail : 617314917@qq.com
 */
class WanAndroidResponse<T> {
    var data: T? = null
    var errorCode: Int? = null
    var errorMsg: String? = null
}