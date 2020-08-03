package me.robbin.wanandroid.ui.fragment.todo.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.databinding.FragmentTodoDetailBinding
import me.robbin.wanandroid.model.TodoBean
import me.robbin.wanandroid.ui.fragment.todo.viewmodel.TodoDetailViewModel
import java.text.SimpleDateFormat

/**
 *
 * Create by Robbin at 2020/8/1
 */
class TodoDetailFragment : BaseFragment<TodoDetailViewModel, FragmentTodoDetailBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_todo_detail, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    private var bean: TodoBean? = null

    override fun initView(savedInstanceState: Bundle?) {
        arguments?.let {
            bean = it.getParcelable("bean")
        }
        if (bean != null) {
            mViewModel.setValue(bean!!)
        }
    }

    inner class ClickProxy {
        fun back() = nav().navigateUp()

        @SuppressLint("SimpleDateFormat")
        fun setDate() {
            val builder = MaterialDatePicker.Builder.datePicker()
            val today = MaterialDatePicker.todayInUtcMilliseconds()
            builder.setSelection(today)
            val picker = builder.build()
            picker.show(parentFragmentManager, picker.toString())
            picker.addOnPositiveButtonClickListener {
                val date = picker.selection
                val format = SimpleDateFormat("yyyy-MM-dd")
                val dateStr = format.format(date)
                mViewModel.setDate(dateStr)
            }
        }

        fun setPriority() {
            val item = arrayOf("普通", "紧急")
            MaterialAlertDialogBuilder(context)
                .setTitle(resources.getString(R.string.dialog_title_action))
                .setItems(item) { dialog, which ->
                    val priority =
                        when (which) {
                            0 -> 1
                            else -> 1
                        }
                    mViewModel.setPriority(priority)
                    dialog.dismiss()
                }.show()
        }

        fun setType() {
            val item = arrayOf("工作", "日常", "娱乐", "其他")
            MaterialAlertDialogBuilder(context)
                .setTitle(resources.getString(R.string.dialog_title_action))
                .setItems(item) { dialog, which ->
                    val type =
                        when (which) {
                            0 -> 1
                            1 -> 2
                            2 -> 3
                            else -> 4
                        }
                    mViewModel.setType(type)
                    dialog.dismiss()
                }.show()
        }

        fun commit() {
            if (mViewModel.id.value != -1) {
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
            } else {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.dialog_title_commit))
                    .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                        dialog.dismiss()
                    }.setPositiveButton(resources.getString(R.string.accept)) { dialog, _ ->
                        mViewModel.addTodo {
                            dialog.dismiss()
                            nav().navigateUp()
                        }
                    }.show()
            }
        }

    }

}