package me.robbin.wanandroid.ui.fragment.login.view

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.layout_login.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.databinding.LayoutLoginBinding
import me.robbin.wanandroid.ui.fragment.login.viewmodel.LoginViewModel

/**
 * 登录 Fragment
 * Create by Robbin at 2020/7/17
 */
class LoginFragment : BaseFragment<BaseViewModel, LayoutLoginBinding>() {

    private val userViewModel by activityViewModels<LoginViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.layout_login, BR.viewModel, userViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        btnGoRegister.setOnClickListener {
            (parentFragment as LoginMainFragment).goRegister()
        }
        btnLogin.setOnClickListener {
            userViewModel.login {
                appViewModel.setIsLogin(true)
                nav().navigateUp()
            }
        }
    }

}