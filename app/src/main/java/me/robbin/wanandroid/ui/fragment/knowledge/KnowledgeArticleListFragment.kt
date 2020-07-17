package me.robbin.wanandroid.ui.fragment.knowledge

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_knowledge_article_list.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.init
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.common.ArticleListFragment
import me.robbin.wanandroid.viewmodel.ArticleListViewModel

/**
 *
 * Create by Robbin at 2020/7/15
 */
class KnowledgeArticleListFragment : BaseVMFragment<ArticleListViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_knowledge_article_list

    private lateinit var chapter: ChapterBean
    private var index: Int = 1

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        arguments?.let {
            chapter = it.getParcelable("data")!!
            index = it.getInt("index")
        }
        toolbarKnowledgeArticle.title = chapter.name
        toolbarKnowledgeArticle.addTopPadding(StatusBarUtils.getStatusBarHeight())
        toolbarKnowledgeArticle.setNavigationOnClickListener {
            nav().navigateUp()
        }
        val fragments = ArrayList<Fragment>()
        chapter.children.forEach {
            fragments.add(ArticleListFragment.newInstance(3, it.id))
        }
        vpKnowledge.init(childFragmentManager, lifecycle, fragments)
            .postDelayed({ vpKnowledge.currentItem = index }, 50)
        TabLayoutMediator(tabKnowledge, vpKnowledge) { tab, position ->
            when (position) {
                position -> tab.text = chapter.children[position].name
            }
        }.attach()
    }

}