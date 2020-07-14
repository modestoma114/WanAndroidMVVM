package me.robbin.wanandroid.data.repository

import me.robbin.wanandroid.data.api.ApiService

/**
 *
 * Create by Robbin at 2020/7/13
 */
class WechatRepository {

    suspend fun getWechatList() = ApiService.getApi().getPublicList()

}