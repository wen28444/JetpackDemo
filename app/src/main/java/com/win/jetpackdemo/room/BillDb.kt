package com.win.jetpackdemo.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

import com.win.jetpackdemo.room.dao.AccountDao
import com.win.jetpackdemo.room.dao.BillDao
import com.win.jetpackdemo.room.entity.Account
import com.win.jetpackdemo.room.entity.Bill


/**
 * Author：win
 * Date：2019-11-13
 */

private const val DB_VERSION = 1

@Database(
    entities = [Bill::class, Account::class],
    //views = [BillFullInfo::class],
    version = DB_VERSION,
    exportSchema = true
)
abstract class BillDb: RoomDatabase() {

    abstract fun billDao(): BillDao

    abstract fun accountDao(): AccountDao

    private class DbCallback: Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.d(TAG, "onCreate")
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            Log.d(TAG, "onOpen")
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
            Log.d(TAG, "onDestructiveMigration")
        }
    }

//    private class OpenHelperFactory: SupportSQLiteOpenHelper.Factory {
//
//        override fun create(configuration: SupportSQLiteOpenHelper.Configuration?): SupportSQLiteOpenHelper {
//
//        }
//    }

    companion object {
        private const val TAG = "BillDb"

        private const val DB_NAME = "bill_room.db"

        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: BillDb? = null

        fun getDatabase(context: Context): BillDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BillDb::class.java,
                    DB_NAME
                ).allowMainThreadQueries()
                    .setJournalMode(JournalMode.TRUNCATE)
                    .addCallback(DbCallback())
                    //.openHelperFactory(OpenHelperFactory())
                    //.addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }
    }
}