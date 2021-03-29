package me.robbin.wanandroid.model

/**
 * TodoL
 * Created by Robbin on 2020-07-27
 */
data class Todo(
    private var completeDate: Long,
    private var completeDateStr: String,
    private var content: String,
    private var date: Long,
    private var dateStr: String,
    private var id: Int,
    private var priority: Int,
    private var status: Int,
    private var title: String,
    private var type: Int,
    private var userId: Int,
    private var deleteFlag: Boolean = false
) {

    val whatType: String
        get() = when (type) {
            1 -> "工作"
            2 -> "日常"
            3 -> "娱乐"
            else -> "其他"
        }

    val whatPriority: String
        get() = when (priority) {
            1 -> "普通"
            else -> "紧急"
        }
}