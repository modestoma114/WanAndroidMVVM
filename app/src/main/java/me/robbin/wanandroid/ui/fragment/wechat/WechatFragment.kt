package me.robbin.wanandroid.ui.fragment.wechat

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_wechat.*
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.databinding.FragmentWechatBinding
import me.robbin.wanandroid.ext.init
import me.robbin.wanandroid.viewmodel.WechatViewModel

/**
 *
 * Create by Robbin at 2020/7/10
 */
class WechatFragment: BaseDBFragment<WechatViewModel, FragmentWechatBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_wechat

    private val fragments: ArrayList<Fragment> = arrayListOf()

    private val titles: ArrayList<ChapterBean> = arrayListOf()

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        vpWechat.init(this, fragments)
        TabLayoutMediator(tabWechat, vpWechat) {tab, position ->
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
            it.forEach {
                fragments.add(WechatChildFragment())
            }
            vpWechat.adapter?.notifyDataSetChanged()
        })
    }

}