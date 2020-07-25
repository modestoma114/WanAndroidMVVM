package me.robbin.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.IntegralBean
import me.robbin.wanandroid.data.repository.IntegralRepository

/**
 *
 * Create by Robbin at 2020/7/25
 */
class IntegralViewModel : BaseViewModel() {

    private val integralRepository by lazy { IntegralRepository.instance }

    val autoRefresh: MutableLiveData<Boolean> = MutableLiveData(true)

    val integral: MutableLiveData<Int> = MutableLiveData(0)

    fun getIntegral() =
        launchGo(
            block = {
                autoRefresh.value = true
                integral.value = ApiService.getApi().getIntegral().data.coinCount
            },
            complete = { autoRefresh.value = false }
        )

    fun getIntegralDetail() = integralRepository.getIntegralDetail()

}