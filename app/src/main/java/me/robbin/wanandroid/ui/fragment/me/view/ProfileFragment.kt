package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.listener.AdapterItemClickListener
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.databinding.FragmentProfileBinding
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.ui.fragment.common.adapter.ArticleAdapter
import me.robbin.wanandroid.ui.fragment.common.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.me.viewmodel.ProfileViewModel

/**
 * 用户信息 Fragment
 * Create by Robbin at 2020/7/21
 */
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_profile, BR.viewModel, mViewModel)
    }

    private val articleAdapter by lazy { ArticleAdapter(requireContext()) }
    private var articleJob: Job? = null

    private var userId: Int = 2

    override fun initView(savedInstanceState: Bundle?) {
        arguments?.let {
            userId = it.getInt("userId", 2)
        }
        emptyStr = resources.getString(R.string.text_empty_retry)
        initAdapter()
        // 设置 RefreshLayout 刷新事件
        refreshArticles.setOnRefreshListener { refreshData() }
        // 提示信息界面 Button 点击事件
        btnEmpty.setOnClickListener { articleAdapter.retry() }
    }

    override fun initData() {
        if (userId != -1) {
            mViewModel.getUserInfo(userId)
        } else {
            mViewModel.userName.value = "没有这个用户"
            mViewModel.userInfo.value = "小场面，问题不大"
        }
    }

    override fun createObserver() {
        mViewModel.back.observe(viewLifecycleOwner, Observer {
            if (it) nav().navigateUp()
        })
        mViewModel.haveArticle.observe(viewLifecycleOwner, Observer {
            if (it) {
                articleJob?.cancel()
                refreshArticles.isEnabled = true
                articleJob = lifecycleScope.launch {
                    mViewModel.getUserPage().collectLatest { data ->
                        articleAdapter.submitData(data)
                    }
                }
            } else {
                rlArticles.visibility = View.GONE
                ivEmpty.visibility = View.VISIBLE
                btnEmpty.visibility = View.VISIBLE
                btnEmpty.text = "什么都没有"
                refreshArticles.isEnabled = false
            }
        })
    }

    private var emptyStr = ""

    /**
     * 初始化适配器
     * Create by Robbin at 2020/7/28
     */
    private fun initAdapter() {
        // 绑定带有 LoadMore 的适配器
        rlArticles.adapter =
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
    private fun refreshData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            mViewModel.getUserPage().collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }

}