package me.robbin.wanandroid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ArticleBean

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
    val chapter: AppCompatTextView = itemView.findViewById(R.id.tv_chapter)
    val author: AppCompatTextView = itemView.findViewById(R.id.tv_author)
    val title: AppCompatTextView = itemView.findViewById(R.id.tv_title)
    val desc: AppCompatTextView = itemView.findViewById(R.id.tv_desc)
    val time: AppCompatTextView = itemView.findViewById(R.id.tv_time)
    val new: AppCompatTextView = itemView.findViewById(R.id.tv_new)
    val top: AppCompatTextView = itemView.findViewById(R.id.tv_top)
    val tag: AppCompatTextView = itemView.findViewById(R.id.tv_tag)

    fun bindTo(article: ArticleBean?) {
        if (article?.type == 0) {
            top.visibility = View.GONE
        }
        if (article?.fresh == false) {
            new.visibility = View.GONE
        }
        chapter.text = "${article?.superChapterName}·${article?.chapterName}"
        author.text = if (article?.author == "") article.shareUser else article?.author
        title.text = article?.title
        if (desc.text == "") {
            desc.visibility = View.GONE
        } else {
            desc.text = article?.desc
        }
        time.text = article?.niceDate
        if (article?.tags != null && article.tags.isNotEmpty()) {
            tag.text = article.tags[0].name
        } else {
            tag.visibility = View.GONE
        }
    }

}