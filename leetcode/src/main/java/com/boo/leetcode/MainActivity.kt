package com.boo.leetcode

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.boo.leetcode.utils.DefaultUtils

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()

        val height = intArrayOf(65, 70, 56, 75, 60, 68)
        val weight = intArrayOf(100, 150, 90, 190, 95, 110)

        val height1 = intArrayOf(175, 175, 175, 175, 175, 175)
        val weight1 = intArrayOf(140, 110, 105, 175, 125, 200)

        val a = DefaultUtils.bestSeqAtIndex(height1, weight1)
        Log.d(TAG, "onStart: $a")
    }
}