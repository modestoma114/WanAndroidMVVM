package me.robbin.wanandroid.data.network

import me.robbin.mvvmscaffold.network.RetrofitClient

/**
 *
 * Create by Robbin at 2020/7/10
 */
object ApiService {

    fun getApi() = RetrofitClient.instance.getApi("https://www.wanandroid.com", WanApi::class.java)

}