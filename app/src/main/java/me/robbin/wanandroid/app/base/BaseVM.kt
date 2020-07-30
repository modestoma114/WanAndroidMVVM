package me.robbin.wanandroid.app.base

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel

/**
 *
 * Create by Robbin at 2020/7/29
 */
open class BaseVM : BaseViewModel() {

    val back: MutableLiveData<Boolean> = MutableLiveData(false)

    fun goBack() {
        back.value = true
    }

}