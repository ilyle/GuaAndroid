package com.xiaoqi.base.mvp.presenter

import com.xiaoqi.base.mvp.view.BaseView
import javax.inject.Inject

/**
 * Created by xujie on 2019/2/20.
 * Mail : 617314917@qq.com
 */
abstract class BasePresenter {

    /**
     * Method that control the lifecycle of the view. It should be called in the
     * view's (Activity or Fragment) onCreate() method.
     */
    open fun create() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the
     * view's (Activity or Fragment) onResume() method.
     */
    open fun resume() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the
     * view's (Activity or Fragment) onPause() method.
     */
    open fun pause() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the
     * view's (Activity or Fragment) onStop() method.
     */
    open fun stop() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the
     * view's (Activity or Fragment) onDestroy() method.
     */
    open fun destroy() {

    }

    companion object {
        val TAG: String = Class::class.java.simpleName
    }
}