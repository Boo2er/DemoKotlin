package com.boo.demokotlin.fourth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.boo.demokotlin.MainActivity
import com.boo.demokotlin.R
import com.boo.demokotlin.first.FirstActivity
import com.boo.demokotlin.third.ThirdActivity

class FourthActivity : AppCompatActivity() ,View.OnClickListener{
    private val TAG = "FourthActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fourth)
        Log.d(TAG, "onCreate: ")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //按钮监听实现
        val btnFirst = findViewById<Button>(R.id.btn_first)
        btnFirst.setOnClickListener(this)
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_first -> {
                val intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
            }

            else -> {
                TODO("Not yet implemented")
            }
        }
    }
}