package com.boo.java;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.boo.java.utils.DefaultUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    /**
     * LeetCode马戏团人塔
     */
    private void test1() {
        int[] height = {65, 75, 56, 75, 60, 68};
        int[] weight = {100, 150, 90, 190, 95, 110};
        int a = DefaultUtils.bestSeqAtIndex(height, weight);
        Log.d(TAG, "test1: " + a);
    }

    /**
     * 1.两个线程同时运行；
     * 2.两个线程同时运，int a = 0 是公共代码；
     * 3.synchronized关键字修饰；
     */
    private void test2() {
        new Thread(this::test2Test).start();
        new Thread(this::test2Test).start();
    }

    /**
     * i++、++i区别
     */
    synchronized private void test2Test() {
        int a = 0;
        int b = 0;
        int c = 0;
        int d;
        int e = 0;
        int f;
        for (int i = 0; i < 10; i++) {
            a++;
            ++b;
            d = c++;
            f = ++e;
            Log.d(TAG, "test2Test: a=" + a + ",b=" + b + ",d=" + d + ",f=" + f);
        }
    }
}