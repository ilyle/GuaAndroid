package com.xiaoqi.base.di.module

import android.content.Context
import com.xiaoqi.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by xujie on 2019/2/20.
 * Mail : 617314917@qq.com
 */

@Module
class ApplicationModule(private val context: BaseApplication) {

    @Singleton
    @Provides
    fun provideActivity(): Context {
        return this.context
    }
}