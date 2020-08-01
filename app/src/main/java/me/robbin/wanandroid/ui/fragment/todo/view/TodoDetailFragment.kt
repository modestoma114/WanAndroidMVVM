package me.robbin.wanandroid.ui.fragment.todo.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_todo_detail.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.data.bean.TodoBean
import me.robbin.wanandroid.databinding.FragmentTodoDetailBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.todo.viewmodel.TodoDetailViewModel

/**
 *
 * Create by Robbin at 2020/8/1
 */
class TodoDetailFragment : BaseFragment<TodoDetailViewModel, FragmentTodoDetailBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_todo_detail, BR.viewModel, mViewModel)
    }

    private var bean: TodoBean? = null

    override fun initView(savedInstanceState: Bundle?) {
        arguments?.let {
            bean = it.getParcelable("bean")
        }
        if (bean != null) {
            mViewModel.bean.value = bean
        } else {
            "Woops! 出现错误了".toToast()
            nav().navigateUp()
        }
        btnTodoDetailEdit.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.dialog_title_edit))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }.setPositiveButton(resources.getString(R.string.accept)) { dialog, _ ->
                    mViewModel.updateTodo {
                        dialog.dismiss()
                        nav().navigateUp()
                    }
                }.show()
        }
        btnTodoDetailDelete.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.dialog_title_delete))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }.setPositiveButton(resources.getString(R.string.accept)) { dialog, _ ->
                    mViewModel.deleteTodo {
                        dialog.dismiss()
                        nav().navigateUp()
                    }
                }.show()
        }
    }

    override fun createObserver() {
        mViewModel.back.observe(viewLifecycleOwner, Observer {
            if (it) nav().navigateUp()
        })
    }

}