package com.boo.app.ui_a.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.boo.app.ui_a.bean.MusicBean
import com.boo.app.ui_a.dao.MusicDao
import kotlin.concurrent.Volatile

@Database(entities = [MusicBean::class], version = 1, exportSchema = false)
abstract class MusicDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "MusicDemo.db"
        @Volatile
        private var instance: MusicDatabase? = null

        fun getInstance(context: Context): MusicDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    MusicDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build().also { instance = it }
            }
        }
    }

    abstract fun getMusicDao(): MusicDao
}