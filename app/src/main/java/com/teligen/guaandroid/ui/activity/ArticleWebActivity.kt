package com.teligen.guaandroid.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.teligen.guaandroid.ui.fragment.ArticleWebFragment
import com.xiaoqi.base.web.WebActivity

/**
 * Created by xujie on 2020-02-24.
 * Mail : 617314917@qq.com
 */
class ArticleWebActivity: WebActivity() {

    override fun getWebFragment(): ArticleWebFragment {
        return ArticleWebFragment.newInstance()
    }

    companion object {

        fun startAction(context: Context, url: String, title: String) {
            startAction(context, url, title, null)
        }

        fun startAction(context: Context, url: String, title: String, any: Parcelable?) {
            val intent = Intent(context, ArticleWebActivity::class.java)
            intent.putExtra(WebActivity.url, url)
            intent.putExtra(WebActivity.title, title)
            intent.putExtra(obj, any)
            context.startActivity(intent)
        }

    }
}