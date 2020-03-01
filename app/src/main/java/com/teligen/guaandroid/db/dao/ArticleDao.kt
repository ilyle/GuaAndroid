package com.teligen.guaandroid.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.teligen.guaandroid.bean.Article
import io.reactivex.Observable

/**
 * Created by xujie on 2020-02-26.
 * Mail : 617314917@qq.com
 */
@Dao
interface ArticleDao {

    // 查询所有数据
    @Query("Select * from article")
    fun getAll(): Observable<List<Article>>

    // 插入单条数据
    @Insert
    fun insert(article: Article)

    // 删除单条数据
    @Delete
    fun remove(article: Article)
}