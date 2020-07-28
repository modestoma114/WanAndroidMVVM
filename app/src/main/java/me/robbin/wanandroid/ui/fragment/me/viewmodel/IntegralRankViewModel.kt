package me.robbin.wanandroid.ui.fragment.me.viewmodel

import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.repository.IntegralRepository

/**
 * 积分排行榜界面 ViewModel
 * Create by Robbin at 2020/7/26
 */
class IntegralRankViewModel : BaseViewModel() {

    private val repository by lazy { IntegralRepository.instance }

    fun getIntegralRank() = repository.getIntegralRank()

}