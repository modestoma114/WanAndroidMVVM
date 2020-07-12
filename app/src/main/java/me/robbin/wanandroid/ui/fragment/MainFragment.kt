package me.robbin.wanandroid.ui.fragment

import android.os.Bundle
import android.view.Gravity
import androidx.core.view.GravityCompat
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.mainAdapter

/**
 *
 * Create by Robbin at 2020/7/10
 */
class MainFragment: BaseVMFragment<BaseViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        vpMain.mainAdapter(this)
        toolbarMain.addTopPadding(108)
        toolbarMain.setNavigationOnClickListener {
            navigationMain.openDrawer(GravityCompat.START)
        }
        TabLayoutMediator(tabMain, vpMain) { tab, position ->
            when (position) {
                0 -> tab.text = "首页"
                1 -> tab.text = "广场"
                2 -> tab.text = "项目"
                else -> tab.text = "公众号"
            }
        }.attach()
    }

}