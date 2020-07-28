package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_project.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ui.fragment.common.view.BaseArticlesFragment
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.databinding.FragmentProjectBinding
import me.robbin.wanandroid.ui.fragment.me.adapter.ProjectAdapter
import me.robbin.wanandroid.ui.fragment.me.viewmodel.ProjectViewModel

/**
 * 项目 Fragment
 * Create by Robbin at 2020/7/25
 */
class ProjectFragment : BaseArticlesFragment<ProjectViewModel, FragmentProjectBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_project, BR.viewModel, mViewModel)
    }

    private val projectAdapter by lazy { ProjectAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        rlProjects.adapter = projectAdapter
        projectAdapter.setOnItemClickListener { adapter, _, position ->
            mViewModel.cid.value = (adapter.getItem(position) as ChapterBean).id
        }
    }

    override fun initData() {
        mViewModel.getProjectList()
    }

    override fun createObserver() {
        mViewModel.projectList.observe(viewLifecycleOwner, Observer {
            mViewModel.cid.value = it[0].id
            projectAdapter.setNewInstance(it)
        })
        // 当 cid 发生变化时自动刷新数据
        mViewModel.cid.observe(viewLifecycleOwner, Observer {
            articleJob?.cancel()
            articleJob = lifecycleScope.launch {
                mViewModel.getArticles(cid = it).collectLatest {
                    articleAdapter.submitData(it)
                }
            }
        })
    }

}