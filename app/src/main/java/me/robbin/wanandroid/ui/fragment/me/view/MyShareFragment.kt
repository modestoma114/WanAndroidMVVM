package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.event.listener.AdapterItemClickListener
import me.robbin.wanandroid.app.ext.checkLogin
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.databinding.FragmentMyShareBinding
import me.robbin.wanandroid.ui.fragment.common.view.BaseArticlesFragment
import me.robbin.wanandroid.ui.fragment.me.viewmodel.MyShareViewModel

/**
 * 我的分享 Fragment
 * Create by Robbin at 2020/7/26
 */
class MyShareFragment : BaseArticlesFragment<MyShareViewModel, FragmentMyShareBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_my_share, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        val item = arrayOf("删除")
        articleAdapter.setItemClickListener(object : AdapterItemClickListener {
            override fun itemClickListener(): NavController = nav()
            override fun itemLongClickListener(position: Int) {
                MaterialAlertDialogBuilder(context)
                    .setTitle(resources.getString(R.string.dialog_title_action))
                    .setItems(item) { dialog, which ->
                        if (which == 0) {
                            item[0].toToast()
                            val id = articleAdapter.getData(position)?.id
                            mViewModel.deleteShare(id) {
                                articleAdapter.refresh()
                                dialog.dismiss()
                            }
                        }
                    }.show()
            }
        })
    }

    inner class ClickProxy : Toolbar.OnMenuItemClickListener {
        fun back() = nav().navigateUp()

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            if (item?.itemId == R.id.addShare)
                checkLogin {
                    nav().navigate(R.id.action_my_share_to_share_Article)
                }
            return true
        }
    }

}