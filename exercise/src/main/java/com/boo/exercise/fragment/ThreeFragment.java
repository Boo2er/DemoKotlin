package com.boo.exercise.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boo.exercise.R;

public class ThreeFragment extends Fragment {
    private static final String TAG = "ThreeFragment";

    public ThreeFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 当Fragment被绑定到Activity时调用
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在初始化Fragment的时候调用
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // 用来绘制Fragment的UI,返回一个代表Fragment的根视图的View对象
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 完成任何需要等待视图创建完成后才能进行的初始化操作
        Log.d(TAG, "onViewCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        // 当Fragment对用户即将可见时调用
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        // 当Fragment开始与用户交互时调用
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        // 当Fragment不再处于活动状态并且将要停止与用户交互时调用
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        // 当Fragment不再对用户可见时调用
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 当Fragment的视图被移除时调用
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 在Fragment被销毁之前调用。(这是清理资源或释放其他引用的最后机会)
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // 当Fragment与Activity解除关联时调用
        Log.d(TAG, "onDetach: ");
    }
}