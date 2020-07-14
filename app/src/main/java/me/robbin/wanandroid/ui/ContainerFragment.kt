package me.robbin.wanandroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.fragment_chapter.*
import kotlinx.android.synthetic.main.fragment_container.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ui.fragment.MainFragment
import me.robbin.wanandroid.ui.fragment.todo.TodoFragment

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ContainerFragment: BaseVMFragment<BaseViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_container

    private val todoFragment by lazy { TodoFragment() }
    private val mainFragment by lazy { MainFragment() }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        val fragments = listOf<Fragment>(todoFragment, mainFragment)
        vpContainer.adapter = object : FragmentStatePagerAdapter(childFragmentManager, 1) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> fragments[0]
                    else -> fragments[1]
                }
            }

            override fun getCount(): Int = 2

        }
        vpContainer.currentItem = 1
    }

}