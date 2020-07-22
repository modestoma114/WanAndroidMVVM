package me.robbin.wanandroid.ui.fragment.me

import android.os.Bundle
import androidx.lifecycle.Observer
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.ext.viewmodel.getAppVM
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.utils.CacheUtils
import me.robbin.wanandroid.databinding.FragmentMeBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.viewmodel.AppViewModel
import me.robbin.wanandroid.viewmodel.MeViewModel

/**
 *
 * Create by Robbin at 2020/7/10
 */
class MeFragment : BaseDBFragment<MeViewModel, FragmentMeBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_me

    private val appViewModel by lazy { getAppVM<AppViewModel>() }

    override fun initView(savedInstanceState: Bundle?) {
        appViewModel.isLogin.value.toString().toToast()
    }

    override fun initVariable() {
        mBinding.route = RouteClick()
        mBinding.viewModel = mViewModel
    }

    override fun createObserver() {
        appViewModel.userInfo.observe(viewLifecycleOwner, Observer {
            mViewModel.userName.value = it.nickname
        })
    }

    override fun initData() {
        mViewModel.getUserInfo()
    }

    inner class RouteClick {
        fun login() {
            if (CacheUtils.isLogin()) {
                nav().navigate(R.id.action_main_to_profileFragment)
            } else {
                nav().navigate(R.id.action_global_loginFragment)
            }
        }

        fun goIntegral() = nav()

        fun goMyCollect() = nav()

        fun goMyArticle() = nav()

        fun goWanAndroidSite() = nav()

        fun goOpenSource() = nav()

        fun goAboutMe() = nav()

        fun goSetting() = nav().navigate(R.id.action_mainFragment_to_settingFragment)
    }

}