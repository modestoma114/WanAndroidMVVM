package me.robbin.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.api.ApiService

/**
 *
 * Create by Robbin at 2020/7/17
 */
class MeViewModel : BaseViewModel() {

    val userName: MutableLiveData<String> = MutableLiveData("去登陆")

    val info: MutableLiveData<String> = MutableLiveData("等级： —    排名： —")

    val coin: MutableLiveData<Int> = MutableLiveData(0)

    fun getUserInfo() {
        launchGo(
            {
                val result = ApiService.getApi().getIntegral()
                info.value = "等级：${result.data.level}  排名：${result.data.rank}"
                coin.value = result.data.coinCount
            }
        )
    }

}