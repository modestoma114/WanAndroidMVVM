package me.robbin.wanandroid.ui.fragment.todo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.RvItemTodoBinding
import me.robbin.wanandroid.model.TodoBean

/**
 * TodoL 列表适配器
 * Create by Robbin at 2020/7/27
 */
class TodoAdapter(private val context: Context) :
    PagingDataAdapter<TodoBean, TodoViewHolder>(TODO_COMPARATOR) {

    companion object {
        val TODO_COMPARATOR = object : DiffUtil.ItemCallback<TodoBean>() {
            override fun areContentsTheSame(oldItem: TodoBean, newItem: TodoBean): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: TodoBean, newItem: TodoBean): Boolean =
                oldItem.id == newItem.id
        }
    }

    private var changeStatusClick: (bean: TodoBean) -> Unit =
        { _ -> }

    private var clickAction: (bean: TodoBean, view: View, position: Int) -> Unit = { _, _, _ -> }

    private var longClickAction: (bean: TodoBean, view: View, position: Int) -> Unit =
        { _, _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<RvItemTodoBinding>(
            inflater,
            R.layout.rv_item_todo,
            parent,
            false
        )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<RvItemTodoBinding>(holder.itemView)
        binding?.bean = getItem(position)
        val bean = getItem(position)
        val view = binding?.root
        val check = view?.findViewById<ImageView>(R.id.btnChangeStatus)
        if (view != null && check != null && bean != null) {
            view.setOnClickListener { clickAction.invoke(bean, view, position) }
            view.setOnLongClickListener {
                longClickAction.invoke(bean, view, position)
                true
            }
            check.setOnClickListener { changeStatusClick.invoke(bean) }
            if (bean.deleteFlag) {
                view.visibility = View.GONE
                view.layoutParams = ViewGroup.LayoutParams(0, 0)
            }
        }
    }

    fun getItemByPosition(position: Int): TodoBean? = getItem(position)

    fun setChangeStatusClick(action: (bean: TodoBean) -> Unit) {
        this.changeStatusClick = action
    }

    fun setClickAction(action: (bean: TodoBean, view: View, position: Int) -> Unit) {
        this.clickAction = action
    }

    fun setLongClickAction(action: (bean: TodoBean, view: View, position: Int) -> Unit) {
        this.longClickAction = action
    }

}

class TodoViewHolder(binding: RvItemTodoBinding) : RecyclerView.ViewHolder(binding.root)