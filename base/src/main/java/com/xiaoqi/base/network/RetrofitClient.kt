package com.xiaoqi.base.network

import com.xiaoqi.base.constant.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    /**
     * okHttp客户端
     */
    private var okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

    /**
     * retrofit客户端
     */
    private var retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl(Constant.URL_BASE)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * 获取retrofit客户端
     */
    fun getClient(): Retrofit {
        return retrofitClient
    }

}