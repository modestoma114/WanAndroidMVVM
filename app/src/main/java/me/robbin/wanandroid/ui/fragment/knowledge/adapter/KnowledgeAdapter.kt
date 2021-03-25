package me.robbin.wanandroid.ui.fragment.knowledge.adapter

import android.os.Bundle
import androidx.navigation.NavController
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import me.robbin.wanandroid.R
import me.robbin.wanandroid.model.Chapter
import me.robbin.wanandroid.databinding.RvItemKnowledgeBinding
import java.util.*

/**
 * 体系列表适配器
 * Create by Robbin at 2020/7/15
 */
class KnowledgeAdapter :
    BaseQuickAdapter<Chapter, BaseDataBindingHolder<RvItemKnowledgeBinding>>(R.layout.rv_item_knowledge) {

    // 当前模块的子模块列表
    private val chipItemCaches: Queue<Chip> = LinkedList()

    // 点击事件
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

    @Suppress("DEPRECATION")
    override fun convert(holder: BaseDataBindingHolder<RvItemKnowledgeBinding>, item: Chapter) {
        val binding = holder.dataBinding
        if (binding != null) {
            binding.tvChapterName.text = item.name
            for (index in item.children.indices) {
                val chip = createItemChip()
                chip.text = item.children[index].name
                chip.isCheckable = false
                chip.isCloseIconVisible = false
                chip.setTextColor(context.resources.getColor(R.color.textSecondary))
                chip.setChipBackgroundColorResource(R.color.bgThird)
                chip.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putParcelable("data", item)
                    bundle.putInt("index", index)
                    onItemChipClickListener?.setNavController()
                        ?.navigate(R.id.action_global_to_chapterArticles, bundle)
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