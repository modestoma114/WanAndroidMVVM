package me.robbin.wanandroid.model

import android.os.Parcelable
//import androidx.room.Entity
//import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * 文章
 * Created by Robbin on 2020-07-11
 */
@Parcelize
//@Entity(tableName = "articles")
data class ArticleBean(
    var apkLink: String,
    var audit: Int,
    var author: String,
    var canEdit: Boolean,
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Int,
    var desc: String,
    var descMd: String,
    var envelopePic: String,
    var fresh: Boolean,
//    @PrimaryKey
    var id: Int,
    var link: String,
    var niceDate: String,
    var niceShareDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var realSuperChapterId: Int,
    var selfVisible: Int,
    var shareDate: Long,
    var shareUser: String,
    var superChapterId: Int,
    var superChapterName: String,
    var title: String,
    var type: Int,
    var userId: Int,
    var visible: Int,
    var zan: Int,
    var tags: List<TagsBean>
) : Parcelable