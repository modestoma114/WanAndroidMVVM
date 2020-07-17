package me.robbin.wanandroid.ui.fragment.login

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_register.*
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentRegisterBinding
import me.robbin.wanandroid.viewmodel.LoginViewModel

/**
 *
 * Create by Robbin at 2020/7/17
 */
class RegisterFragment : BaseDBFragment<BaseViewModel, FragmentRegisterBinding>() {

    private val userViewModel by activityViewModels<LoginViewModel>()

    override val layoutRes: Int
        get() = R.layout.fragment_register

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        btnGoLogin.setOnClickListener {
            (parentFragment as LoginMainFragment).goLogin()
        }
    }

    override fun initVariable() {
        mBinding.viewModel = userViewModel
    }

}