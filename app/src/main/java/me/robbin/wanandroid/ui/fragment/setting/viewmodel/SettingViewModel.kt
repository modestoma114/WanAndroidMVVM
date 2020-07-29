package me.robbin.wanandroid.ui.fragment.setting.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.app.utils.CacheUtils

/**
 *
 * Create by Robbin at 2020/7/29
 */
class SettingViewModel : BaseViewModel() {

    val nightMode: MutableLiveData<Boolean> = MutableLiveData(CacheUtils.getNightMode())

}