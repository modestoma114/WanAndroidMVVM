package me.robbin.wanandroid.ui.fragment.todo.view

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_todo.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.event.bus.TodoBus
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.databinding.FragmentTodoBinding
import me.robbin.wanandroid.ui.fragment.common.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.todo.adapter.TodoAdapter
import me.robbin.wanandroid.ui.fragment.todo.viewmodel.TodoViewModel

/**
 * TodoL Fragment
 * Create by Robbin at 2020/7/14
 */
class TodoFragment : BaseFragment<TodoViewModel, FragmentTodoBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_todo, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    private var emptyStr = ""

    private val todoAdapter by lazy { TodoAdapter(requireContext()) }

    private var todoJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        emptyStr = resources.getString(R.string.text_empty_retry)
        initAdapter()
        btnEmpty.setOnClickListener { todoAdapter.retry() }
        refreshTodo.setOnRefreshListener { todoAdapter.refresh() }
    }

    override fun initData() {
        todoJob?.cancel()
        todoJob = lifecycleScope.launchWhenResumed {
            mViewModel.getTodoList().collectLatest {
                todoAdapter.submitData(it)
            }
        }
    }

    override fun createObserver() {
        eventViewModel.changeTodoStatus.observe(viewLifecycleOwner, Observer {
            for (i in 0 until todoAdapter.itemCount) {
                if (todoAdapter.getItemByPosition(i)?.id == it.id) {
                    todoAdapter.getItemByPosition(i)?.status = it.status
                    todoAdapter.notifyItemChanged(i)
                    break
                }
            }
        })
        eventViewModel.deleteTodo.observe(viewLifecycleOwner, Observer {
            for (i in 0 until todoAdapter.itemCount) {
                if (todoAdapter.getItemByPosition(i)?.id == it.id) {
                    todoAdapter.run {
                        getItemByPosition(i)?.deleteFlag = true
                        notifyItemChanged(i)
                    }
                    break
                }
            }
        })
        eventViewModel.addTodo.observe(viewLifecycleOwner, Observer {
            todoAdapter.refresh()
        })
        eventViewModel.changeTodo.observe(viewLifecycleOwner, Observer {
            for (i in 0 until todoAdapter.itemCount) {
                if (todoAdapter.getItemByPosition(i)?.id == it.id) {
                    todoAdapter.run {
                        getItemByPosition(i).run {
                            this?.title = it.bean.title
                            this?.content = it.bean.content
                            this?.dateStr = it.bean.dateStr
                            this?.type = it.bean.type
                            this?.priority = it.bean.priority
                        }
                        notifyItemChanged(i)
                    }
                    break
                }
            }
        })
    }

    private fun initAdapter() {
        rlTodo.adapter =
            todoAdapter.withLoadStateFooter(PagingLoadStateAdapter { todoAdapter.retry() })
        lifecycleScope.launchWhenCreated {
            todoAdapter.loadStateFlow.collectLatest { loadState ->
                refreshTodo.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }
        todoAdapter.setClickAction { bean, _, _ ->
            val bundle = Bundle()
            bundle.putParcelable("bean", bean)
            nav().navigate(R.id.action_todo_to_todoDetail, bundle)
        }
        todoAdapter.setLongClickAction { bean, _, _ ->
            MaterialAlertDialogBuilder(context)
                .setTitle(resources.getString(R.string.dialog_title_delete))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }.setPositiveButton(resources.getString(R.string.accept)) { dialog, _ ->
                    mViewModel.deleteTodo(bean.id) {
                        eventViewModel.deleteTodo.postValue(TodoBus(bean.id, bean.status))
                        dialog.dismiss()
                    }
                }.show()
        }
        todoAdapter.setChangeStatusClick { bean ->
            mViewModel.changeTodoStatus(bean.id, (bean.status + 1) % 2) {
                eventViewModel.changeTodoStatus.postValue(
                    TodoBus(
                        bean.id,
                        (bean.status + 1) % 2
                    )
                )
            }
        }
        // 界面状态绑定
        todoAdapter.addLoadStateListener { loadState ->
            rlTodo.isVisible = loadState.refresh is LoadState.NotLoading
            progressLoading.isVisible = loadState.refresh is LoadState.Loading
            ivEmpty.isVisible = loadState.refresh is LoadState.Error
            btnEmpty.isVisible = loadState.refresh is LoadState.Error
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error
            if (errorState?.error is EmptyException) {
                btnEmpty.text = emptyStr
            } else {
                errorState?.let {
                    "\uD83D\uDE28 Wooops: ${it.error}".toToast()
                }
            }
        }
    }

    inner class ClickProxy {
        fun addTodo() {
            nav().navigate(R.id.action_todo_to_todoDetail)
        }
    }

}