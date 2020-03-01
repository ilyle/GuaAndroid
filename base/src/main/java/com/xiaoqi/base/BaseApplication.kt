package com.xiaoqi.base

import android.app.Application
import android.content.Context
import com.kingja.loadsir.core.LoadSir
import com.xiaoqi.base.di.component.DaggerApplicationComponent
import com.xiaoqi.base.di.module.ApplicationModule
import com.xiaoqi.base.ui.loadsir.callback.*


/**
 * Created by xujie on 2019/2/19.
 * Mail : 617314917@qq.com
 */
open class BaseApplication : Application() {

    lateinit var mApplicationComponent: DaggerApplicationComponent

    override fun onCreate() {
        super.onCreate()

        initInjection()

        initLoadSir()

        instance = this
    }

    private fun initInjection() {
        mApplicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build() as DaggerApplicationComponent
    }

    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback()) // 添加各种状态页
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(TimeoutCallback())
            .addCallback(CustomCallback())
            .setDefaultCallback(LoadingCallback::class.java) // 设置默认状态页
            .commit()
    }

    companion object {
        private lateinit var instance: BaseApplication

        @JvmStatic
        fun getContext(): Context {
            return instance.applicationContext
        }

    }
}