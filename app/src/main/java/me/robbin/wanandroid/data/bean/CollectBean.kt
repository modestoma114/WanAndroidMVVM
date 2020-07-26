package me.robbin.wanandroid.data.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Robbin on 2020-07-26
 */
@Parcelize
data class CollectBean(
    var author: String,
    var chapterId: Int,
    var chapterName: String,
    var courseId: Int,
    var desc: String,
    var envelopePic: String,
    var id: Int,
    var link: String,
    var niceDate: String,
    var origin: String,
    var originId: Int,
    var publishTime: Long,
    var title: String,
    var userId: Int,
    var visible: Int,
    var zan: Int
) : Parcelable