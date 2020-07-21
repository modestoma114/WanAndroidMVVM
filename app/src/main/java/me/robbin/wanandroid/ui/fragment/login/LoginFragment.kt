package me.robbin.wanandroid.ui.fragment.login

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_login.*
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.ext.viewmodel.getAppVM
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentLoginBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.viewmodel.AppViewModel
import me.robbin.wanandroid.viewmodel.LoginViewModel

/**
 *
 * Create by Robbin at 2020/7/17
 */
class LoginFragment : BaseDBFragment<BaseViewModel, FragmentLoginBinding>() {

    private val userViewModel by activityViewModels<LoginViewModel>()

    private val appViewModel by lazy { getAppVM<AppViewModel>() }

    override val layoutRes: Int
        get() = R.layout.fragment_login

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        btnGoRegister.setOnClickListener {
            (parentFragment as LoginMainFragment).goRegister()
        }
        btnLogin.setOnClickListener {
            userViewModel.login {
                appViewModel.setLogin(true)
                nav().navigateUp()
            }
        }
    }

    override fun initVariable() {
        mBinding.viewModel = userViewModel
    }

}