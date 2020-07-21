package me.robbin.wanandroid.ui.fragment.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.mvvmscaffold.utils.setStatusBarLightMode
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentSearchBinding
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.ui.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.viewmodel.SearchViewModel

/**
 *
 * Create by Robbin at 2020/7/19
 */
class SearchFragment : BaseDBFragment<SearchViewModel, FragmentSearchBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_search

    private val adapter by lazy { ArticleAdapter(requireContext()) }
    private var searchJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        llSearch.addTopPadding(StatusBarUtils.getStatusBarHeight())
        rlSearchResult.adapter = adapter
        initAdapter()
        initSearch()
        btnClearSearch.setOnClickListener {
            mBinding.evSearch.setText("")
            searchJob?.cancel()
            mBinding.rlSearchResult.visibility = View.GONE
            mBinding.cgSearch.visibility = View.VISIBLE
        }
    }

    private fun initAdapter() {
        adapter.withLoadStateFooter(PagingLoadStateAdapter { adapter.retry() })
        adapter.addLoadStateListener { loadState ->
            mBinding.rlSearchResult.isVisible = loadState.refresh is LoadState.NotLoading
        }
    }

    override fun initData() {
        super.initData()
        mViewModel.getHotKey()
    }

    private fun initSearch() {
        mBinding.evSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateSearchList()
                true
            } else {
                false
            }
        }
        mBinding.evSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateSearchList()
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            @OptIn(ExperimentalPagingApi::class)
            adapter.dataRefreshFlow.collect {
                mBinding.rlSearchResult.scrollToPosition(0)
            }
        }
    }

    private fun updateSearchList() {
        mBinding.evSearch.text?.trim().let {
            if (!it.isNullOrBlank()) {
                search()
            }
        }
    }

    private fun search() {
        mBinding.cgSearch.visibility = View.GONE
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            mViewModel.searchArticle().collect {
                adapter.submitData(it)
            }
        }
    }

    override fun createObserver() {
        super.createObserver()
        mViewModel.hotKeys.observe(viewLifecycleOwner, Observer { hotKeys ->
            hotKeys.forEach { hotKey ->
                val chip = Chip(context)
                chip.text = hotKey.name
                chip.isCheckable = false
                chip.isCloseIconVisible = false
                chip.setOnClickListener {
                    mBinding.evSearch.setText(hotKey.name)
                    search()
                }
                cgSearch.addView(chip)
            }
        })
    }

    override fun initVariable() {
        mBinding.viewModel = mViewModel
    }

}