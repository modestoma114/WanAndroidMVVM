package me.robbin.wanandroid.ui.fragment.me.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import me.robbin.wanandroid.R
import me.robbin.wanandroid.model.ChapterBean
import me.robbin.wanandroid.databinding.RvItemProjectsBinding

/**
 * 项目分类列表适配器
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