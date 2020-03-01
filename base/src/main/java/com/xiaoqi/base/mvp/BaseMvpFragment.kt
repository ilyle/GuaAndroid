package com.xiaoqi.base.mvp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xiaoqi.base.BaseFragment
import com.xiaoqi.base.mvp.presenter.BasePresenter
import com.xiaoqi.base.mvp.view.BaseView
import javax.inject.Inject

/**
 * Created by xujie on 2019/2/20.
 * Mail : 617314917@qq.com
 */
abstract class BaseMvpFragment<T : BasePresenter> : BaseFragment(), BaseView {

    @Inject
    lateinit var mPresenter: T


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.create()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.destroy()
    }

    override fun initDagger() {
        super.initDagger()
        initComponent()
    }

    abstract fun initComponent()

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showEmpty() {

    }

    override fun showError() {

    }
}