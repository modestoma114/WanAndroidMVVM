package me.robbin.wanandroid.data.bean

/**
 * Created by Robbin on 2020-07-27
 */
data class TodoBean(
    var completeDate: Any,
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
) {
    val hasDone: Boolean
        get() = status == 1

    val whatType: String
        get() = when (type) {
            1 -> "工作"
            2 -> "日常"
            3 -> "娱乐"
            else -> "其他"
        }
}