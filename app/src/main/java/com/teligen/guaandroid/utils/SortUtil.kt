package com.teligen.guaandroid.utils

import com.teligen.guaandroid.bean.Article

object SortUtil {

    /**
     * 倒叙排列文章
     */
    fun sortArticleDsc(a1: Article, a2: Article): Int {
        return if (a1.publishTime > a2.publishTime) -1 else 1
    }

}