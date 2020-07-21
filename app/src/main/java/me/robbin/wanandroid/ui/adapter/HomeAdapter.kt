package me.robbin.wanandroid.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.databinding.RvItemArticleBinding

/**
 *
 * Create by Robbin at 2020/7/19
 */
//class HomeAdapter : PagingDataAdapter<ArticleBean, RecyclerView.ViewHolder>(POST_COMPARATOR) {
//
//    override fun getItemViewType(position: Int): Int {
//        return when (position == 0) {
//            true -> ITEM_TYPE_HEADER
//            false -> super.getItemViewType(position)
//        }
//    }
//
//    private var headView: View? = null
//
//    fun setHeadView(headView: View) {
//        this.headView = headView
//    }
//
//    companion object {
//        val POST_COMPARATOR = object : DiffUtil.ItemCallback<ArticleBean>() {
//            override fun areItemsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean =
//                oldItem.id == newItem.id
//
//            override fun areContentsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean =
//                oldItem == newItem
//        }
//        private const val ITEM_TYPE_HEADER = 99
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (getItemViewType(position) == ITEM_TYPE_HEADER)
//            return
//        else {
//            (holder as ArticleViewHolder).bindTo(getItem(position - 1))
//            holder.itemView.setOnClickListener {
//                val bundle = Bundle()
//                bundle.putParcelable("data", getItem(position - 1))
//                Navigation.findNavController(holder.itemView)
//                    .navigate(R.id.action_global_to_webFragment, bundle)
//            }
//        }
//        when (holder) {
//            is HeadViewHolder -> {
//            }
//            is ArticleViewHolder -> {
//                holder.bindTo(getItem(position - 1))
//                holder.itemView.setOnClickListener {
//                    val bundle = Bundle()
//                    bundle.putParcelable("data", getItem(position - 1))
//                    Navigation.findNavController(holder.itemView)
//                        .navigate(R.id.action_global_webView, bundle)
//                }
//            }
//        }
//    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            ITEM_TYPE_HEADER -> HeadViewHolder(parent)
////            else -> {
////                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
////                val binding: RvItemArticleBinding =
////                    DataBindingUtil.inflate(inflater, R.layout.rv_item_article, parent, false)
////            }
//        }
//    }
//
//}
//
//class HeadViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
//    LayoutInflater.from(parent.context)
//        .inflate(R.layout.rv_home_header, parent, false)
//) {
//
//}