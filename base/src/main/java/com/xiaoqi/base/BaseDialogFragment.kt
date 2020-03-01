package com.xiaoqi.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import android.view.Gravity
import android.view.WindowManager



/**
 * Created by xujie on 2019/4/23.
 * Mail : 617314917@qq.com
 */
abstract class BaseDialogFragment : DialogFragment() {

    lateinit var mView: View

    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, getStyle())
    }

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
     * 获取布局文件R.layout.abc
     *
     * 布局要求和建议
     * <ViewGroup
     *      android:width="MATCH_PARENT"
     *      android:width="MATCH_PARENT">
     *      <ViewGroup
     *          android:width="xxdp"
     *          android:width="xxdp">
     *
     *      </ViewGroup>
     * </ViewGroup>
     */
    abstract fun getLayoutId(): Int

    open fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(getLayoutId(), container, false)
        return mView
    }

    open fun initDagger() {

    }

    /**
     * 初始化操作
     */
    open fun initOther() {}

    /**
     * 初始化数据，不依赖布局，先于initView()执行
     */
    open fun initData() {}

    /**
     * 初始化窗口
     */
    private fun initWindow() {
        dialog?.window?.let {
            val params = it.attributes
            params.gravity = getGravity()
            params.width = getWidth()
            params.height = getHeight()
            it.decorView.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
            it.attributes = params
            it.setBackgroundDrawable(getBackgroundDrawable())
        }
    }

    /**
     * 初始化布局
     */
    open fun initView(view: View) {
        initWindow()
    }

    /**
     * 设置监听器
     */
    open fun setListener() {}

    /**
     * 获取左边padding
     */
    open fun getPaddingLeft() = -1

    /**
     * 获取上边padding
     */
    open fun getPaddingTop() = -1

    /**
     * 获取右边padding
     */
    open fun getPaddingRight() = -1

    /**
     * 获取下边padding
     */
    open fun getPaddingBottom() = -1

    /**
     * 获取dialog的排布
     */
    open fun getGravity(): Int = Gravity.CENTER

    /**
     * 获取dialog的宽度
     */
    open fun getWidth(): Int = WindowManager.LayoutParams.MATCH_PARENT

    /**
     * 获取dialog的高度
     */
    open fun getHeight(): Int = WindowManager.LayoutParams.WRAP_CONTENT

    /**
     * 获取风格，默认左右留空白
     */
    open fun getStyle(): Int = R.style.Theme_AppCompat_Dialog

    /**
     * 获取背景
     */
    open fun getBackgroundDrawable(): ColorDrawable = ColorDrawable(Color.TRANSPARENT)


}