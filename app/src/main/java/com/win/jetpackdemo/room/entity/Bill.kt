package com.win.jetpackdemo.room.entity

import android.graphics.Bitmap
import androidx.room.*

/**
 * Author：win
 * Date：2019-11-13
 */
@Entity(
    tableName = "t_bill",
    foreignKeys = [
        ForeignKey(entity = Account::class,
            parentColumns = ["fid"],
            childColumns = ["accountId"],
            //onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE)
    ],
    indices = [
        Index("fid")
    ]
)
class Bill() {
    @PrimaryKey
    @ColumnInfo(name = "fid")
    var id: Long = 0
    var type: Int = 0
    var money: Double = 0.toDouble()
    var memo: String? = null
    var pictureName: String? = null
    var tradeTime: Long = 0
    var createTime: Long = 0
    var updateTime: Long = 0

    var accountId: Long = 0

    @Embedded var tag: Tag? = null

    @Ignore var picture: Bitmap? = null

    @Ignore
    constructor(id: Long): this() {
        this.id = id
    }

    override fun toString(): String {
        return "id:$id, type:$type, money:$money, memo:$memo, accountId:$accountId, ${tag?.toString()}"
    }

    companion object {
        const val TYPE_PAYOUT = 0
        const val TYPE_INCOME = 1
    }
}
