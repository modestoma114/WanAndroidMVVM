package me.robbin.wanandroid.ui.fragment.home

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentHomeBinding
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.ui.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.viewmodel.HomeViewModel

/**
 *
 * Create by Robbin at 2020/7/10
 */
class HomeFragment : BaseDBFragment<HomeViewModel, FragmentHomeBinding>() {

    private val adapter by lazy { ArticleAdapter(requireContext()) }
    private var articleJob: Job? = null

    private lateinit var refresh: SwipeRefreshLayout

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home, BR.state, mViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbarHome.addTopPadding(StatusBarUtils.getStatusBarHeight())
        toolbarHome.setTitle(R.string.tab_home)
        toolbarHome.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener if (it.itemId == R.id.searchHome) {
                nav().navigate(R.id.action_main_to_searchFragment)
                true
            } else
                false
        }
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
        rlHome.adapter = adapter.withLoadStateFooter(PagingLoadStateAdapter { adapter.retry() })
        adapter.setOnArticleItemClickListener(object :
            ArticleAdapter.OnArticleItemClickListener {
            override fun setNavController(): NavController = nav()
        })
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
                refresh.isRefreshing = false
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        articleJob?.cancel()
    }

}