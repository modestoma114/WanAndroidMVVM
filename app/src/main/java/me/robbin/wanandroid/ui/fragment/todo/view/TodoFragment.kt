package me.robbin.wanandroid.ui.fragment.todo.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_todo_list.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.setStatusBarLightMode
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.databinding.FragmentTodoListBinding
import me.robbin.wanandroid.ui.fragment.common.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.todo.adapter.TodoAdapter
import me.robbin.wanandroid.ui.fragment.todo.viewmodel.TodoViewModel

/**
 * TodoL Fragment
 * Create by Robbin at 2020/7/14
 */
class TodoFragment : BaseFragment<TodoViewModel, FragmentTodoListBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_todo_list, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    private val todoAdapter by lazy { TodoAdapter(requireContext()) }

    private var todoJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        initAdapter()
        refreshTodo.setOnRefreshListener { refreshData() }
        btnEmpty.setOnClickListener { todoAdapter.retry() }
    }

    override fun createObserver() {
        appViewModel.isLogin.observe(viewLifecycleOwner, Observer { isLogin ->
            if (isLogin) {
                refreshTodo.isEnabled = true
                getTodo()
            } else {
                rlTodo.visibility = View.GONE
                ivEmpty.visibility = View.VISIBLE
                btnEmpty.visibility = View.VISIBLE
                refreshTodo.isEnabled = false
                btnEmpty.text = "请先登录"
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
        todoAdapter.setCollectAction { bean, view, position ->
            if (view.isChecked) {
                mViewModel.doneTodo(bean.id, 1) {
                    bean.status = 1
                    view.isChecked = true
                    todoAdapter.notifyItemRemoved(position)
                }
            } else {
                mViewModel.doneTodo(bean.id, 0) {
                    bean.status = 0
                    view.isChecked = false
                    todoAdapter.notifyItemRemoved(position)
                }
            }
        }
        todoAdapter.setClickAction { bean, _, _ ->
            val bundle = Bundle()
            bundle.putParcelable("bean", bean)
            nav().navigate(R.id.action_main_to_todo_detail, bundle)
        }
        todoAdapter.setLongClickAction { _, _, position ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.dialog_title_delete))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }.setPositiveButton(resources.getString(R.string.accept)) { dialog, _ ->
                    mViewModel.deleteTodo(position) {
                        todoAdapter.notifyItemRemoved(position)
                        dialog.dismiss()
                        nav().navigateUp()
                    }
                }.show()
        }
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
                btnEmpty.text = (errorState.error as EmptyException).errMsg
            } else {
                errorState?.let {
                    "\uD83D\uDE28 Wooops: ${it.error}".toToast()
                }
            }
        }
    }

    private fun getTodo() {
        todoJob?.cancel()
        todoJob = lifecycleScope.launchWhenResumed {
            mViewModel.getTodoList().collectLatest {
                todoAdapter.submitData(it)
            }
        }
    }

    private fun refreshData() {
        todoAdapter.refresh()
    }

    override fun onResume() {
        super.onResume()
        setStatusBarLightMode(!appViewModel.isNightMode.value!!)
    }

    inner class ClickProxy : Toolbar.OnMenuItemClickListener {
        fun addTodo() {
            nav().navigate(R.id.action_main_to_todo_detail)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            return if (appViewModel.isLogin.value == true) {
                when (item?.itemId) {
                    R.id.tab_done -> {
                        todoJob?.cancel()
                        todoJob = lifecycleScope.launchWhenResumed {
                            mViewModel.getDoneTodoList().collectLatest {
                                todoAdapter.submitData(it)
                            }
                        }
                        true
                    }
                    R.id.tab_todo -> {
                        todoJob?.cancel()
                        todoJob = lifecycleScope.launchWhenResumed {
                            mViewModel.getTodoList().collectLatest {
                                todoAdapter.submitData(it)
                            }
                        }
                        true
                    }
                    else -> false
                }
            } else {
                false
            }
        }

    }

}