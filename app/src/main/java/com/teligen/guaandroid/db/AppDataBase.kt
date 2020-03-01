package com.teligen.guaandroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teligen.guaandroid.bean.Article
import com.teligen.guaandroid.db.dao.ArticleDao

/**
 * Created by xujie on 2020-02-26.
 * Mail : 617314917@qq.com
 */
@Database(entities = [Article::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getArticleDao(): ArticleDao
}