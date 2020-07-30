package me.robbin.wanandroid.ui.fragment.me.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.CollectBean
import me.robbin.wanandroid.databinding.RvItemCollectBinding

/**
 * 收藏列表适配器
 * Create by Robbin at 2020/7/26
 */
class CollectAdapter(private val context: Context) :
    PagingDataAdapter<CollectBean, CollectViewHolder>(
        COLLECT_COMPARATOR
    ) {

    companion object {
        val COLLECT_COMPARATOR = object : DiffUtil.ItemCallback<CollectBean>() {
            override fun areItemsTheSame(oldItem: CollectBean, newItem: CollectBean): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CollectBean, newItem: CollectBean): Boolean =
                oldItem == newItem
        }
    }

    interface OnArticleItemClickListener {
        fun setNavController(): NavController
    }

    private var listener: OnArticleItemClickListener? = null

    fun setOnArticleItemClickListener(listener: OnArticleItemClickListener) {
        this.listener = listener
    }

    private var view: View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: RvItemCollectBinding =
            DataBindingUtil.inflate(inflater, R.layout.rv_item_collect, parent, false)
        view = binding.root
        return CollectViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: CollectViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<RvItemCollectBinding>(holder.itemView)
        binding?.bean = getItem(position)
        binding?.route = RouteClick()
    }

    inner class RouteClick {
        fun goWeb(bean: CollectBean) {
            val bundle = Bundle()
            bundle.putString("url", bean.link)
            bundle.putString("title", bean.title)
            bundle.putInt("articleId", bean.id)
            bundle.putBoolean("collected", true)
            bundle.putString("author", bean.author)
            bundle.putInt("userId", bean.userId)
            view?.let {
                listener?.setNavController()
                    ?.navigate(R.id.action_global_to_webFragment, bundle)
            }
        }

        fun goChapter() {}

    }

}

class CollectViewHolder(binding: RvItemCollectBinding) : RecyclerView.ViewHolder(binding.root)