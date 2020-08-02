package me.robbin.wanandroid.ui.fragment.main.view

import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_main.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.setStatusBarLightMode
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.ext.mainAdapter

/**
 * 二级承载界面
 * Create by Robbin at 2020/7/13
 */
class MainFragment : BaseVMFragment<BaseViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_main

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
        vpMain.addOnPageChangeListener(pageChangeListener)
    }

    private var menuItem: MenuItem? = null
    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            if (menuItem != null)
                menuItem?.isChecked = false
            else
                bottomMain.menu.getItem(position).isChecked = false
            menuItem = bottomMain.menu.getItem(position)
            menuItem?.isChecked = true
        }

    }

    override fun onResume() {
        super.onResume()
        setStatusBarLightMode(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vpMain.removeOnPageChangeListener(pageChangeListener)
    }

}