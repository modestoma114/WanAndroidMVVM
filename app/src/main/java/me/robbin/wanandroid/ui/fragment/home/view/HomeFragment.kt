package me.robbin.wanandroid.ui.fragment.home.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.layout_articles.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.app.listener.AdapterItemClickListener
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.databinding.FragmentHomeBinding
import me.robbin.wanandroid.ui.fragment.common.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.home.adapter.HomeAdapter
import me.robbin.wanandroid.ui.fragment.home.viewmodel.HomeViewModel

/**
 * 首页 Fragment
 * Create by Robbin at 2020/7/10
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home, BR.state, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    private val homeAdapter by lazy { HomeAdapter(requireContext()) }

    private var articleJob: Job? = null

    private var emptyStr = ""

    override fun initView(savedInstanceState: Bundle?) {
        emptyStr = resources.getString(R.string.text_empty_retry)
        initAdapter()
        // 设置 RefreshLayout 刷新事件
        refreshArticles.setOnRefreshListener { refreshData() }
        // 提示信息界面 Button 点击事件
        btnEmpty.setOnClickListener { homeAdapter.retry() }
    }

    override fun initData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            mViewModel.getArticles().collectLatest {
                homeAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        // 绑定带有 LoadMore 的适配器
        rlArticles.adapter =
            homeAdapter.withLoadStateFooter(PagingLoadStateAdapter { homeAdapter.retry() })
        // 将 RefreshLayout 状态与 Paging 加载状态绑定
        lifecycleScope.launchWhenCreated {
            homeAdapter.loadStateFlow.collectLatest { loadState ->
                refreshArticles.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }
        // 设置 Article 列表 Item 点击事件
        homeAdapter.setItemClickListener(object : AdapterItemClickListener {
            override fun itemClickListener(): NavController = nav()
            override fun itemLongClickListener(position: Int) {
            }
        })
        homeAdapter.setCollectAction { item, view, position ->
            if (view.isChecked) {
                mViewModel.collect(item.id) {
                    item.collect = true
                    view.isChecked = true
                    homeAdapter.notifyItemChanged(position)
                }
            } else {
                mViewModel.unCollect(item.id) {
                    item.collect = false
                    view.isChecked = false
                    homeAdapter.notifyItemChanged(position)
                }
            }
        }
        // 界面状态绑定
        homeAdapter.addLoadStateListener { loadState ->
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
        homeAdapter.refresh()
    }

    inner class ClickProxy : Toolbar.OnMenuItemClickListener {

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            return if (item?.itemId == R.id.searchHome) {
                nav().navigate(R.id.action_main_to_search)
                true
            } else
                false
        }

    }

}