package com.xiaoqi.base.mvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.xiaoqi.base.BaseFragment
import com.xiaoqi.base.ui.loadsir.callback.EmptyCallback
import com.xiaoqi.base.ui.loadsir.callback.ErrorCallback
import com.xiaoqi.base.ui.loadsir.callback.LoadingCallback

/**
 * Created by xujie on 2020-02-20.
 * Mail : 617314917@qq.com
 */
abstract class BaseMvvmFragment<VM : BaseViewModel<*>, VDB : ViewDataBinding> : BaseFragment() {

    protected lateinit var mViewModel: VM
    protected lateinit var mBinding: VDB

    // private lateinit var mLoadService: LoadService<*>

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate<VDB>(inflater, getLayoutId(), null, false)
        mView = mBinding.root
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        addObserve()
    }

    override fun initView(view: View) {
        super.initView(view)
        // initLoadSir()
    }

    private fun initViewModel() {
        val modelClass: Class<VM> = getVMClass()
        mViewModel = ViewModelProviders.of(this).get(modelClass)
    }

//    private fun initLoadSir() {
//        mLoadService = LoadSir.getDefault().register(getWrapperView())
//    }

    abstract fun getVMClass(): Class<VM>

    open fun getWrapperView(): Any {
        return mView
    }

    open fun addObserve() {

    }

    open fun showLoading() {
        Log.i(TAG, "showLoading")
        //mLoadService.showCallback(LoadingCallback::class.java)
    }

    open fun hideLoading() {
        Log.i(TAG, "hideLoading")
        //mLoadService.showCallback(SuccessCallback::class.java)
    }

    open fun showSuccess(any: Any?) {
        Log.i(TAG, "showSuccess : $any")
    }

    open fun showEmpty() {
        Log.i(TAG, "showEmpty")
        // mLoadService.showCallback(EmptyCallback::class.java)
    }

    open fun showError(error: Throwable?) {
        Log.i(TAG, "showError : $error")
        // mLoadService.showCallback(ErrorCallback::class.java)
    }

    fun showPopupLoading() {

    }

    fun hidePopupLoading() {

    }

    fun showNonPopupLoading() {

    }

    fun hideNonPopupLoading() {

    }


    fun <T> observeStateLiveData(stateData: StateData<T>) {
        stateData.handler(object : StateData.OnHandleCallback<T> {
            override fun onLoading() {
                showLoading()
            }

            override fun onSuccess(data: T?) {
                showSuccess(data)
            }

            override fun onError(error: Throwable?) {
                showError(error)
            }

            override fun onCompleted() {
                hideLoading()
            }

        })
    }
}