package me.robbin.wanandroid.ui.fragment.me.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.LicensesBean
import me.robbin.wanandroid.databinding.RvItemLicensesBinding

/**
 *
 * Create by Robbin at 2020/7/27
 */
class LicensesAdapter :
    BaseQuickAdapter<LicensesBean, BaseDataBindingHolder<RvItemLicensesBinding>>(R.layout.rv_item_licenses) {

    override fun convert(holder: BaseDataBindingHolder<RvItemLicensesBinding>, item: LicensesBean) {
        val binding = holder.dataBinding
        if (binding != null) {
            binding.bean = item
        }
    }

}