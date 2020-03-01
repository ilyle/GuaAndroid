package com.teligen.guaandroid.bean

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ArticleData(
    var data: Data?,
    var errorCode: Int,
    var errorMsg: String?
)

data class Data(
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int,
    var datas: ArrayList<Article>?
)

/**
 * 单个文章
 */
@Entity
class Article() : Parcelable {
    var apkLink: String? = null
    var author: String? = null
    var chapterId: Int? = null
    var chapterName: String? = null
    var isCollect: Boolean = false
    var courseId: Int? = null
    var desc: String? = null
    var envelopePic: String? = null
    var fresh: Boolean? = null
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var articleId: Int? = null
    var link: String? = null
    var niceDate: String? = null
    var origin: String? = null
    var projectLink: String? = null
    var publishTime: Long = 0
    var superChapterId: Int? = null
    var superChapterName: String? = null
    var title: String? = null
    var type: Int? = null
    var userId: Int? = null
    var visible: Int? = null
    var zan: Int? = null
    var timestamp: Long? = null
    @Ignore
    var tags: List<Tags>? = null

    constructor(parcel: Parcel) : this() {
        apkLink = parcel.readString()
        author = parcel.readString()
        chapterId = parcel.readValue(Int::class.java.classLoader) as? Int
        chapterName = parcel.readString()
        isCollect = parcel.readByte() != 0.toByte()
        courseId = parcel.readValue(Int::class.java.classLoader) as? Int
        desc = parcel.readString()
        envelopePic = parcel.readString()
        fresh = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        articleId = parcel.readValue(Int::class.java.classLoader) as? Int
        link = parcel.readString()
        niceDate = parcel.readString()
        origin = parcel.readString()
        projectLink = parcel.readString()
        publishTime = parcel.readLong()
        superChapterId = parcel.readValue(Int::class.java.classLoader) as? Int
        superChapterName = parcel.readString()
        title = parcel.readString()
        type = parcel.readValue(Int::class.java.classLoader) as? Int
        userId = parcel.readValue(Int::class.java.classLoader) as? Int
        visible = parcel.readValue(Int::class.java.classLoader) as? Int
        zan = parcel.readValue(Int::class.java.classLoader) as? Int
        timestamp = parcel.readValue(Long::class.java.classLoader) as? Long
        tags = parcel.createTypedArrayList(Tags)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(apkLink)
        parcel.writeString(author)
        parcel.writeValue(chapterId)
        parcel.writeString(chapterName)
        parcel.writeByte(if (isCollect) 1 else 0)
        parcel.writeValue(courseId)
        parcel.writeString(desc)
        parcel.writeString(envelopePic)
        parcel.writeValue(fresh)
        parcel.writeValue(articleId)
        parcel.writeString(link)
        parcel.writeString(niceDate)
        parcel.writeString(origin)
        parcel.writeString(projectLink)
        parcel.writeLong(publishTime)
        parcel.writeValue(superChapterId)
        parcel.writeString(superChapterName)
        parcel.writeString(title)
        parcel.writeValue(type)
        parcel.writeValue(userId)
        parcel.writeValue(visible)
        parcel.writeValue(zan)
        parcel.writeValue(timestamp)
        parcel.writeTypedList(tags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}

data class Tags(
    var name: String?,
    var url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tags> {
        override fun createFromParcel(parcel: Parcel): Tags {
            return Tags(parcel)
        }

        override fun newArray(size: Int): Array<Tags?> {
            return arrayOfNulls(size)
        }
    }
}
