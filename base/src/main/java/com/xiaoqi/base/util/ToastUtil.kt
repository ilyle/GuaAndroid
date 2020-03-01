package com.xiaoqi.base.util

import com.xiaoqi.base.BaseApplication
import com.xiaoqi.base.toast.BaseToast

object ToastUtil {
    fun showMsg(msg: String) {
        BaseToast.showMsg(BaseApplication.getContext(), msg)
    }

    fun showMsg(resId: Int) {
        BaseToast.showMsg(BaseApplication.getContext(), resId)
    }
}