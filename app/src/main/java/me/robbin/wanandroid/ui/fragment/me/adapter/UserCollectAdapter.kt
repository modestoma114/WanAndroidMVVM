package me.robbin.wanandroid.ui.fragment.me.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.RvItemCollectUserBinding

/**
 *
 * Create by Robbin at 2020/7/30
 */
class UserCollectAdapter :
    BaseQuickAdapter<UserCollect, BaseDataBindingHolder<RvItemCollectUserBinding>>(R.layout.rv_item_collect_user) {

    override fun convert(
        holder: BaseDataBindingHolder<RvItemCollectUserBinding>,
        item: UserCollect
    ) {
        val binding = holder.dataBinding
        if (binding != null) {
            binding.bean = item
        }
    }

}