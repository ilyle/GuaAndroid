package com.xiaoqi.base.di.module

import com.xiaoqi.base.mvp.view.BaseView
import dagger.Module
import dagger.Provides

/**
 * Created by Administrator on 2019/8/23.
 * 617314917@qq.com
 */
@Module
open class BaseModule(private val mBaseView: BaseView) {

    @Provides
    fun provideBaseView(): BaseView {
        return this.mBaseView
    }
}