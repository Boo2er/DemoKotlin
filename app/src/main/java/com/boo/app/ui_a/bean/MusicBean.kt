package com.boo.app.ui_a.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
class MusicBean {
    // 音乐ID
    @PrimaryKey(autoGenerate = true)
    var mMusicId: Int = 0

    // 歌曲名称
    var mMusicName: String? = null

    // 歌曲封面
    var mMusicIcon: ByteArray? = null

    // 音乐专辑信息
    var mMusicAlbum: String? = null

    // 音乐歌手名
    var mMusicArtist: String? = null

    // 设备ID
    var mSerialID: String? = null

    // 音乐路径
    var mMusicPath: String? = null

    // 音乐所在文件夹
    var mFolderPath: String? = null

    // 播放状态
    var isPlay: Boolean = false

    // 收藏状态
    var isFavourite: Boolean = false

    // 存储状态
    var isExist: Boolean = false

    // 优先级
    var isAdvance: Boolean = false
}