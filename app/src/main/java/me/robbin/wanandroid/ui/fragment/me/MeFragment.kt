package me.robbin.wanandroid.ui.fragment.me

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_me.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentMeBinding
import me.robbin.wanandroid.ext.checkLogin
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

    override fun initView(savedInstanceState: Bundle?) {
        refreshMe.setOnRefreshListener {
            mViewModel.refreshInfo()
        }
    }

    override fun createObserver() {
        appViewModel.isLogin.observe(viewLifecycleOwner, Observer {
            refreshMe.isEnabled = it
            if (it) {
                mViewModel.getUserInfo()
            } else {
                mViewModel.clearUserInfo()
            }
        })
        mViewModel.refresh.observe(viewLifecycleOwner, Observer {
            refreshMe.isRefreshing = it
        })
    }

    inner class RouteClick {

        fun goProfile() = checkLogin { nav().navigate(R.id.action_main_to_profileFragment) }

        fun goIntegral() = checkLogin { nav().navigate(R.id.action_main_to_integralFragment) }

        fun goMyCollect() = checkLogin { nav() }

        fun goMyArticle() = checkLogin { nav() }

        fun goWanAndroidSite() = nav()

        fun goProject() = nav()

        fun goOpenSource() = nav()

        fun goAboutMe() = nav()

        fun goSetting() = nav().navigate(R.id.action_mainFragment_to_settingFragment)
    }

}