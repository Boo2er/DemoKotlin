package com.boo.app.ui_a.db

import android.media.MediaMetadataRetriever
import android.os.Handler
import android.util.Log
import com.boo.app.MyApplication
import com.boo.app.R
import com.boo.app.ui_a.bean.MusicBean
import java.io.File
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.Volatile

class ScanControl private constructor() {
    private val musicList: MutableList<MusicBean> = ArrayList<MusicBean>()
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private var scanTask: Future<*>? = null

    /**
     * 创建一个新的扫描任务
     *
     * @param serialID
     */
    fun scanMusicFile(serialID: String, handler: Handler?) {
        Log.d(TAG, "scanMusicFile: serialID=$serialID")
        // 如果有正在执行的扫描任务，取消它
        cancelScan()
        // 清空音乐列表
        musicList.clear()
        // 提交一个新的扫描任务
        scanTask = executorService.submit {
            try {
                val file = File(serialID)
                if (file.exists()) {
                    // 获取当前线程ID
                    val threadID = Thread.currentThread().id
                    Log.d(
                        TAG,
                        "scanMusicFile: threadID=$threadID"
                    )
                    // 记录扫描开始时间
                    val start = System.currentTimeMillis()
                    val count =
                        AtomicInteger()
                    val sum =
                        AtomicInteger()
                    scanFolder(serialID, file, sum, count)
                    // 记录扫描结束时间
                    val end = System.currentTimeMillis()
                    Log.d(
                        TAG,
                        "scanMusicFile: 扫描耗时=" + (end - start) + "ms" + ", 扫描完成：File=" + sum.get() + ", Music=" + count.get()
                    )
                    // 点击扫描按钮handler不为null时，发送消息通知扫描完成
                    handler?.sendMessage(handler.obtainMessage(0, sum.get(), count.get()))
                        ?: Log.e(TAG, "Handler is null")
                } else {
                    Log.e(TAG, "File not found: $serialID")
                }
            } catch (e: Exception) {
                if (e is InterruptedException) {
                    Log.d(TAG, "Scan task was cancelled")
                } else {
                    Log.e(TAG, "Error scanning music files", e)
                }
            }
        }
    }

    /**
     * 取消扫描任务
     */
    fun cancelScan() {
        if (scanTask != null) {
            scanTask!!.cancel(true)
        }
    }

    /**
     * 判断文件是否为音乐文件
     *
     * @param file
     * @return
     */
    private fun isMusicFile(file: File): Boolean {
        // 判断文件类型是否为音乐文件
        Log.d(TAG, "isMusicFile: file=" + file.name)
        val name = file.name.lowercase(Locale.getDefault())
        return (name.endsWith(".mp3") || name.endsWith(".flac") || name.endsWith(".wma")
                || name.endsWith(".aac") || name.endsWith(".ogg") || name.endsWith(".ape")
                || name.endsWith(".wav") || name.endsWith(".m4a") || name.endsWith(".amr")
                || name.endsWith(".dsf"))
    }

    /**
     * 递归扫描文件夹
     *
     * @param folder
     * @param sum
     * @param count
     */
    fun scanFolder(serialID: String?, folder: File, sum: AtomicInteger, count: AtomicInteger) {
        if (folder.isDirectory) {
            val files = folder.listFiles()
            if (files != null) {
                Log.d(TAG, "scanFolder: files=" + files.contentToString())
                for (file in files) {
                    Log.d(TAG, "scanFolder: file=" + file.name)
                    if (file.isDirectory) {
                        // 如果是文件夹，递归扫描
                        scanFolder(serialID, file, sum, count)
                    } else {
                        sum.incrementAndGet()
                        // 如果是文件，处理文件
                        if (isMusicFile(file)) {
                            if (file.name == "tone.mp3") {
                                continue
                            }
                            count.incrementAndGet()
                            processMusicFile(serialID, file)
                        }
                    }
                }
            }
        }
    }

    /**
     * 解析音乐文件
     */
    private fun processMusicFile(serialID: String?, file: File) {
        var mb: MusicBean? = MusicDatabase.getInstance(MyApplication.appContext).getMusicDao()
            .queryMusic(file.absolutePath)
        var isDataExit = true
        if (mb == null) {
            isDataExit = false
            mb = MusicBean()
        }
        Log.d(TAG, "processMusicFile:" + file.path)
        // 使用 MediaMetadataRetriever 解析音乐文件的元数据
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(file.absolutePath)
        val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        val artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        val album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
        val embedPic = retriever.embeddedPicture
        mb.mMusicPath = title ?: file.name
        mb.mMusicArtist = artist ?: MyApplication.appContext.getString(R.string.unknown)
        mb.mMusicAlbum = album ?: MyApplication.appContext.getString(R.string.unknown)
        mb.mMusicIcon = embedPic
        mb.mMusicPath = file.absolutePath
        mb.mFolderPath = file.parent
        mb.mSerialID = serialID
        if (isDataExit) {
            MusicDatabase.getInstance(MyApplication.appContext).getMusicDao().update(mb)
        } else {
            MusicDatabase.getInstance(MyApplication.appContext).getMusicDao().insert(mb)
        }
    }

    companion object {
        private const val TAG = "ScanControl"

        @Volatile
        var instance: ScanControl? = null
            get() {
                if (field == null) {
                    synchronized(ScanControl::class.java) {
                        if (field == null) {
                            field = ScanControl()
                        }
                    }
                }
                return field
            }
            private set
    }
}