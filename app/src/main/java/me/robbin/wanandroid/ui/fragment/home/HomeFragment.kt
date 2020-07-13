package me.robbin.wanandroid.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.databinding.FragmentHomeBinding
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
    private var articleJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        rlHome.layoutManager = LinearLayoutManager(requireContext())
        rlHome.adapter = adapter
    }

    override fun initData() {
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