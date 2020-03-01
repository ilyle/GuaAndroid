package com.xiaoqi.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {


    lateinit var mView: View

    val TAG: String = javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDagger()
        initData()
        initView(view)
        initOther()
        setListener()
    }

    /**
     * 获取布局文件R.layout.fragment_abc
     */
    abstract fun getLayoutId(): Int

    open fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(getLayoutId(), container, false)
        return mView
    }

    /**
     * 初始化Dagger2依赖
     */
    open fun initDagger() {

    }

    /**
     * 初始化数据，不依赖布局，先于initView()执行
     */
    open fun initData() {

    }

    /**
     * 初始化布局
     */
    open fun initView(view: View) {

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

    fun showDialogFragment(dialog: DialogFragment) {
        val ft = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
        dialog.show(ft, dialog.javaClass.simpleName)
    }


}