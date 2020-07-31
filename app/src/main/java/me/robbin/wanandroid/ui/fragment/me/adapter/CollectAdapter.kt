package me.robbin.wanandroid.ui.fragment.me.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.listener.AdapterItemClickListener
import me.robbin.wanandroid.data.bean.CollectBean
import me.robbin.wanandroid.databinding.RvItemCollectBinding

/**
 * 收藏列表适配器
 * Create by Robbin at 2020/7/26
 */
class CollectAdapter(private val context: Context) :
    PagingDataAdapter<CollectBean, CollectViewHolder>(COLLECT_COMPARATOR) {

    companion object {
        val COLLECT_COMPARATOR = object : DiffUtil.ItemCallback<CollectBean>() {
            override fun areItemsTheSame(oldItem: CollectBean, newItem: CollectBean): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CollectBean, newItem: CollectBean): Boolean =
                oldItem == newItem
        }
    }

    private var itemClickListener: AdapterItemClickListener? = null

    fun setItemClickListener(listener: AdapterItemClickListener) {
        this.itemClickListener = listener
    }

    private var collectAction: (bean: CollectBean, view: CheckBox, position: Int) -> Unit =
        { _, _, _ -> }

    override fun onBindViewHolder(holder: CollectViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<RvItemCollectBinding>(holder.itemView)
        binding?.bean = getItem(position)
        if (itemClickListener != null) {
            val bean = getItem(position)
            if (bean != null) {
                binding?.root?.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("url", bean.link)
                    bundle.putString("title", bean.title)
                    bundle.putInt("articleId", bean.id)
                    bundle.putBoolean("collected", true)
                    bundle.putString("author", bean.author)
                    bundle.putInt("userId", bean.userId)
                    itemClickListener?.itemClickListener()
                        ?.navigate(R.id.action_global_to_webFragment, bundle)
                }
                val collectView = binding?.root?.findViewById<CheckBox>(R.id.collectCollect)
                collectView?.setOnClickListener { _ ->
                    collectAction.invoke(bean, collectView, position)
                }
                binding?.root?.setOnLongClickListener {
                    itemClickListener?.itemLongClickListener(position)
                    return@setOnLongClickListener true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: RvItemCollectBinding =
            DataBindingUtil.inflate(inflater, R.layout.rv_item_collect, parent, false)
        return CollectViewHolder(binding)
    }

    fun setCollectAction(action: (item: CollectBean, view: CheckBox, position: Int) -> Unit) {
        this.collectAction = action
    }

}

class CollectViewHolder(binding: RvItemCollectBinding) : RecyclerView.ViewHolder(binding.root)