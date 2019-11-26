package com.win.jetpackdemo.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Author：win
 * Date：2019-11-14
 */
class BillSqliteHelper private constructor(context: Context, dbPath: String) :
    SQLiteOpenHelper(context, dbPath, null, DATABASE_VERSION) {

    override fun onCreate(database: SQLiteDatabase?) {
        val createBill = "CREATE TABLE t_bill (" +
                "fid bigint not null," +
                "type tinyint default 0, " +
                "money decimal default 0," +
                "memo varchar," +
                "pictureName varchar," +
                "accountId bigint, " +
                "tagName varchar, " +
                "tagType tinyint default 0, " +
                "tagIcon varchar, " +
                "tradeTime bigint default 0," +
                "createTime bigint default 0," +
                "updateTime bigint default 0," +
                "PRIMARY KEY (fid) " +
                ")"
        val createAccount = "CREATE TABLE t_account (" +
                "fid bigint not null," +
                "name varchar not null," +
                "groupType varchar not null," +
                "PRIMARY KEY (fid)" +
                ")"
        database?.let {
            it.execSQL(createBill)
            it.execSQL(createAccount)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    companion object {
        /**
         * 数据库版本号，每次升级加1，并在onUpgrade方法中做升级处理
         */
        private const val DATABASE_VERSION = 1

        /**
         * 数据库名称
         */
        private const val DB_NAME = "bill.db"

        @Volatile
        private var INSTANCE: BillSqliteHelper? = null

        fun getDbHelper(context: Context, dbPath: String?): BillSqliteHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = BillSqliteHelper(context, dbPath ?: DB_NAME)
                INSTANCE = instance
                instance
            }
        }
    }

}