package com.boo.exercise.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentPagerAdapter的实现默认预加载左右相邻各一个Fragment。
 * setOffscreenPageLimit(int limit):修改预加载数量
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        //FragmentManager负责管理 Fragment 的添加、移除和生命周期。
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // 用于返回指定位置的 Fragment 实例
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * 添加Fragment到List
     */
    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

}