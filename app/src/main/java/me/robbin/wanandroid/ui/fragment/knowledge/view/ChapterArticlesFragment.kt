package me.robbin.wanandroid.ui.fragment.knowledge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_chapter_articles.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentChapterArticlesBinding
import me.robbin.wanandroid.model.Chapter
import me.robbin.wanandroid.ui.fragment.common.view.ArticleListsFragment
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType
import me.robbin.wanandroid.ui.fragment.knowledge.viewmodel.ChapterArticlesViewModel

/**
 * 模块文章列表 Fragment
 * Create by Robbin at 2020/7/15
 */
class ChapterArticlesFragment :
    BaseFragment<ChapterArticlesViewModel, FragmentChapterArticlesBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_chapter_articles, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    private var chapter: Chapter? = null
    private var index: Int = 1

    private var superChapterName: String = ""
    private var superChapterId: Int = -1
    private var chapterId: Int = -1

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        arguments?.let {
            chapter = it.getParcelable("data")
            index = it.getInt("index")
            superChapterName = it.getString("superChapterName", "")
            superChapterId = it.getInt("superChapterId", -1)
            chapterId = it.getInt("chapterId", -1)
        }
        mViewModel.title.value = chapter?.name
    }

    override fun initData() {
        if (chapter == null) {
            mViewModel.getKnowledgeList()
            mViewModel.title.value = superChapterName
        } else {
            initTabAndViewPager()
        }
    }

    override fun createObserver() {
        mViewModel.knowledgeList.observe(viewLifecycleOwner, Observer { data ->
            data.forEach { item ->
                if (item.id == superChapterId) {
                    chapter = item
                    initTabAndViewPager()
                    var i = 0
                    chapter!!.children.forEach {
                        if (it.id == chapterId)
                            index = i
                        i++
                    }
                    return@forEach
                }
            }
        })
    }

    private fun initTabAndViewPager() {
        val fragments = ArrayList<Fragment>()
        chapter?.children?.forEach {
            fragments.add(ArticleListsFragment.newInstance(ArticleType.TREE, it.id))
        }
        vpChapter.init(childFragmentManager, lifecycle, fragments)
            .postDelayed({ vpChapter.currentItem = index }, 50)
        TabLayoutMediator(tabChapter, vpChapter) { tab, position ->
            when (position) {
                position -> tab.text = chapter?.children?.get(position)?.name
            }
        }.attach()
    }

    inner class ClickProxy {
        fun back() {
            nav().navigateUp()
        }
    }

}