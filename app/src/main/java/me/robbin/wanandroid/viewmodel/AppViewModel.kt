package me.robbin.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.UserBean

/**
 *
 * Create by Robbin at 2020/7/19
 */
class AppViewModel : BaseViewModel() {

    val userInfo: MutableLiveData<UserBean> = MutableLiveData()

    init {
    }

    fun getIntegral() {
        launchGo(
            {
                ApiService.getApi().getIntegral().data.coinCount.toToast()
            }
        )
    }

}