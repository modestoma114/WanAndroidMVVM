package me.robbin.wanandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R

/**
 *
 * Create by Robbin at 2020/7/11
 */
//class PostsLoadStateAdapter(
//    private val adapter: ArticleAdapter
//) : LoadStateAdapter<NetworkStateItemViewHolder>() {
//    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
//        holder.bindTo(loadState)
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        loadState: LoadState
//    ): NetworkStateItemViewHolder {
//        return NetworkStateItemViewHolder(parent) { adapter.retry() }
//    }
//}
//
//class NetworkStateItemViewHolder(
//    parent: ViewGroup,
//    private val retryCallback: () -> Unit
//) : RecyclerView.ViewHolder(
//    LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
//) {
//    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)
//    private val errorMsg = itemView.findViewById<TextView>(R.id.error_msg)
//    private val retry = itemView.findViewById<Button>(R.id.retry_button)
//        .also {
//            it.setOnClickListener { retryCallback() }
//        }
//
//    fun bindTo(loadState: LoadState) {
//        progressBar.isVisible = loadState is LoadState.Loading
//        retry.isVisible = loadState is Error
//        errorMsg.isVisible = !(loadState as? Error)?.error?.message.isNullOrBlank()
//        errorMsg.text = (loadState as? Error)?.error?.message
//    }
//}