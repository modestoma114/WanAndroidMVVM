package me.robbin.wanandroid.ui.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.ext.html2string
import me.robbin.wanandroid.ext.removeAllBlank

/**
 *
 * Create by Robbin at 2020/7/11
 */
class ArticleAdapter : PagingDataAdapter<ArticleBean, ArticleViewHolder>(POST_COMPARATOR) {

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<ArticleBean>() {
            override fun areItemsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_article, parent, false)
        )
    }

}

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val chapter: AppCompatTextView = itemView.findViewById(R.id.tv_chapter)
    private val author: AppCompatTextView = itemView.findViewById(R.id.tv_author)
    private val title: AppCompatTextView = itemView.findViewById(R.id.tv_title)
    private val desc: AppCompatTextView = itemView.findViewById(R.id.tv_desc)
    private val time: AppCompatTextView = itemView.findViewById(R.id.tv_time)
    private val new: AppCompatTextView = itemView.findViewById(R.id.tv_new)
    private val top: AppCompatTextView = itemView.findViewById(R.id.tv_top)
    private val tag: AppCompatTextView = itemView.findViewById(R.id.tv_tag)

    @SuppressLint("SetTextI18n")
    fun bindTo(article: ArticleBean?) {
        itemView.setOnClickListener {
            Navigation.findNavController(itemView).navigate(R.id.action_global_webView)
        }
        if (article != null) {
            // author
            author.text = if (article.author == "") article.shareUser else article.author
            // new
            new.visibility = if (article.fresh) View.VISIBLE else View.GONE
            // top
            top.visibility = if (article.type == 1) View.VISIBLE else View.GONE
            // tag
            if (article.tags.isNotEmpty()) {
                tag.text = article.tags[0].name
                tag.visibility = View.VISIBLE
            } else {
                tag.visibility = View.GONE
            }
            // time
            time.text = article.niceDate
            // title
            title.text = article.title.html2string()
            // desc
            if (TextUtils.isEmpty(article.desc)) {
                desc.visibility = View.GONE
                title.isSingleLine = false
            } else {
                desc.visibility = View.VISIBLE
                title.isSingleLine = true
                desc.text = article.desc.html2string().removeAllBlank(2)
            }
            // chapter
            chapter.text = "${article.superChapterName}·${article.chapterName}"
            chapter.setOnClickListener {
                chapter.text.toString().toToast()
            }
        }
    }

}
