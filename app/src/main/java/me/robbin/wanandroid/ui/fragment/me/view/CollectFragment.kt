package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.layout_article_list.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentCollectBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.me.adapter.CollectAdapter
import me.robbin.wanandroid.ui.fragment.common.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.common.viewmodel.ArticleListViewModel

/**
 * 收藏列表 Fragment
 * Create by Robbin at 2020/7/21
 */
class CollectFragment : BaseFragment<ArticleListViewModel, FragmentCollectBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_collect, BR.viewModel, mViewModel)
    }

    private val collectAdapter by lazy {
        CollectAdapter(
            requireContext()
        )
    }
    private var articleJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        initAdapter()
        refreshArticles.setOnRefreshListener { refreshData() }
        btnEmpty.setOnClickListener { collectAdapter.retry() }
    }

    override fun initData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launchWhenResumed {
            mViewModel.getCollect().collect {
                collectAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        rlArticles.adapter =
            collectAdapter.withLoadStateFooter(PagingLoadStateAdapter { collectAdapter.retry() })
        collectAdapter.setOnArticleItemClickListener(object :
            CollectAdapter.OnArticleItemClickListener {
            override fun setNavController(): NavController = nav()
        })
        lifecycleScope.launchWhenCreated {
            collectAdapter.loadStateFlow.collectLatest { loadState ->
                refreshArticles.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }
        collectAdapter.addLoadStateListener { loadState ->
            rlArticles.isVisible = loadState.refresh is LoadState.NotLoading
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

    private fun refreshData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            mViewModel.getCollect().collect {
                collectAdapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        articleJob?.cancel()
    }

}