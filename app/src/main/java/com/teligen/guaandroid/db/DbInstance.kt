package com.teligen.guaandroid.db

import androidx.room.Room
import com.xiaoqi.base.BaseApplication

/**
 * Created by xujie on 2020-02-26.
 * Mail : 617314917@qq.com
 */

class DbInstance {

    companion object {

        const val DB_NAME = "db_guaandroid"

        val instance: AppDataBase by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(BaseApplication.getContext(), AppDataBase::class.java, DB_NAME).build()
        }
    }
}