package me.robbin.wanandroid.ext

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import me.robbin.wanandroid.ui.fragment.home.HomeFragment
import me.robbin.wanandroid.ui.fragment.me.MeFragment
import me.robbin.wanandroid.ui.fragment.project.ProjectFragment
import me.robbin.wanandroid.ui.fragment.tree.TreeFragment
import me.robbin.wanandroid.ui.fragment.wechat.WechatFragment

/**
 *
 * Create by Robbin at 2020/7/10
 */

fun ViewPager2.mainAdapter(fragment: Fragment): ViewPager2 {

    this.isUserInputEnabled = false
    this.adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> TreeFragment()
                2 -> ProjectFragment()
                3 -> WechatFragment()
                4 -> MeFragment()
                else -> throw Exception("Illegal index.")
            }
        }

        override fun getItemCount(): Int = 5
    }

    return this

}
