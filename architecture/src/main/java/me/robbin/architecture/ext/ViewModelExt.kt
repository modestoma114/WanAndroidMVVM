package me.robbin.architecture.ext

import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
fun <VM> getVMCls(cls: Any): VM {
    return (cls.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}