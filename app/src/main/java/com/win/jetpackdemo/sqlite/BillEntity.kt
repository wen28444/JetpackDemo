package com.win.jetpackdemo.sqlite


/**
 * Author：win
 * Date：2019-11-14
 */
class BillEntity {

    var id: Long = 0
    var type: Int = 0
    var money: Double = 0.toDouble()
    var memo: String? = null
    var pictureName: String? = null
    var tradeTime: Long = 0
    var createTime: Long = 0
    var updateTime: Long = 0

    var tagEntity: TagEntity? = null
    lateinit var accountEntity: AccountEntity

    override fun toString(): String {
        return "id:$id, type:$type, money:$money, memo:$memo, ${tagEntity?.toString()}, $accountEntity"
    }

    companion object {
        const val TYPE_PAYOUT = 0
        const val TYPE_INCOME = 1
    }
}