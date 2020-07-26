package me.robbin.wanandroid.ui.fragment.me

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.layout_article_list.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.ui.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.common.ArticleType
import me.robbin.wanandroid.databinding.FragmentMyShareBinding
import me.robbin.wanandroid.viewmodel.common.ArticleListViewModel

/**
 *
 * Create by Robbin at 2020/7/26
 */
class MyShareFragment : BaseFragment<ArticleListViewModel, FragmentMyShareBinding>() {

    private val articleAdapter by lazy { ArticleAdapter(requireContext()) }

    private var articleJob: Job? = null

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_my_share, BR.viewModel, mViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        rlArticles.adapter = articleAdapter
        initAdapter()
        refreshArticles.setOnRefreshListener { refreshData() }
        btnError.setOnClickListener { articleAdapter.retry() }
    }

    override fun initData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launchWhenResumed {
            mViewModel.getArticles(ArticleType.MY_SHARE).collect {
                articleAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        rlArticles.adapter =
            articleAdapter.withLoadStateFooter(PagingLoadStateAdapter { articleAdapter.retry() })
        articleAdapter.setOnArticleItemClickListener(object :
            ArticleAdapter.OnArticleItemClickListener {
            override fun setNavController(): NavController = nav()
        })
        articleAdapter.addLoadStateListener { loadState ->
            rlArticles.isVisible = loadState.refresh is LoadState.NotLoading
            loadingArticles.isVisible = loadState.refresh is LoadState.Loading
            ivError.isVisible = loadState.refresh is LoadState.Error
            btnError.isVisible = loadState.refresh is LoadState.Error
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
            mViewModel.getArticles(ArticleType.MY_SHARE).collect {
                articleAdapter.submitData(it)
            }
        }
    }

    override fun createObserver() {
        mViewModel.autoRefresh.observe(viewLifecycleOwner, Observer {
            refreshArticles.isRefreshing = it
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        articleJob?.cancel()
    }

}