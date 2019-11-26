package com.win.jetpackdemo.room.entity

/**
 * Author：win
 * Date：2019-11-13
 */
//@DatabaseView("SELECT bill.fid, bill.type, bill.money, bill.memo, bill.pictureName, " +
//        "bill.tradeTime, bill.tagName, bill.tagType, bill.tagIcon," +
//        "account.fid as accountId, account.name as accountName, account.groupType as accountGroup " +
//        "FROM t_bill AS bill " +
//        "INNER JOIN t_account as account ON bill.accountId = account.fid ")
data class BillFullInfo(
    var fid: Long,
    var type: Int,
    var money: Double,
    var memo: String? = "",
    var pictureName: String? = "",
    var tradeTime: Long,
    var tagName: String? = "",
    var tagType: Int? = Tag.TYPE_CATEGORY_PAYOUT,
    var tagIcon: String? = "",
    var accountId: Long,
    var accountName: String,
    var accountGroup: String
)