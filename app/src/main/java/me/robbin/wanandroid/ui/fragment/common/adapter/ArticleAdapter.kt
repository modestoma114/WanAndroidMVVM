package me.robbin.wanandroid.ui.fragment.common.adapter

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
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.databinding.RvItemArticleBinding

/**
 * 文章列表适配器
 * Create by Robbin at 2020/7/11
 */
class ArticleAdapter(private val context: Context) :
    PagingDataAdapter<ArticleBean, ArticleViewHolder>(POST_COMPARATOR) {

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<ArticleBean>() {
            override fun areItemsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean =
                oldItem == newItem
        }
    }

    private var itemClickListener: AdapterItemClickListener? = null

    fun setItemClickListener(listener: AdapterItemClickListener) {
        this.itemClickListener = listener
    }

    private var collectAction: (bean: ArticleBean, view: CheckBox, position: Int) -> Unit =
        { _, _, _ -> }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<RvItemArticleBinding>(holder.itemView)
        binding?.route = this.RouteClick()
        binding?.bean = getItem(position)
        if (itemClickListener != null) {
            val bean = getItem(position)
            if (bean != null) {
                binding?.root?.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("url", bean.link)
                    bundle.putString("title", bean.title)
                    bundle.putInt("articleId", bean.id)
                    bundle.putBoolean("collected", bean.collect)
                    bundle.putString("author", bean.author)
                    bundle.putInt("userId", bean.userId)
                    itemClickListener?.itemClickListener()
                        ?.navigate(R.id.action_global_to_webFragment, bundle)
                }
                val collectView = binding?.root?.findViewById<CheckBox>(R.id.collectArticle)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: RvItemArticleBinding =
            DataBindingUtil.inflate(inflater, R.layout.rv_item_article, parent, false)
        return ArticleViewHolder(binding)
    }

    fun getData(position: Int): ArticleBean? = getItem(position)

    fun setCollectAction(action: (item: ArticleBean, view: CheckBox, position: Int) -> Unit) {
        this.collectAction = action
    }

    inner class RouteClick {
        fun goAuthorProfile(bean: ArticleBean) {
            val bundle = Bundle()
            bundle.putInt("userId", bean.userId)
            itemClickListener?.itemClickListener()
                ?.navigate(R.id.action_global_to_profile, bundle)

        }

        fun goChapter(bean: ArticleBean) {
            val bundle = Bundle()
            bundle.putString("superChapterName", bean.superChapterName)
            bundle.putInt("superChapterId", bean.realSuperChapterId)
            bundle.putInt("chapterId", bean.chapterId)
            itemClickListener?.itemClickListener()
                ?.navigate(R.id.action_global_to_chapterArticles, bundle)

        }

    }

}

class ArticleViewHolder(binding: RvItemArticleBinding) : RecyclerView.ViewHolder(binding.root)
