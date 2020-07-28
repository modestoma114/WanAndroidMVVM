package me.robbin.wanandroid.ui.fragment.me.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.app.utils.CacheUtils
import me.robbin.wanandroid.data.api.ApiService

/**
 * 我的界面 ViewModel
 * Create by Robbin at 2020/7/17
 */
class MeViewModel : BaseViewModel() {

    val refresh: MutableLiveData<Boolean> = MutableLiveData(false)

    val userName: MutableLiveData<String> = MutableLiveData("去登陆")

    val info: MutableLiveData<String> = MutableLiveData("等级： —    排名： —")

    val coin: MutableLiveData<Int> = MutableLiveData(0)

    /**
     * 获取用户信息
     * Create by Robbin at 2020/7/21
     */
    fun getUserInfo() {
        val userInfo = CacheUtils.getUser()
        userName.value = userInfo?.nickname
        launchGo(
            {
                val result = ApiService.getApi().getIntegral()
                info.value = "等级：${result.data.level}  排名：${result.data.rank}"
                coin.value = result.data.coinCount
            }
        )
    }

    /**
     * 刷新用户信息
     * Create by Robbin at 2020/7/21
     */
    fun refreshInfo() {
        launchGo(
            {
                refresh.value = true
                val result = ApiService.getApi().getIntegral()
                info.value = "等级：${result.data.level}  排名：${result.data.rank}"
                coin.value = result.data.coinCount
            },
            complete = { refresh.value = false }
        )
    }

    /**
     * 清除用户信息
     * Create by Robbin at 2020/7/21
     */
    fun clearUserInfo() {
        userName.value = "去登陆"
        info.value = "等级： —    排名： —"
        coin.value = 0
    }

}