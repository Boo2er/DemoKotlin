package com.boo.app.ui_a.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.boo.app.R
import com.boo.app.ui_a.adapter.MusicListAdapter
import com.boo.app.ui_a.bean.MusicBean
import com.boo.app.ui_a.db.MusicListControl
import com.boo.app.utils.Constants

class MusicListActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MusicListAdapter
    private lateinit var musicList: List<MusicBean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        enableEdgeToEdge()
        setContentView(R.layout.activity_music_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.rv_music)
        musicList = ArrayList()
        val btnScan = findViewById<Button>(R.id.btn_scan)
        btnScan.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
        musicList = MusicListControl.getInstance().getAllMusic(Constants.TEST_PATH)
        adapter = MusicListAdapter(this, musicList)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    companion object {
        private const val TAG = "MusicListActivity"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_scan -> {

            }
        }
    }
}