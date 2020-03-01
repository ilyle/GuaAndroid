package com.xiaoqi.base.web

import android.content.Context
import android.content.Intent
import com.xiaoqi.base.BaseActivity
import com.xiaoqi.base.R

open class WebActivity : BaseActivity() {

    private lateinit var mWebFragment: WebFragment

    override fun getLayoutId(): Int {
        return R.layout.activity_common
    }

    override fun initView() {
        mWebFragment = getWebFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fl_common, mWebFragment).commit()
    }

    open fun getWebFragment(): WebFragment {
        return WebFragment.newInstance()
    }

    override fun initData() {
    }

    companion object {

        const val url = "url"
        const val title = "title"
        const val obj = "obj"

        fun startAction(context: Context, url: String, title: String) {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra(WebActivity.url, url) // Article实现Parcelable接口，可以在Activity间传输
            intent.putExtra(WebActivity.title, title)
            context.startActivity(intent)
        }
    }
}
