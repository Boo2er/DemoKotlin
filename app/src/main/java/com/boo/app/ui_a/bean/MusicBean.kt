package com.boo.app.ui_a.bean

data class MusicBean(
    var mMusicId: Int,
    var mMusicName: String?,
    var mMusicAlbum: String?,
    var mMusicArtist: String?,
    var mMusicIcon: ByteArray?,
    var mSerialID: String?,
    var mMusicPath: String?,
    var mFolderPath: String?,
    var mAdvance: Boolean,
    var isPlay: Boolean,
    var isFavourite: Boolean,
    var isExist: Boolean
)