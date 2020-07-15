package me.robbin.wanandroid.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import me.robbin.wanandroid.ui.fragment.home.HomeFragment
import me.robbin.wanandroid.ui.fragment.me.MeFragment
import me.robbin.wanandroid.ui.fragment.question.QuestionFragment
import me.robbin.wanandroid.ui.fragment.knowledge.KnowledgeFragment

/**
 *
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
