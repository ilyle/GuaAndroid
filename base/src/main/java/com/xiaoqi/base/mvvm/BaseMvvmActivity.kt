package com.xiaoqi.base.mvvm

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.xiaoqi.base.BaseActivity
import com.xiaoqi.base.BaseFragment
import com.xiaoqi.base.ui.loadsir.callback.EmptyCallback
import com.xiaoqi.base.ui.loadsir.callback.ErrorCallback
import com.xiaoqi.base.ui.loadsir.callback.LoadingCallback


/**
 * Created by xujie on 2020-02-17.
 * Mail : 617314917@qq.com
 */
abstract class BaseMvvmActivity<VM : BaseViewModel<*>, VDB : ViewDataBinding> : BaseActivity() {

    protected lateinit var mViewModel: VM
    protected lateinit var mBinding: VDB

    // private lateinit var mLoadService: LoadService<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        addObserve()
    }

    override fun createView() {
        // 初始化我们的binging
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        // 给binding加上感知生命周期，AppCompatActivity就是lifeOwner
        mBinding.lifecycleOwner = this;
    }

    override fun initView() {
        super.initView()
        initLoadSir()
    }

    private fun initViewModel() {
        val modelClass: Class<VM> = getVMClass()
        mViewModel = ViewModelProviders.of(this).get(modelClass)
    }

    private fun initLoadSir() {
//        mLoadService = LoadSir.getDefault().register(getWrapperView()) {
//            // TODO: 重新加载逻辑
//        }
    }

    abstract fun getVMClass(): Class<VM>

    open fun getWrapperView(): Any {
        return mContext
    }

    /**
     * 添加对ViewModel的LiveData的观察
     */
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

    open fun showSuccess(vararg args: Any?) {

    }

    open fun showEmpty() {
        Log.i(TAG, "showEmpty")
        //mLoadService.showCallback(EmptyCallback::class.java)
    }

    open fun showError(error: Throwable?) {
        Log.i(TAG, "showError : $error")
        //mLoadService.showCallback(ErrorCallback::class.java)
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