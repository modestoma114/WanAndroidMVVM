package me.robbin.wanandroid.ui.fragment.knowledge.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_chapter_list.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.knowledge.adapter.KnowledgeAdapter
import me.robbin.wanandroid.ui.fragment.knowledge.adapter.NavigationAdapter
import me.robbin.wanandroid.ui.fragment.knowledge.viewmodel.ChapterListViewModel

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ChapterListFragment : BaseVMFragment<ChapterListViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_chapter_list

    private var type: ChapterType =
        ChapterType.KNOWLEDGE

    private val knowledgeAdapter by lazy { KnowledgeAdapter() }
    private val navigationAdapter by lazy { NavigationAdapter() }
    private val publicAdapter by lazy { KnowledgeAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        arguments?.let {
            type = it.getSerializable("type") as ChapterType
        }
        when (type) {
            ChapterType.KNOWLEDGE -> {
                rlChapterList.adapter = knowledgeAdapter
                knowledgeAdapter.setOnItemChipClickListener(object :
                    KnowledgeAdapter.OnItemChipClickListener {
                    override fun setNavController(): NavController {
                        return nav()
                    }
                })
            }
            ChapterType.NAVIGATION -> {
                rlChapterList.adapter = navigationAdapter
                navigationAdapter.setOnItemChipClickListener(object :
                    NavigationAdapter.OnItemChipClickListener {
                    override fun setNavController(): NavController {
                        return nav()
                    }
                })
            }
            ChapterType.PUBLIC -> {
                rlChapterList.adapter = publicAdapter
                publicAdapter.setOnItemChipClickListener(object :
                    KnowledgeAdapter.OnItemChipClickListener {
                    override fun setNavController(): NavController {
                        return nav()
                    }
                })
            }
        }
    }

    override fun initData() {
        when (type) {
            ChapterType.KNOWLEDGE -> mViewModel.getKnowledgeList()
            ChapterType.NAVIGATION -> mViewModel.getNavigationList()
            ChapterType.PUBLIC -> mViewModel.getPublicList()
        }
    }

    override fun createObserver() {
        super.createObserver()
        mViewModel.knowledgeList.observe(viewLifecycleOwner, Observer {
            knowledgeAdapter.setNewInstance(it)
        })
        mViewModel.navigationList.observe(viewLifecycleOwner, Observer {
            navigationAdapter.setNewInstance(it)
        })
        mViewModel.publicList.observe(viewLifecycleOwner, Observer {
            val data = ChapterBean(
                courseId = 1,
                id = 1,
                name = "公众号",
                order = 1,
                parentChapterId = 1,
                userControlSetTop = false,
                visible = 1,
                children = it
            )
            publicAdapter.setNewInstance(mutableListOf(data))
        })
    }

    companion object {
        fun newInstance(type: ChapterType): ChapterListFragment {
            val args = Bundle()
            args.putSerializable("type", type)
            val fragment =
                ChapterListFragment()
            fragment.arguments = args
            return fragment
        }
    }

}

enum class ChapterType {
    KNOWLEDGE, NAVIGATION, PUBLIC
}