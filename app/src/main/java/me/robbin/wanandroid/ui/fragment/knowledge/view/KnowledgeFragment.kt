package me.robbin.wanandroid.ui.fragment.knowledge.view

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_chapter.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.init
import me.robbin.wanandroid.ui.fragment.knowledge.view.ChapterListFragment
import me.robbin.wanandroid.ui.fragment.knowledge.view.ChapterType

/**
 * 体系 Fragment
 * Create by Robbin at 2020/7/10
 */
class KnowledgeFragment : BaseVMFragment<BaseViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_chapter

    private val knowledgeListFragment by lazy { ChapterListFragment.newInstance(
        ChapterType.KNOWLEDGE) }
    private val naviFragment by lazy { ChapterListFragment.newInstance(
        ChapterType.NAVIGATION) }
    private val publicFragment by lazy { ChapterListFragment.newInstance(
        ChapterType.PUBLIC) }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        tabChapter.addTopPadding(StatusBarUtils.getStatusBarHeight())
        tabChapter.tabMode = TabLayout.MODE_FIXED
        vpChapter.init(
            childFragmentManager,
            lifecycle,
            arrayListOf(knowledgeListFragment, naviFragment, publicFragment)
        )
        TabLayoutMediator(tabChapter, vpChapter) { tab, position ->
            when (position) {
                0 -> tab.text = "体系"
                1 -> tab.text = "导航"
                2 -> tab.text = "公众号"
            }
        }.attach()
    }

}