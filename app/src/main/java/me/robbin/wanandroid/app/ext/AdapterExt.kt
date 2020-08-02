package me.robbin.wanandroid.app.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import me.robbin.wanandroid.ui.fragment.home.view.HomeFragment
import me.robbin.wanandroid.ui.fragment.knowledge.view.KnowledgeFragment
import me.robbin.wanandroid.ui.fragment.me.view.MeFragment
import me.robbin.wanandroid.ui.fragment.question.QuestionFragment

/**
 * Adapter 拓展函数
 * Create by Robbin at 2020/7/10
 */

/**
 * 初始化 MainFragment 界面 ViewPager
 * @param fm 所属界面 FragmentManager
 * Create by Robbin at 2020/7/10
 */
fun ViewPager.mainAdapter(fm: FragmentManager): ViewPager {

    val homeFragment by lazy { HomeFragment() }
    val questionFragment by lazy { QuestionFragment() }
    val treeFragment by lazy { KnowledgeFragment() }
    val meFragment by lazy { MeFragment() }

    this.offscreenPageLimit = 3
    this.adapter = object : FragmentStatePagerAdapter(fm, 1) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> homeFragment
                1 -> questionFragment
                2 -> treeFragment
                else -> meFragment
            }
        }

        override fun getCount(): Int = 4

    }

    return this

}

/**
 * 初始化 ViewPager
 * @param fm 所属界面 FragmentManager
 * @param fragments 要添加的 Fragment 列表
 * Create by Robbin at 2020/7/10
 */
fun ViewPager.init(
    fm: FragmentManager,
    fragments: ArrayList<Fragment>
): ViewPager {
    this.adapter = object : FragmentStatePagerAdapter(fm, 1) {
        override fun getCount(): Int = fragments.size

        override fun getItem(position: Int): Fragment = fragments[position]
    }
    return this
}

/**
 * 初始化 ViewPager2
 * @param fm 所属界面 FragmentManager
 * @param lifecycle 所属界面生命周期
 * @param fragments 要添加的 Fragment 列表
 * @param enableSlide 是否开启用户输入 true 为开启
 * Create by Robbin at 2020/7/10
 */
fun ViewPager2.init(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    fragments: ArrayList<Fragment>,
    enableSlide: Boolean = true
): ViewPager2 {

    this.isUserInputEnabled = enableSlide

    this.adapter = object : FragmentStateAdapter(fm, lifecycle) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }

    return this

}
