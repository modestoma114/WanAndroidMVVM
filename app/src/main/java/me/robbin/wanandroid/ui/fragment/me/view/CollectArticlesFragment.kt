package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.layout_collect_articles.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.data.bean.UserCollectBean
import me.robbin.wanandroid.databinding.LayoutCollectArticlesBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.common.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.me.adapter.CollectAdapter
import me.robbin.wanandroid.ui.fragment.me.adapter.UserCollectAdapter
import me.robbin.wanandroid.ui.fragment.me.viewmodel.CollectViewModel

/**
 *
 * Create by Robbin at 2020/7/30
 */
class CollectArticlesFragment : BaseFragment<CollectViewModel, LayoutCollectArticlesBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.layout_collect_articles, BR.viewModel, mViewModel)
    }

    private val wanAdapter by lazy { CollectAdapter(requireContext()) }
    private val userAdapter by lazy { UserCollectAdapter() }

    private var type: CollectType = CollectType.WAN

    private var emptyStr = ""

    private var collectJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        arguments?.let {
            type = it.getSerializable("type") as CollectType
        }
        initAdapter()
        // 设置 RefreshLayout 刷新事件
        refreshCollects.setOnRefreshListener { refreshData() }
    }

    override fun initData() {
        // 获取数据
        refreshData()
    }

    override fun createObserver() {
        if (type == CollectType.USER) {
            mViewModel.userCollects.observe(viewLifecycleOwner, Observer {
                userAdapter.setNewInstance(it)
            })
        }
    }

    private fun initAdapter() {
        if (type == CollectType.WAN) {
            // 提示信息界面 Button 点击事件
            btnEmpty.setOnClickListener { wanAdapter.retry() }
            rlCollects.adapter =
                wanAdapter.withLoadStateFooter(PagingLoadStateAdapter { wanAdapter.retry() })
            lifecycleScope.launchWhenCreated {
                wanAdapter.loadStateFlow.collectLatest { loadState ->
                    refreshCollects.isRefreshing = loadState.refresh is LoadState.Loading
                }
            }
            wanAdapter.addLoadStateListener { loadState ->
                rlCollects.isVisible = loadState.refresh is LoadState.NotLoading
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
        } else {
            rlCollects.adapter = userAdapter
            progressLoading.visibility = View.GONE
            ivEmpty.visibility = View.GONE
            btnEmpty.visibility = View.GONE
            userAdapter.setOnItemClickListener { adapter, _, position ->
                val bundle = Bundle()
                val bean = adapter.data[position] as UserCollectBean
                bundle.putString("url", bean.link)
                bundle.putString("title", bean.name)
                bundle.putInt("articleId", bean.id)
                bundle.putBoolean("collected", true)
                bundle.putInt("userId", bean.userId)
                nav().navigate(R.id.action_global_to_webFragment, bundle)
            }
        }
    }

    private fun refreshData() {
        collectJob?.cancel()
        collectJob = lifecycleScope.launch {
            if (type == CollectType.WAN) {
                mViewModel.getWanCollect().collectLatest {
                    wanAdapter.submitData(it)
                }
            } else {
                mViewModel.getUserCollectArticles()
            }
        }
    }

    companion object {
        fun newInstance(type: CollectType): CollectArticlesFragment {
            val args = Bundle()
            args.putSerializable("type", type)
            val fragment = CollectArticlesFragment()
            fragment.arguments = args
            return fragment
        }
    }

}

enum class CollectType {
    WAN, USER
}