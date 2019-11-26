package com.win.jetpackdemo.ui

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

import com.win.jetpackdemo.repository.BillRepo
import com.win.jetpackdemo.room.entity.Account
import com.win.jetpackdemo.sqlite.AccountEntity
import com.win.jetpackdemo.sqlite.BillEntity
import com.win.jetpackdemo.sqlite.BillSqliteRepo
import com.win.jetpackdemo.sqlite.TagEntity

/**
 * Author：win
 * Date：2019-11-14
 */
class RoomActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this))

        sqliteTest()
        //roomTest()
    }

    @SuppressLint("StaticFieldLeak")
    private fun roomTest() {
        val task = object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                val billRepo = BillRepo(this@RoomActivity)
                var account = billRepo.getAccount(1)
                if (account == null) {
                    val tmpAccount = Account(
                        2,
                        "以太坊",
                        Account.GROUP_ASSETS
                    )
                    billRepo.insertAccount(tmpAccount)
                    account = billRepo.getAccount(2)
                }
//                if (account != null) {
//                    val billOne = Bill().apply {
//                        id = 11
//                        type = Bill.TYPE_PAYOUT
//                        money = 1000.0
//                        memo = "关爱女友"
//                        tradeTime = System.currentTimeMillis()
//                        createTime = System.currentTimeMillis()
//                        updateTime = System.currentTimeMillis()
//                        accountId = account.id
//                        this.tag = Tag().apply {
//                            name = "支出"
//                            type = Tag.TYPE_CATEGORY_PAYOUT
//                            icon = ""
//                        }
//                    }
//
//                    val billTwo = Bill().apply {
//                        id = 12
//                        type = Bill.TYPE_PAYOUT
//                        money = 2000.0
//                        memo = "孝敬父母"
//                        tradeTime = System.currentTimeMillis()
//                        createTime = System.currentTimeMillis()
//                        updateTime = System.currentTimeMillis()
//                        accountId = account.id
//                        this.tag = Tag().apply {
//                            name = "支出"
//                            type = Tag.TYPE_CATEGORY_PAYOUT
//                            icon = ""
//                        }
//                    }
//
//                    val billThree = Bill().apply {
//                        id = 13
//                        type = Bill.TYPE_PAYOUT
//                        money = 3000.0
//                        memo = "犒劳自己"
//                        tradeTime = System.currentTimeMillis()
//                        createTime = System.currentTimeMillis()
//                        updateTime = System.currentTimeMillis()
//                        accountId = account.id
//                        this.tag = Tag().apply {
//                            name = "支出"
//                            type = Tag.TYPE_CATEGORY_PAYOUT
//                            icon = ""
//                        }
//                    }
//                    val billList = arrayListOf(billOne, billTwo, billThree)
//                    billRepo.insertBill(billList)
//                }
                billRepo.getBillList()?.forEach {
                    Log.d("bill-room", it.toString())
                }
                Log.d("bill-room", "======")

                account?.let {
                    billRepo.getBillByAccount(it.id)?.forEach { bill ->
                        Log.d("bill-room", bill.toString())
                    }
                    Log.d("bill-room", "======")

                    billRepo.getAccountWithBill(it.id)?.forEach { accountWithBill ->
                        Log.d("bill-room", accountWithBill.toString())
                    }
                    Log.d("bill-room", "======")
                }

                billRepo.getBillSimpleList()?.forEach {
                    Log.d("bill-room", it.toString())
                }
                Log.d("bill-room", "======")

                billRepo.getBillDetailList()?.forEach {
                    Log.d("bill-room", it.toString())
                }
                Log.d("bill-room", "======")

                return null
            }
        }
        task.execute()
    }

    @SuppressLint("StaticFieldLeak")
    private fun sqliteTest() {
        val task = object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                val billSqliteRepo = BillSqliteRepo(this@RoomActivity)
                var accountEntity = billSqliteRepo.getAccount(1)
                if (accountEntity == null) {
                    val tmpAccount = AccountEntity(
                        1,
                        "比特币",
                        AccountEntity.GROUP_ASSETS
                    )
                    billSqliteRepo.insertAccount(tmpAccount)
                    accountEntity = billSqliteRepo.getAccount(1)
                }
                if (accountEntity != null) {
                    val billOne = BillEntity().apply {
                        id = 1
                        type = BillEntity.TYPE_INCOME
                        money = 100.0
                        memo = "第一桶金"
                        tradeTime = System.currentTimeMillis()
                        createTime = System.currentTimeMillis()
                        updateTime = System.currentTimeMillis()
                        this.accountEntity = accountEntity
                        this.tagEntity = TagEntity("投资收入", TagEntity.TYPE_CATEGORY_INCOME, "")
                    }

                    val billTwo = BillEntity().apply {
                        id = 2
                        type = BillEntity.TYPE_INCOME
                        money = 200.0
                        memo = "第二桶金"
                        tradeTime = System.currentTimeMillis()
                        createTime = System.currentTimeMillis()
                        updateTime = System.currentTimeMillis()
                        this.accountEntity = accountEntity
                        this.tagEntity = TagEntity("投资收入", TagEntity.TYPE_CATEGORY_INCOME, "")
                    }

                    val billThree = BillEntity().apply {
                        id = 3
                        type = BillEntity.TYPE_PAYOUT
                        money = 200.0
                        memo = "韭菜的自我修养"
                        tradeTime = System.currentTimeMillis()
                        createTime = System.currentTimeMillis()
                        updateTime = System.currentTimeMillis()
                        this.accountEntity = accountEntity
                        this.tagEntity = TagEntity("投资亏损", TagEntity.TYPE_CATEGORY_PAYOUT, "")
                    }
                    val billList = arrayListOf(billOne, billTwo, billThree)
                    billSqliteRepo.insertBill(billList)
                }

                billSqliteRepo.getBillList()?.forEach {
                    Log.d("bill-sqlite", it.toString())
                }
                return null
            }
        }
        task.execute()
    }
}