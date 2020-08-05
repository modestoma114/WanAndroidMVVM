package me.robbin.wanandroid.ui.fragment.login.view

import android.os.Bundle
import androidx.navigation.navGraphViewModels
import kotlinx.android.synthetic.main.layout_register.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.databinding.LayoutRegisterBinding
import me.robbin.wanandroid.ui.fragment.login.viewmodel.LoginViewModel

/**
 * 注册 Fragment
 * Create by Robbin at 2020/7/17
 */
class RegisterFragment : BaseFragment<BaseViewModel, LayoutRegisterBinding>() {

    private val userViewModel by navGraphViewModels<LoginViewModel>(R.navigation.nav_login)

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.layout_register, BR.viewModel, userViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        btnGoLogin.setOnClickListener {
            (parentFragment as LoginMainFragment).goLogin()
        }
        btnRegister.setOnClickListener {
            userViewModel.register {
                "注册成功".toToast()
                appViewModel.setIsLogin(true)
                nav().navigateUp()
            }
        }
    }

}