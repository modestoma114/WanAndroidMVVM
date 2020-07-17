package me.robbin.wanandroid.ui.fragment.me

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_me.*
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentMeBinding
import me.robbin.wanandroid.ext.nav

/**
 *
 * Create by Robbin at 2020/7/10
 */
class MeFragment : BaseDBFragment<BaseViewModel, FragmentMeBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_me

    override fun initView(savedInstanceState: Bundle?) {
        btnMeLogin.setOnClickListener {
            nav().navigate(R.id.action_global_loginFragment)
        }
    }

}