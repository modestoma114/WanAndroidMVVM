package me.robbin.wanandroid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * TodoL
 * Created by Robbin on 2020-07-27
 */
@Parcelize
data class TodoBean(
    var completeDate: Long,
    var completeDateStr: String,
    var content: String,
    var date: Long,
    var dateStr: String,
    var id: Int,
    var priority: Int,
    var status: Int,
    var title: String,
    var type: Int,
    var userId: Int
) : Parcelable {
    val hasDone: Boolean
        get() = status == 1

    val whatType: String
        get() = when (type) {
            1 -> "工作"
            2 -> "日常"
            3 -> "娱乐"
            else -> "其他"
        }

    val whatPriority: String
        get() = when (priority) {
            1 -> "紧急"
            else -> "平常"
        }
}