package me.robbin.wanandroid.ui.fragment.question

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_chapter.*
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentChapterBinding
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.init
import me.robbin.wanandroid.ui.fragment.common.ArticleListFragment

/**
 *
 * Create by Robbin at 2020/7/10
 */
class QuestionFragment : BaseDBFragment<BaseViewModel, FragmentChapterBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_chapter

    private val questionFragment by lazy { ArticleListFragment.newInstance(1) }
    private val userArticleFragment by lazy { ArticleListFragment.newInstance(2) }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        tabChapter.addTopPadding(StatusBarUtils.getStatusBarHeight())
        tabChapter.tabMode = TabLayout.MODE_FIXED
        vpChapter.init(
            childFragmentManager,
            lifecycle,
            arrayListOf(questionFragment, userArticleFragment)
        )
        TabLayoutMediator(tabChapter, vpChapter) { tab, position ->
            when (position) {
                0 -> tab.text = "问答"
                1 -> tab.text = "广场"
            }
        }.attach()
    }

}