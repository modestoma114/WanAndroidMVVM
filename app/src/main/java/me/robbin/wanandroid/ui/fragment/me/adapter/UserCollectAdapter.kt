package me.robbin.wanandroid.ui.fragment.me.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.UserCollectBean
import me.robbin.wanandroid.databinding.RvItemCollectUserBinding

/**
 *
 * Create by Robbin at 2020/7/30
 */
class UserCollectAdapter :
    BaseQuickAdapter<UserCollectBean, BaseDataBindingHolder<RvItemCollectUserBinding>>(R.layout.rv_item_collect_user) {

    override fun convert(
        holder: BaseDataBindingHolder<RvItemCollectUserBinding>,
        item: UserCollectBean
    ) {
        val binding = holder.dataBinding
        if (binding != null) {
            binding.bean = item
        }
    }

}