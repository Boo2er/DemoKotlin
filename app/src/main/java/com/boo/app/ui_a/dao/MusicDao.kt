package com.boo.app.ui_a.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.boo.app.ui_a.bean.MusicBean

@Dao
interface MusicDao {

    @Query("SELECT * FROM music WHERE mMusicPath = :path")
    fun queryMusic(path: String): MusicBean

    @Query("SELECT * FROM music")
    fun queryAllMusic(): List<MusicBean>

    @Query("SELECT * FROM music WHERE mSerialID = :serialIDs")
    fun queryAllMusic(serialIDs: String): List<MusicBean>

    @Insert
    fun insert(vararg musicBean: MusicBean)

    @Update
    fun update(vararg musicBean: MusicBean)

    @Delete
    fun delete(vararg musicBean: MusicBean)
}