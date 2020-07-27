package me.robbin.wanandroid.ui.fragment.me

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.fragment_project.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.databinding.FragmentProjectBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.ui.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.adapter.ProjectAdapter
import me.robbin.wanandroid.viewmodel.me.ProjectViewModel

/**
 *
 * Create by Robbin at 2020/7/25
 */
class ProjectFragment : BaseFragment<ProjectViewModel, FragmentProjectBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_project, BR.viewModel, mViewModel)
    }

    private val projectAdapter by lazy { ProjectAdapter() }
    private val articleAdapter by lazy { ArticleAdapter(requireContext()) }

    private var articleJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        initAdapter()
        refreshProjectArticles.setOnRefreshListener { refreshData() }
    }

    private fun initAdapter() {
        rlProjects.adapter = projectAdapter
        projectAdapter.setOnItemClickListener { adapter, _, position ->
            mViewModel.cid.value = (adapter.getItem(position) as ChapterBean).id
        }
        rlProjectArticles.adapter =
            articleAdapter.withLoadStateFooter(PagingLoadStateAdapter { articleAdapter.retry() })
        articleAdapter.setOnArticleItemClickListener(object :
            ArticleAdapter.OnArticleItemClickListener {
            override fun setNavController(): NavController = nav()
        })
        lifecycleScope.launchWhenCreated {
            articleAdapter.loadStateFlow.collectLatest { loadState ->
                refreshProjectArticles.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }
        articleAdapter.addLoadStateListener { loadState ->
            rlProjectArticles.isVisible = loadState.refresh is LoadState.NotLoading
            progressLoading.isVisible = loadState.refresh is LoadState.Loading
            ivEmpty.isVisible = loadState.refresh is LoadState.Error
            btnEmpty.isVisible = loadState.refresh is LoadState.Error
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                "\uD83D\uDE28 Wooops: ${it.error}".toToast()
            }
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
        mViewModel.cid.observe(viewLifecycleOwner, Observer {
            articleJob?.cancel()
            articleJob = lifecycleScope.launch {
                mViewModel.getProjectArticles(it).collectLatest {
                    articleAdapter.submitData(it)
                }
            }
        })
    }

    private fun refreshData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            mViewModel.getProjectArticles(mViewModel.cid.value!!).collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }

}