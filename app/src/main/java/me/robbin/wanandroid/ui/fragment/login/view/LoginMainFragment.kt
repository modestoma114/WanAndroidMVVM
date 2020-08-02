package me.robbin.wanandroid.ui.fragment.login.view

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_login_main.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.ext.addTopPadding
import me.robbin.wanandroid.app.ext.backMain
import me.robbin.wanandroid.app.ext.init
import me.robbin.wanandroid.app.ext.nav

/**
 * 登录/注册 Fragment
 * Create by Robbin at 2020/7/11
 */
class LoginMainFragment : BaseVMFragment<BaseViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_login_main

    private val loginFragment by lazy { LoginFragment() }
    private val registerFragment by lazy { RegisterFragment() }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        btnLoginClose.addTopPadding(StatusBarUtils.getStatusBarHeight())
        btnLoginClose.setOnClickListener {
            nav().navigateUp()
        }
        vpLogin.init(childFragmentManager, lifecycle, arrayListOf(loginFragment, registerFragment))
        backMain()
    }

    fun goLogin() {
        vpLogin.currentItem = 0
    }

    fun goRegister() {
        vpLogin.currentItem = 1
    }

}