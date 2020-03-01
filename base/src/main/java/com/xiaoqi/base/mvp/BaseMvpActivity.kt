package com.xiaoqi.base.mvp

import android.os.Bundle
import android.widget.Toast
import com.xiaoqi.base.BaseActivity
import com.xiaoqi.base.mvp.presenter.BasePresenter
import com.xiaoqi.base.mvp.view.BaseView
import javax.inject.Inject

/**
 * Created by xujie on 2019/2/21.
 * Mail : 617314917@qq.com
 */
abstract class BaseMvpActivity<T : BasePresenter> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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