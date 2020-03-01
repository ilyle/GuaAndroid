package com.xiaoqi.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by xujie on 2019/2/19.
 * Mail : 617314917@qq.com
 */
abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var mContext: Context

    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置Activity布局
        createView()
        // 初始化Dagger依赖
        initDagger()
        // 初始化数据
        initData()
        // 初始化布局
        initView()
        // 初始化其他
        initOther()
        // 设置监听
        setListener()
    }

    /**
     * 获取布局文件R.layout.activity_abc
     */
    abstract fun getLayoutId(): Int

    open fun createView() {
        setContentView(getLayoutId())
    }

    /**
     * 初始化Dagger依赖
     */
    open fun initDagger() {

    }

    /**
     * 初始化数据
     */
    open fun initData() {
        mContext = this
    }

    /**
     * 初始化布局
     */
    open fun initView() {

    }

    /**
     * 初始化其他
     */
    open fun initOther() {

    }

    /**
     * 设置监听器
     */
    open fun setListener() {

    }


}