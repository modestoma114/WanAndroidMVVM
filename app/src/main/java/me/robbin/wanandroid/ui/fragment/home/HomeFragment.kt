package me.robbin.wanandroid.ui.fragment.home

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentHomeBinding
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.ui.adapter.ReposLoadStateAdapter
import me.robbin.wanandroid.viewmodel.HomeViewModel

/**
 *
 * Create by Robbin at 2020/7/10
 */
class HomeFragment : BaseDBFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_home

    private val adapter by lazy { ArticleAdapter() }
    private var articleJob: Job? = null

    private lateinit var refresh: SwipeRefreshLayout

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbarHome.addTopPadding(StatusBarUtils.getStatusBarHeight())
        toolbarHome.setTitle(R.string.tab_home)
        initAdapter()
        refresh = refreshHome
        refresh.setOnRefreshListener {
            refreshData()
        }
        btnEmpty.setOnClickListener {
            adapter.retry()
        }
    }

    private fun initAdapter() {
        rlHome.adapter = adapter.withLoadStateFooter(ReposLoadStateAdapter { adapter.retry() })
        adapter.addLoadStateListener { loadState ->
            rlHome.isVisible = loadState.refresh is LoadState.NotLoading
            progressLoading.isVisible = loadState.refresh is LoadState.Loading
            ivEmpty.isVisible = loadState.refresh is LoadState.Error
            btnEmpty.isVisible = loadState.refresh is LoadState.Error
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                "\uD83D\uDE28 Wooops ${it.error}".toToast()
            }
        }
    }

    override fun initData() {
        articleJob = lifecycleScope.launch {
            mViewModel.getArticle().collect {
                adapter.submitData(it)
            }
        }
    }

    private fun refreshData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            mViewModel.getArticle().collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        articleJob?.cancel()
    }

}