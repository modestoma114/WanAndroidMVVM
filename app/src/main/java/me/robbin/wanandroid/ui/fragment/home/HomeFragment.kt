package me.robbin.wanandroid.ui.fragment.home

import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.include_list.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentHomeBinding
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.viewmodel.HomeViewModel

/**
 *
 * Create by Robbin at 2020/7/10
 */
class HomeFragment : BaseDBFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_home

    private val adapter by lazy {
        ArticleAdapter()
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbar.title = resources.getString(R.string.bottom_home)
        toolbar.addTopPadding(108)
        data_list.layoutManager = LinearLayoutManager(requireContext())
        data_list.adapter = adapter
    }

    override fun initData() {
        mViewModel.getArticle()
    }

    override fun createObserver() {
        mViewModel.getArticle().observe(viewLifecycleOwner, Observer {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
        })
    }

}