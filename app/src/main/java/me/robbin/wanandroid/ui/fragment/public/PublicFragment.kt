package me.robbin.wanandroid.ui.fragment.public

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_chapter.*
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.databinding.FragmentChapterBinding
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.init
import me.robbin.wanandroid.viewmodel.PublicViewModel

/**
 *
 * Create by Robbin at 2020/7/10
 */
class PublicFragment: BaseDBFragment<PublicViewModel, FragmentChapterBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_chapter

    private val fragments: ArrayList<Fragment> = arrayListOf()

    private val titles: ArrayList<ChapterBean> = arrayListOf()

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        vpChapter.init(childFragmentManager, lifecycle, fragments)
        tabChapter.addTopPadding(StatusBarUtils.getStatusBarHeight())
        TabLayoutMediator(tabChapter, vpChapter) { tab, position ->
            when (position) {
                position -> tab.text = titles[position].name
            }
        }.attach()
    }

    override fun initData() {
        mViewModel.getWechatList()
    }

    override fun createObserver() {
        super.createObserver()
        mViewModel.wechatList.observe(viewLifecycleOwner, Observer {
            titles.addAll(it)
            it.forEach { chapter ->
                fragments.add(PublicChildFragment.newInstance(chapter.id))
            }
            vpChapter.adapter?.notifyDataSetChanged()
        })
    }

}