package me.robbin.wanandroid.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.SPUtils
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.UserBean

/**
 *
 * Create by Robbin at 2020/7/19
 */
class AppViewModel : BaseViewModel() {

    // 判断是否登录
    val isLogin: MutableLiveData<Boolean> = MutableLiveData(false)

    private val sp by lazy { SPUtils.getInstance("app", Context.MODE_PRIVATE) }

    init {
        isLogin.value = sp.getBoolean("isLogin", false)
    }

    fun setLogin(isLogin: Boolean) {
        this.isLogin.value = isLogin
        sp.put("isLogin", isLogin)
    }

}