package com.xiaoqi.base.mvvm

/**
 * 这个用来拓展LiveData，带状态
 *
 * Created by leo on 2019/10/16.
 * Modifed by xujie on 2020/2/18
 */
class StateData<T> {

    var state: Int = 0
    var data: T? = null
    var error: Throwable? = null

    constructor(state: Int) : this(state, null, null)

    constructor(state: Int, data: T) : this(state, data, null)

    constructor(state: Int, error: Throwable) : this(state, null, error)

    constructor(state: Int, data: T?, error: Throwable?) {
        this.state = state
        this.data = data
        this.error = error
    }


    fun handler(callback: OnHandleCallback<T>) {
        when (state) {
            LOADING -> callback.onLoading()
            SUCCESS -> callback.onSuccess(data)
            ERROR -> callback.onError(error)
        }
        if (state != LOADING) {
            callback.onCompleted()
        }
    }


    interface OnHandleCallback<T> {

        fun onLoading()

        fun onSuccess(data: T?)

        fun onError(error: Throwable?)

        fun onCompleted()
    }

    companion object {

        const val LOADING = 0 // 加载中
        const val SUCCESS = 1 // 成功
        const val ERROR = 2 // 失败

        fun <T> loading(): StateData<T> {
            return StateData(LOADING)
        }

        fun <T> success(data: T): StateData<T> {
            return StateData(
                SUCCESS,
                data
            )
        }

        fun <T> error(t: Throwable): StateData<T> {
            return StateData(
                ERROR,
                t
            )
        }

    }


}
