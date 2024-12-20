package com.example.onlineMusic.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpUtil {
//    val baseUrl = "http://tingapi.ting.baidu.com"
    val baseUrl1 = "http://192.168.1.104:3000"

    /**
     * 根据指定的基IP地址获取retrofit
     */
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl1)//如果使用雷电模拟器，不能使用10.0.2.2地址，需要使用实际的ip地址
            .addConverterFactory(GsonConverterFactory.create())//当需要使用gson解析时调用该方法
            .build()
    }
}