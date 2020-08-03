package me.robbin.wanandroid.ui.fragment.me.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.data.repository.IntegralRepository

/**
 * 积分界面 ViewModel
 * Create by Robbin at 2020/7/25
 */
class IntegralViewModel : BaseViewModel() {

    private val integralRepository by lazy { IntegralRepository.instance }

    val integral: MutableLiveData<Int> = MutableLiveData(0)

    val autoRefresh: MutableLiveData<Boolean> = MutableLiveData(false)

    /**
     * 获取积分
     * Create by Robbin at 2020/7/25
     */
    fun getIntegral() =
        launchGo(
            block = {
                autoRefresh.value = true
                integral.value = ApiService.getApi().getIntegral().data.coinCount
            },
            complete = { autoRefresh.value = false }
        )

    /**
     * 获取积分详细
     * Create by Robbin at 2020/7/25
     */
    fun getIntegralDetail() = integralRepository.getIntegralDetail()

}