package me.robbin.wanandroid.ui.fragment.home.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.listener.AdapterItemClickListener
import me.robbin.wanandroid.databinding.LayoutBannerBinding
import me.robbin.wanandroid.databinding.RvItemArticleBinding
import me.robbin.wanandroid.model.ArticleBean
import me.robbin.wanandroid.model.BannerBean
import me.robbin.wanandroid.ui.fragment.common.adapter.ArticleViewHolder

/**
 *
 * Create by Robbin at 2020/8/3
 */
class HomeAdapter(private val context: Context) :
    PagingDataAdapter<ArticleBean, RecyclerView.ViewHolder>(HOME_COMPARATOR) {

    companion object {

        private const val ITEM_TYPE_HEADER = 99
        private const val ITEM_TYPE_FOOTER = 100

        val HOME_COMPARATOR = object : DiffUtil.ItemCallback<ArticleBean>() {
            override fun areItemsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_HEADER -> {
                val inflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding: LayoutBannerBinding =
                    DataBindingUtil.inflate(inflater, R.layout.layout_banner, parent, false)
                BannerViewHolder(binding, context)
            }
            else -> {
                val inflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding: RvItemArticleBinding =
                    DataBindingUtil.inflate(inflater, R.layout.rv_item_article, parent, false)
                ArticleViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_TYPE_HEADER
            itemCount - 1 -> ITEM_TYPE_FOOTER
            else -> super.getItemViewType(position)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> {
                val binding = DataBindingUtil.getBinding<RvItemArticleBinding>(holder.itemView)
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
                        binding?.root?.setOnLongClickListener {
                            itemClickListener?.itemLongClickListener(position)
                            return@setOnLongClickListener true
                        }
                        val collectView = binding?.root?.findViewById<CheckBox>(R.id.collectArticle)
                        collectView?.setOnClickListener { _ ->
                            collectAction.invoke(bean, collectView, position)
                        }
                        val author = binding?.root?.findViewById<TextView>(R.id.tv_author)
                        author?.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putInt("userId", bean.userId)
                            itemClickListener?.itemClickListener()
                                ?.navigate(R.id.action_global_to_profile, bundle)
                        }
                        val chapter = binding?.root?.findViewById<TextView>(R.id.tv_chapter)
                        chapter?.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("superChapterName", bean.superChapterName)
                            bundle.putInt("superChapterId", bean.realSuperChapterId)
                            bundle.putInt("chapterId", bean.chapterId)
                            itemClickListener?.itemClickListener()
                                ?.navigate(R.id.action_global_to_chapterArticles, bundle)
                        }
                    }
                }
            }
            is BannerViewHolder -> {
                holder.bind(getItem(position)?.bannerList)
            }
        }
    }

    private var itemClickListener: AdapterItemClickListener? = null

    fun setItemClickListener(listener: AdapterItemClickListener) {
        this.itemClickListener = listener
    }

    private var collectAction: (bean: ArticleBean, view: CheckBox, position: Int) -> Unit =
        { _, _, _ -> }

    fun setCollectAction(action: (item: ArticleBean, view: CheckBox, position: Int) -> Unit) {
        this.collectAction = action
    }

}

class BannerViewHolder(private val binding: LayoutBannerBinding, private val mContext: Context) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(banners: List<BannerBean>?) {
        val banner = binding.banner
        banner.run {
            adapter = object : BannerImageAdapter<BannerBean>(banners) {
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: BannerBean?,
                    position: Int,
                    size: Int
                ) {
                    holder?.imageView?.load(data?.url)
                }
            }
            addBannerLifecycleObserver(mContext as LifecycleOwner)
            setIndicator(CircleIndicator(mContext))
        }
    }

}