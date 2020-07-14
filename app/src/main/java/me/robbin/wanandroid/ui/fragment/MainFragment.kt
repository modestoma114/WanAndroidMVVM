package me.robbin.wanandroid.ui.fragment

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.navigation.NavHostFragment
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.ext.mainAdapter

/**
 *
 * Create by Robbin at 2020/7/13
 */
class MainFragment : BaseVMFragment<BaseViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        vpMain.mainAdapter(childFragmentManager)
        bottomMain.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.tab_home -> {
                    vpMain.currentItem = 0
                    true
                }
                R.id.tab_question -> {
                    vpMain.currentItem = 1
                    true
                }
                R.id.tab_tree -> {
                    vpMain.currentItem = 2
                    true
                }
                R.id.tab_me -> {
                    vpMain.currentItem = 4
                    true
                }
                else -> false
            }
        }
    }

}