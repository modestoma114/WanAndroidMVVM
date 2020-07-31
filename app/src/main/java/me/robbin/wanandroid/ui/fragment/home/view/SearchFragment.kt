package me.robbin.wanandroid.ui.fragment.home.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentSearchBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.common.view.BaseArticlesFragment
import me.robbin.wanandroid.ui.fragment.home.viewmodel.SearchViewModel

/**
 * 搜索 Fragment
 * Create by Robbin at 2020/7/19
 */
class SearchFragment : BaseArticlesFragment<SearchViewModel, FragmentSearchBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_search, BR.viewModel, mViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        initSearch()
        emptyStr = resources.getString(R.string.text_search_empty)

        // 清除按钮点击事件
        btnClearSearch.setOnClickListener {
            etSearch.setText("")
            articleJob?.cancel()
            rlArticles.visibility = View.GONE
            cgSearch.visibility = View.VISIBLE
        }
    }

    override fun initData() {
        mViewModel.getHotKey()
    }

    // 初始化搜索
    private fun initSearch() {
        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateSearchList()
                true
            } else {
                false
            }
        }
        etSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateSearchList()
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            @OptIn(ExperimentalPagingApi::class)
            articleAdapter.dataRefreshFlow.collectLatest {
                rlArticles.scrollToPosition(0)
            }
        }
    }

    /**
     * 更新搜索列表
     * Create by Robbin at 2020/7/19
     */
    private fun updateSearchList() {
        etSearch.text?.trim().let {
            if (!it.isNullOrBlank()) {
                search()
            }
        }
    }

    /**
     * 搜索事件
     * Create by Robbin at 2020/7/19
     */
    private fun search() {
        cgSearch.visibility = View.GONE
        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            mViewModel.getArticles().collect {
                articleAdapter.submitData(it)
            }
        }
    }

    override fun createObserver() {
        mViewModel.hotKeys.observe(viewLifecycleOwner, Observer { hotKeys ->
            hotKeys.forEach { hotKey ->
                val chip = Chip(context)
                chip.text = hotKey.name
                chip.isCheckable = false
                chip.isCloseIconVisible = false
                chip.setOnClickListener {
                    etSearch.setText(hotKey.name)
                    search()
                }
                cgSearch.addView(chip)
            }
        })
        mViewModel.back.observe(viewLifecycleOwner, Observer {
            if (it) nav().navigateUp()
        })
    }

}