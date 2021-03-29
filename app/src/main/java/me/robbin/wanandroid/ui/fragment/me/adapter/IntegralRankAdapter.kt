package me.robbin.wanandroid.ui.fragment.me.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.model.Integral
import me.robbin.wanandroid.databinding.RvItemIntegralRankBinding

/**
 * 积分排名列表适配器
 * Create by Robbin at 2020/7/26
 */
class IntegralRankAdapter(private val context: Context) :
    PagingDataAdapter<Integral, IntegralRankViewHolder>(
        INTEGRAL_COMPARATOR
    ) {

    companion object {
        val INTEGRAL_COMPARATOR = object : DiffUtil.ItemCallback<Integral>() {
            override fun areItemsTheSame(
                oldItem: Integral,
                newItem: Integral
            ): Boolean =
                oldItem.userId == newItem.userId

            override fun areContentsTheSame(
                oldItem: Integral,
                newItem: Integral
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
        return IntegralRankViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: IntegralRankViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<RvItemIntegralRankBinding>(holder.itemView)
        binding?.bean = getItem(position)
        binding?.max = 25000
    }

}

class IntegralRankViewHolder(binding: RvItemIntegralRankBinding) :
    RecyclerView.ViewHolder(binding.root)