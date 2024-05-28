package com.example.xtracker.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xtracker.model.dao.TransactionDao
import com.example.xtracker.model.models.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class XTrackerDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object{
        @Volatile
        private var Instance: XTrackerDatabase? = null

        fun getDatabase(context: Context): XTrackerDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, XTrackerDatabase::class.java, "XTrackerDB")
                    .build().also { Instance = it }
            }
        }

    }
}