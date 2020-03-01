package com.teligen.guaandroid.ui.fragment

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.ui.dialog.ArticleMoreDialog
import com.xiaoqi.base.web.WebActivity
import com.xiaoqi.base.web.WebFragment

/**
 * Created by xujie on 2020-02-24.
 * Mail : 617314917@qq.com
 */
class ArticleWebFragment: WebFragment() {

    private var mArticle: Article? = null

    override fun initData() {
        super.initData()
        mArticle = activity?.intent?.getParcelableExtra(WebActivity.obj)
    }

    override fun onMenuItemClickListener(): Boolean {
        return showMoreDialog()
    }

    private fun showMoreDialog(): Boolean {
        mArticle?.let {
            val dialog = ArticleMoreDialog.newInstance(it)
            val ft = (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            dialog.show(ft, dialog.javaClass.simpleName)
            return true
        } ?: let {
            Toast.makeText(context, "文章为空", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    companion object {
        fun newInstance(): ArticleWebFragment {
            return ArticleWebFragment()
        }
    }
}