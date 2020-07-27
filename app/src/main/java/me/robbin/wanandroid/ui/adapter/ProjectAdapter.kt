package me.robbin.wanandroid.ui.adapter

import androidx.lifecycle.MutableLiveData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.databinding.RvItemProjectsBinding

/**
 *
 * Create by Robbin at 2020/7/27
 */
class ProjectAdapter :
    BaseQuickAdapter<ChapterBean, BaseDataBindingHolder<RvItemProjectsBinding>>(R.layout.rv_item_projects) {

    override fun convert(holder: BaseDataBindingHolder<RvItemProjectsBinding>, item: ChapterBean) {
        val binding = holder.dataBinding
        if (binding != null) {
            binding.title = item.name
        }
    }

}