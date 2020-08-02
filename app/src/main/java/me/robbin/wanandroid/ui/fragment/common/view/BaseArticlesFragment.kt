package me.robbin.wanandroid.ui.fragment.common.view

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_articles.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.listener.AdapterItemClickListener
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.ui.fragment.common.adapter.ArticleAdapter
import me.robbin.wanandroid.ui.fragment.common.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.common.viewmodel.BaseArticlesViewModel

/**
 * 文章列表 Fragment 基类
 * Create by Robbin at 2020/7/28
 */
abstract class BaseArticlesFragment<VM : BaseArticlesViewModel, VDB : ViewDataBinding> :
    BaseFragment<VM, VDB>() {

    // 文章列表适配器
    protected val articleAdapter by lazy { ArticleAdapter(requireContext()) }

    // 数据为空时所显示的提示信息
    var emptyStr = ""

    // 文章类型
    private var type: ArticleType = ArticleType.HOME

    // 文章模块Id
    private var cid: Int = -1

    // 数据获取 Job
    protected var articleJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        // 获取传递参数
        arguments?.let {
            type = it.getSerializable("type") as ArticleType
            cid = it.getInt("cid")
        }
        emptyStr = resources.getString(R.string.text_empty_retry)
        initAdapter(rlArticles)
        // 设置 RefreshLayout 刷新事件
        refreshArticles.setOnRefreshListener { refreshData() }
        // 提示信息界面 Button 点击事件
        btnEmpty.setOnClickListener { articleAdapter.retry() }
    }

    override fun initData() {
        // 获取数据
        articleJob?.cancel()
        articleJob = lifecycleScope.launchWhenResumed {
            mViewModel.getArticles(type, cid).collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }

    /**
     * 初始化适配器
     * Create by Robbin at 2020/7/28
     */
    private fun initAdapter(rl: RecyclerView) {
        // 绑定带有 LoadMore 的适配器
        rl.adapter =
            articleAdapter.withLoadStateFooter(PagingLoadStateAdapter { articleAdapter.retry() })
        // 将 RefreshLayout 状态与 Paging 加载状态绑定
        lifecycleScope.launchWhenCreated {
            articleAdapter.loadStateFlow.collectLatest { loadState ->
                refreshArticles.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }
        // 设置 Article 列表 Item 点击事件
        articleAdapter.setItemClickListener(object : AdapterItemClickListener {
            override fun itemClickListener(): NavController = nav()
            override fun itemLongClickListener(position: Int) {
            }
        })
        articleAdapter.setCollectAction { item, view, position ->
            if (view.isChecked) {
                mViewModel.collect(item.id) {
                    item.collect = true
                    view.isChecked = true
                    articleAdapter.notifyItemChanged(position)
                }
            } else {
                mViewModel.unCollect(item.id) {
                    item.collect = false
                    view.isChecked = false
                    articleAdapter.notifyItemChanged(position)
                }
            }
        }
        // 界面状态绑定
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

    /**
     * 刷新事件
     * Create by Robbin at 2020/7/28
     */
    open fun refreshData() {
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