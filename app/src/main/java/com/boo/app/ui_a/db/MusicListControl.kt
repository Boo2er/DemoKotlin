package com.boo.app.ui_a.db

import android.content.Context
import com.boo.app.ui_a.bean.MusicBean
import java.io.File
import java.lang.ref.WeakReference

class MusicListControl private constructor() {
    private var mContext: WeakReference<Context>? = null

    companion object {
        private const val TAG = "MusicListControl"
        private val INSTANCE = MusicListControl()
        fun getInstance(): MusicListControl = INSTANCE
    }

    fun init(context: Context) {
        this.mContext = WeakReference(context.applicationContext)
    }

    private fun getContext(): Context? {
        return mContext?.get()
    }

    fun getMusic(path: String): MusicBean {
        return MusicDatabase.getInstance(getContext()!!).getMusicDao().queryMusic(path)
    }

    fun getAllMusic(serialId: String): List<MusicBean> {
        val musicBeans = MusicDatabase.getInstance(getContext()!!).getMusicDao().queryAllMusic(serialId)
        return filterNonExistingFiles(musicBeans)
    }

    fun getAllMusic(): List<MusicBean> {
        val musicBeans = MusicDatabase.getInstance(getContext()!!).getMusicDao().queryAllMusic()
        return filterNonExistingFiles(musicBeans)
    }

    fun updateMusic(musicBean: MusicBean) {
        MusicDatabase.getInstance(getContext()!!).getMusicDao().update(musicBean)
    }

    fun updateMusic(serialId: String) {
        getAllMusic(serialId)
    }

    private fun filterNonExistingFiles(musicBeans: List<MusicBean>): List<MusicBean> {
        val filteredList = mutableListOf<MusicBean>()
        for (musicBean in musicBeans) {
            val musicPath = musicBean.mMusicPath
            if (musicPath != null && File(musicPath).exists()) {
                filteredList.add(musicBean)
            } else {
                musicBean.mMusicPath?.let {
                    MusicDatabase.getInstance(getContext()!!).getMusicDao().delete(musicBean)
                }
            }
        }
        return filteredList
    }
}