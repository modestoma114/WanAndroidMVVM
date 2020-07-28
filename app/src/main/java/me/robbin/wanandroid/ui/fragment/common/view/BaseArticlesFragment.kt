package me.robbin.wanandroid.ui.fragment.common.view

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_article_list.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.common.adapter.ArticleAdapter
import me.robbin.wanandroid.ui.fragment.common.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.common.viewmodel.BaseArticlesViewModel

/**
 *
 * Create by Robbin at 2020/7/28
 */
abstract class BaseArticlesFragment<VM : BaseArticlesViewModel, VDB : ViewDataBinding> :
    BaseFragment<VM, VDB>() {

    protected val articleAdapter by lazy {
        ArticleAdapter(
            requireContext()
        )
    }

    var emptyStr = ""

    private var type: ArticleType =
        ArticleType.HOME
    private var cid: Int = -1

    protected var articleJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        arguments?.let {
            type = it.getSerializable("type") as ArticleType
            cid = it.getInt("cid")
        }
        emptyStr = resources.getString(R.string.text_empty_retry)
        initAdapter(rlArticles)
        refreshArticles.setOnRefreshListener { refreshData() }
        btnEmpty.setOnClickListener { articleAdapter.retry() }
    }

    override fun initData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launchWhenResumed {
            mViewModel.getArticles(type, cid).collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter(rl: RecyclerView) {
        rl.adapter =
            articleAdapter.withLoadStateFooter(PagingLoadStateAdapter { articleAdapter.retry() })
        lifecycleScope.launchWhenCreated {
            articleAdapter.loadStateFlow.collectLatest { loadState ->
                refreshArticles.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }
        articleAdapter.setOnArticleItemClickListener(object :
            ArticleAdapter.OnArticleItemClickListener {
            override fun setNavController(): NavController = nav()
        })
        articleAdapter.addLoadStateListener { loadState ->
            rlArticles.isVisible = loadState.refresh is LoadState.NotLoading
            progressLoading.isVisible = loadState.refresh is LoadState.Loading
            ivEmpty.isVisible = loadState.refresh is LoadState.Error
            btnEmpty.isVisible = loadState.refresh is LoadState.Error
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error
            if (errorState?.error is EmptyException) {
                btnEmpty.text = emptyStr
            } else {
                errorState?.let {
                    "\uD83D\uDE28 Wooops: ${it.error}".toToast()
                }
            }
        }
    }

    private fun refreshData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            mViewModel.getArticles(type, cid).collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        articleJob?.cancel()
    }

}