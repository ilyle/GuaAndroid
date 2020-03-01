package com.xiaoqi.base.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.xiaoqi.base.BaseDialogFragment

/**
 * Created by xujie on 2020-02-25.
 * Mail : 617314917@qq.com
 */
abstract class BaseMvvmDialog<VM: BaseViewModel<*>, VDB: ViewDataBinding>: BaseDialogFragment() {

    protected lateinit var mViewModel: VM
    protected lateinit var mBinding: VDB

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate<VDB>(inflater, getLayoutId(), null, false)
        mView = mBinding.root
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        addObserve()
    }

    private fun initViewModel() {
        val modelClass: Class<VM> = getVMClass()
        mViewModel = ViewModelProviders.of(this).get(modelClass)
    }

    open fun addObserve() {

    }

    abstract fun getVMClass(): Class<VM>
}