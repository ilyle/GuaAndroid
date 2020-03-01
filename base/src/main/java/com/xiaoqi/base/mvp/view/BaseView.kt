package com.xiaoqi.base.mvp.view

/**
 * Created by xujie on 2019/2/20.
 * Mail : 617314917@qq.com
 */
interface BaseView {

    /**
     * 吐司通知
     */
    fun showToast(msg: String)

    /**
     * 显示加载画面
     */
    fun showLoading()

    /**
     * 隐藏加载画面
     */
    fun hideLoading()

    /**
     * 显示空数据
     */
    fun showEmpty()

    /**
     * 显示网络错误
     */
    fun showError()
}