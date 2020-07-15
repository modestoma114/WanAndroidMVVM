package me.robbin.wanandroid.ui.fragment.knowledge

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_chapter_list.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.navigation.NavHostFragment
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ui.adapter.KnowledgeAdapter
import me.robbin.wanandroid.viewmodel.KnowledgeListViewModel

/**
 *
 * Create by Robbin at 2020/7/14
 */
class KnowledgeListFragment: BaseVMFragment<KnowledgeListViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_chapter_list

    private val knowledgeAdapter by lazy { KnowledgeAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        rlChapterList.adapter = knowledgeAdapter
        knowledgeAdapter.setOnItemChipClickListener(object : KnowledgeAdapter.OnItemChipClickListener {
            override fun setNavController(): NavController {
                return NavHostFragment.findNavController(this@KnowledgeListFragment)
            }
        })
    }

    override fun initData() {
        mViewModel.getKnowledgeList()
    }

    override fun createObserver() {
        super.createObserver()
        mViewModel.knowledgeList.observe(viewLifecycleOwner, Observer {
            knowledgeAdapter.setNewInstance(it.toMutableList())
        })
    }

}