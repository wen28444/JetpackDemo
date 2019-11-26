package com.win.jetpackdemo.repository

import android.content.Context

import com.win.jetpackdemo.room.BillDb
import com.win.jetpackdemo.room.entity.*

/**
 * Author：win
 * Date：2019-11-13
 */
class BillRepo(context: Context) {

    private val billDb by lazy {
        BillDb.getDatabase(context.applicationContext)
    }

    fun insertAccount(account: Account) {
        billDb.accountDao().addAccount(account)
    }

    fun getAccount(accountId: Long): Account? {
        return billDb.accountDao().getAccountById(accountId)
    }

    fun getAccountWithBill(accountId: Long): List<AccountWithBill>? {
        return billDb.accountDao().getAccountWithBill(accountId)
    }

    fun insertBill(billList: List<Bill>) {
        billDb.billDao().addBills(billList)
    }

    fun getBillList(): List<Bill>? {
        return billDb.billDao().getAllBill()
    }

    fun getBillByAccount(accountId: Long): List<Bill>? {
        return billDb.billDao().getBillByAccount(accountId)
    }

    fun getBillSimpleList(): List<BillSimpleInfo>? {
        return billDb.billDao().getBillSimpleList()
    }

    fun getBillDetailList(): List<BillFullInfo>? {
        return billDb.billDao().getBillDetailList()
    }
}