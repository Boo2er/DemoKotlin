package com.boo.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.boo.app.music.MusicListActivity
import com.boo.app.music.MusicPlayActivity
import com.boo.app.utils.SpfUtils
import com.boo.app.utils.UiUtils

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setDefaultUI()
    }

    /**
     * 根据UI切换初始列表界面
     */
    fun setDefaultUI() {
        val intent = Intent()
        val uiName: String = SpfUtils.getString(UiUtils.SAVE_UI, UiUtils.UI_A)
        Log.d(TAG, "setDefaultUI: uiName = $uiName")
        when (uiName) {
            UiUtils.UI_A -> intent.setClass(this, MusicListActivity::class.java)
            else -> intent.setClass(this, MusicPlayActivity::class.java)
        }
        startActivity(intent)
    }
}