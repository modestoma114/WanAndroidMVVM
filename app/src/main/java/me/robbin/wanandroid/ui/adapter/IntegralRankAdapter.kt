package me.robbin.wanandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.IntegralBean
import me.robbin.wanandroid.databinding.RvItemIntegralRankBinding

/**
 *
 * Create by Robbin at 2020/7/26
 */
class IntegralRankAdapter(private val context: Context) :
    PagingDataAdapter<IntegralBean, IntegralRankViewHolder>(INTEGRAL_COMPARATOR) {

    companion object {
        val INTEGRAL_COMPARATOR = object : DiffUtil.ItemCallback<IntegralBean>() {
            override fun areItemsTheSame(
                oldItem: IntegralBean,
                newItem: IntegralBean
            ): Boolean =
                oldItem.userId == newItem.userId

            override fun areContentsTheSame(
                oldItem: IntegralBean,
                newItem: IntegralBean
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntegralRankViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<RvItemIntegralRankBinding>(
            inflater,
            R.layout.rv_item_integral_rank,
            parent,
            false
        )
        return IntegralRankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IntegralRankViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<RvItemIntegralRankBinding>(holder.itemView)
        binding?.bean = getItem(position)
        binding?.max = 20000
    }

}

class IntegralRankViewHolder(binding: RvItemIntegralRankBinding) :
    RecyclerView.ViewHolder(binding.root)