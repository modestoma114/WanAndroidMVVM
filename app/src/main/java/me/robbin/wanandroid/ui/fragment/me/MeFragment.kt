package me.robbin.wanandroid.ui.fragment.me

import androidx.lifecycle.Observer
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.utils.CacheUtils
import me.robbin.wanandroid.databinding.FragmentMeBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.viewmodel.MeViewModel

/**
 *
 * Create by Robbin at 2020/7/10
 */
class MeFragment : BaseFragment<MeViewModel, FragmentMeBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_me, BR.viewModel, mViewModel)
            .addBindingParams(BR.route, RouteClick())
    }

    override fun createObserver() {
        appViewModel.isLogin.observe(viewLifecycleOwner, Observer {
            if (it) {
                mViewModel.getUserInfo()
            } else {
                mViewModel.clearUserInfo()
            }
        })
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