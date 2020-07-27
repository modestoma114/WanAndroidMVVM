package me.robbin.wanandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.IntegralDetail
import me.robbin.wanandroid.data.bean.IntegralDetailBean
import me.robbin.wanandroid.databinding.RvItemIntegralBinding

/**
 *
 * Create by Robbin at 2020/7/25
 */
class IntegralAdapter(private val context: Context) :
    PagingDataAdapter<IntegralDetailBean, IntegralViewHolder>(INTEGRAL_COMPARATOR) {

    companion object {
        val INTEGRAL_COMPARATOR = object : DiffUtil.ItemCallback<IntegralDetailBean>() {
            override fun areItemsTheSame(
                oldItem: IntegralDetailBean,
                newItem: IntegralDetailBean
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: IntegralDetailBean,
                newItem: IntegralDetailBean
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: IntegralViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<RvItemIntegralBinding>(holder.itemView)
        val item = getItem(position)
        val strArr = item?.desc?.split(" ")
        strArr?.let {
            binding?.bean = IntegralDetail(it[0] + " " + it[1], it[2], item.coinCount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntegralViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<RvItemIntegralBinding>(
            inflater,
            R.layout.rv_item_integral,
            parent,
            false
        )
        return IntegralViewHolder(binding)
    }

}

class IntegralViewHolder(binding: RvItemIntegralBinding) : RecyclerView.ViewHolder(binding.root)