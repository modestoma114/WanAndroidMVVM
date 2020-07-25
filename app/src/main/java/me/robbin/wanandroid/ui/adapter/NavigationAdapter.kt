package me.robbin.wanandroid.ui.adapter

import android.os.Bundle
import androidx.navigation.NavController
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.NavigationBean
import me.robbin.wanandroid.databinding.RvItemKnowledgeBinding
import java.util.*

/**
 *
 * Create by Robbin at 2020/7/21
 */
class NavigationAdapter :
    BaseQuickAdapter<NavigationBean, BaseDataBindingHolder<RvItemKnowledgeBinding>>(R.layout.rv_item_knowledge) {

    private val chipItemCaches: Queue<Chip> = LinkedList()
    private var onItemChipClickListener: OnItemChipClickListener? = null

    override fun onViewRecycled(holder: BaseDataBindingHolder<RvItemKnowledgeBinding>) {
        val chipGroup = holder.getView<ChipGroup>(R.id.chipGroup)
        for (i in 0 until chipGroup.childCount) {
            chipItemCaches.offer(chipGroup.getChildAt(i) as Chip)
        }
        chipGroup.removeAllViews()
    }

    fun setOnItemChipClickListener(listener: OnItemChipClickListener) {
        this.onItemChipClickListener = listener
    }

    override fun convert(
        holder: BaseDataBindingHolder<RvItemKnowledgeBinding>,
        item: NavigationBean
    ) {
        val binding = holder.dataBinding
        if (binding != null) {
            binding.tvChapterName.text = item.name
            for (index in item.articles.indices) {
                val chip = createItemChip()
                chip.text = item.articles[index].title
                chip.isCheckable = false
                chip.isCloseIconVisible = false
                chip.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putParcelable("article", item.articles[index])
                    onItemChipClickListener?.setNavController()
                        ?.navigate(R.id.action_global_to_webFragment, bundle)
                }
                binding.chipGroup.addView(chip)
            }
        }
    }

    private fun createItemChip(): Chip {
        val chip = chipItemCaches.poll()
        if (chip != null) {
            return chip
        }
        return Chip(context)
    }

    interface OnItemChipClickListener {
        fun setNavController(): NavController
    }

}