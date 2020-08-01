package me.robbin.wanandroid.ui.fragment.todo.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.fragment_todo.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.setStatusBarLightMode
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.databinding.FragmentTodoBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.common.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.ui.fragment.todo.adapter.TodoAdapter
import me.robbin.wanandroid.ui.fragment.todo.viewmodel.TodoViewModel
import java.text.SimpleDateFormat

/**
 * TodoL Fragment
 * Create by Robbin at 2020/7/14
 */
class TodoFragment : BaseFragment<TodoViewModel, FragmentTodoBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_todo, BR.viewModel, mViewModel)
    }

    private val todoAdapter by lazy { TodoAdapter(requireContext()) }

    private val bottomSheet by lazy { BottomSheetDialog(requireContext()) }
    private var behavior: BottomSheetBehavior<View>? = null

    private var todoJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        initAdapter()
        refreshTodo.setOnRefreshListener { refreshData() }
        btnEmpty.setOnClickListener { todoAdapter.retry() }
        initBottomSheet()
        btnAddTodo.setOnClickListener {
            behavior?.state = BottomSheetBehavior.STATE_EXPANDED
            bottomSheet.show()
        }
    }

    override fun createObserver() {
        appViewModel.isLogin.observe(viewLifecycleOwner, Observer { isLogin ->
            if (isLogin) {
                refreshTodo.isEnabled = true
                todoJob?.cancel()
                todoJob = lifecycleScope.launchWhenResumed {
                    mViewModel.getTodoList().collectLatest {
                        todoAdapter.submitData(it)
                    }
                }
            } else {
                rlTodo.visibility = View.GONE
                ivEmpty.visibility = View.VISIBLE
                btnEmpty.visibility = View.VISIBLE
                refreshTodo.isEnabled = false
                btnEmpty.text = "请先登录"
            }
        })
    }

    /**
     * 初始化 BottomSheetDialog
     * Create by Robbin at 2020/7/31
     */
    @SuppressLint("SimpleDateFormat")
    private fun initBottomSheet() {
        val addTodoView = View.inflate(requireContext(), R.layout.layout_add_todo, null)
        val todoTitle = addTodoView.findViewById<AppCompatEditText>(R.id.todoTitle)
        val todoDetail = addTodoView.findViewById<AppCompatEditText>(R.id.todoDetail)
        val send = addTodoView.findViewById<AppCompatImageView>(R.id.todoSend)
        val addDetail = addTodoView.findViewById<MaterialTextView>(R.id.todoAddDetail)
        val chooseTime = addTodoView.findViewById<MaterialTextView>(R.id.todoChooseTime)
        bottomSheet.setContentView(addTodoView)
        addDetail.setOnClickListener {
            todoDetail.visibility = View.VISIBLE
        }
        var date: Long? = 0L
        chooseTime.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val today = MaterialDatePicker.todayInUtcMilliseconds()
            builder.setSelection(today)
            val picker = builder.build()
            picker.show(parentFragmentManager, picker.toString())
            picker.addOnPositiveButtonClickListener {
                date = picker.selection
                chooseTime.text = picker.headerText
            }
        }
        send.setOnClickListener {
            val title = todoTitle.text.toString()
            val detail = todoDetail.text.toString()
            when {
                title.isBlank() -> "请填写标题".toToast()
                date == 0L -> "请选择时间".toToast()
                else -> {
                    val format = SimpleDateFormat("yyyy-MM-dd")
                    val dateStr = format.format(date)
                    mViewModel.addTodo(title, detail, dateStr, 1, 1) {
                        todoTitle.text?.clear()
                        todoDetail.text?.clear()
                        date = 0L
                        chooseTime.text = resources.getString(R.string.text_choose_time)
                        todoDetail.visibility = View.GONE
                        todoAdapter.refresh()
                        bottomSheet.dismiss()
                    }
                }
            }
        }
        behavior = BottomSheetBehavior.from(addTodoView.parent as View)
    }

    private fun initAdapter() {
        rlTodo.adapter =
            todoAdapter.withLoadStateFooter(PagingLoadStateAdapter { todoAdapter.retry() })
        lifecycleScope.launchWhenCreated {
            todoAdapter.loadStateFlow.collectLatest { loadState ->
                refreshTodo.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }
        todoAdapter.setClickAction { bean, view, position ->
            if (view.isChecked) {
                mViewModel.doneTodo(bean.id, 1) {
                    bean.status = 1
                    view.isChecked = true
                    todoAdapter.notifyItemChanged(position)
                }
            } else {
                mViewModel.doneTodo(bean.id, 0) {
                    bean.status = 0
                    view.isChecked = false
                    todoAdapter.notifyItemChanged(position)
                }
            }
        }
        todoAdapter.setGoDetail {
            val bundle = Bundle()
            bundle.putParcelable("bean", it)
            nav().navigate(R.id.action_main_to_todo_detail, bundle)
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

    private fun refreshData() {
        todoAdapter.refresh()
    }

    override fun onResume() {
        super.onResume()
        setStatusBarLightMode(!appViewModel.isNightMode.value!!)
    }

}