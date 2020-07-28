package me.robbin.wanandroid.ui.fragment.home.view

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_home.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ui.fragment.common.view.BaseArticlesFragment
import me.robbin.wanandroid.databinding.FragmentHomeBinding
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.home.viewmodel.HomeViewModel

/**
 * 首页 Fragment
 * Create by Robbin at 2020/7/10
 */
class HomeFragment : BaseArticlesFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home, BR.state, mViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbarHome.addTopPadding(StatusBarUtils.getStatusBarHeight())
        toolbarHome.setTitle(R.string.tab_home)

        // 绑定搜索按钮点击事件
        toolbarHome.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener if (it.itemId == R.id.searchHome) {
                nav().navigate(R.id.action_main_to_search)
                true
            } else
                false
        }
    }

}