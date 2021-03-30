package me.robbin.wanandroid.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.RvItemArticlesBinding
import me.robbin.wanandroid.model.Article

class ArticlesAdapter : PagingDataAdapter<Article, ArticleViewHolder>(ARTICLE_COMPARATOR) {

    companion object {
        val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.sameId(newItem)
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.sameContent(newItem)
            }
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<RvItemArticlesBinding>(holder.itemView)
        binding?.bean = getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RvItemArticlesBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.rv_item_articles, parent, false)
        return ArticleViewHolder(binding)
    }

}

class ArticleViewHolder(binding: RvItemArticlesBinding) : RecyclerView.ViewHolder(binding.root)