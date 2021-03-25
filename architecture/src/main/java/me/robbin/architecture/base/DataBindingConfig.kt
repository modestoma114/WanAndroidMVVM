package me.robbin.architecture.base

import android.util.SparseArray
import me.robbin.architecture.model.BaseViewModel

class DataBindingConfig(
    private val layout: Int,
    private val vmVariableId: Int,
    private val stateViewModel: BaseViewModel
) {

    private var bindingParams: SparseArray<Any> = SparseArray()

    fun getLayout(): Int = layout

    fun getVmVariableId(): Int = vmVariableId

    fun getStateViewModel(): BaseViewModel = stateViewModel

    fun getBindingParams(): SparseArray<Any> = bindingParams

    fun addBindingParams(variableId: Int, objezt: Any): DataBindingConfig {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, objezt)
        }
        return this
    }

}