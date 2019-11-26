package com.win.jetpackdemo.room.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Author：win
 * Date：2019-11-14
 */
class AccountWithBill {

    @Embedded
    var account: Account? = null
    @Relation(parentColumn = "fid", entityColumn = "accountId", entity = Bill::class)
    var billList: List<Bill>? = null

    override fun toString(): String {
        return "[$account, $billList]"
    }
}