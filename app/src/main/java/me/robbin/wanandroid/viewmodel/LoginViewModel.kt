package me.robbin.wanandroid.viewmodel

import android.content.Context
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.SPUtils
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.app.watcher.SimpleWatcher
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ApiResponse
import me.robbin.wanandroid.data.bean.UserBean

/**
 *
 * Create by Robbin at 2020/7/17
 */
class LoginViewModel : BaseViewModel() {

    val username: MutableLiveData<String> = MutableLiveData("")
    val pwd: MutableLiveData<String> = MutableLiveData("")
    val pwd2: MutableLiveData<String> = MutableLiveData("")

    val nameWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            username.value = s.toString()
        }
    }

    val pwdWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            pwd.value = s.toString()
        }
    }

    val pwd2Watcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            pwd2.value = s.toString()
        }
    }

    fun login() {
        launchOnlyResult<UserBean>(
            block = { ApiService.getApi().login(username.value.toString(), pwd.value.toString()) },
            success = {
                val sp = SPUtils.getInstance("UserInfo", Context.MODE_PRIVATE)
            },
            error = {
                "${it.code}: ${it.code}".toToast()
            }
        )
    }

}