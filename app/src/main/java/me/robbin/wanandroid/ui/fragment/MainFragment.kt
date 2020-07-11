package me.robbin.wanandroid.ui.fragment

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_main.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
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
        vp_main.mainAdapter(this)
        bottom_bar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    vp_main.setCurrentItem(0, false)
                    true
                }
                R.id.bottom_tree -> {
                    vp_main.setCurrentItem(1, false)
                    true
                }
                R.id.bottom_project -> {
                    vp_main.setCurrentItem(2, false)
                    true
                }
                R.id.bottom_wechat -> {
                    vp_main.setCurrentItem(3, false)
                    true
                }
                R.id.bottom_me -> {
                    vp_main.setCurrentItem(4, false)
                    true
                }
                else -> false
            }
        }
    }

}