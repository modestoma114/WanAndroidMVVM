package me.robbin.wanandroid.data.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 标签
 * Create by Robbin at 2020/7/11
 */
@Parcelize
data class TagsBean(var name: String, var url: String): Parcelable