package com.xiaoqi.base.mvvm

import androidx.lifecycle.MutableLiveData
import com.xiaoqi.base.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * 继承该类的业务Model，包含都是业务
 *
 * Created by xujie on 2020-02-18.
 * Mail : 617314917@qq.com
 */
open class BaseModel {

    val TAG = javaClass.simpleName

    private val mCompositeDisposable = CompositeDisposable()

    fun getRetrofitClient(): Retrofit {
        return RetrofitClient.getClient()
    }

    /**
     * 带状态的LiveData
     */
    fun <T> observeStateData(observable: Observable<T>, liveData: MutableLiveData<StateData<T>>): MutableLiveData<StateData<T>> {
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                liveData.postValue(StateData.loading())
            }
            .observeOn(AndroidSchedulers.mainThread())
            //.compose(objectLifecycleTransformer)
            .subscribeWith(object : DisposableObserver<T>() {
                override fun onComplete() {

                }

                override fun onNext(t: T) {
                    liveData.postValue(StateData.success(t))
                }

                override fun onError(e: Throwable) {
                    liveData.postValue(StateData.error(e))
                }

            })
        mCompositeDisposable.add(disposable)
        return liveData
    }

    /**
     * 不带状态的LiveData
     */
    fun <T> observe(observable: Observable<T>, liveData: MutableLiveData<T>): MutableLiveData<T> {
        val disposable = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<T>() {
                override fun onComplete() {

                }

                override fun onNext(t: T) {
                    liveData.postValue(t)
                }

                override fun onError(e: Throwable) {

                }

            })
        mCompositeDisposable.add(disposable)
        return liveData
    }
}