package me.robbin.wanandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.LayoutListFooterViewBinding

/**
 *
 * Create by Robbin at 2020/7/11
 */
class ReposLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<ReposLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: ReposLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ReposLoadStateViewHolder {
        return ReposLoadStateViewHolder.create(parent, retry)
    }

}

class ReposLoadStateViewHolder(
    private val binding: LayoutListFooterViewBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.footerRetryBtn.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error){
            binding.footerErrorMsg.text = loadState.error.localizedMessage
        }
        binding.footerProgressBar.isVisible = loadState is LoadState.Loading
        binding.footerErrorMsg.isVisible = loadState !is LoadState.Loading
        binding.footerRetryBtn.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ReposLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_footer_view, parent, false)
            val binding = LayoutListFooterViewBinding.bind(view)
            return ReposLoadStateViewHolder(binding, retry)
        }
    }

}