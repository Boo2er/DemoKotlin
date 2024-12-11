package com.boo.java.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DefaultUtils {

    private static final String TAG = "DefaultUtils";

    public static int bestSeqAtIndex(int[] height, int[] weight) {
        Log.d(TAG, "bestSeqAtIndex: ");
        // 身高、体重数组为空则返回人塔数为0
        if (height.length == 0 || weight.length == 0) {
            return 0;
        }

        // 合并身高和体重，并按身高升序排序，如果身高相同则按体重降序排序
        int n = height.length;
        int[][] people = new int[n][2];
        for (int i = 0; i < n; i++) {
            people[i][0] = height[i];
            people[i][1] = weight[i];
        }

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(b[1], a[1]);
                }
            }
        });
        Log.d(TAG, "bestSeqAtIndex: people=" + people);

        // 提取排序后的体重列表
        List<Integer> sortedWeight = new ArrayList<>();
        for (int[] person : people) {
            sortedWeight.add(person[1]);
        }
        Log.d(TAG, "bestSeqAtIndex: sortedWeight="+sortedWeight);

        // 初始化叠罗汉列表
        List<Integer> list = new ArrayList<>();

        // 遍历排序后的体重数据
        for (int w : sortedWeight) {
            // binarySearch二分查找算法
            int pos = Collections.binarySearch(list, w);
            // 叠罗汉列表没有添加这个体重数据
            if (pos < 0) {
                // 实际应该存入的位置
                int insertPos = -(pos + 1);
                // 插入的位置应该在列表下一个数据，由于身高递增，当前体重一定不能比上一个轻
                if (insertPos == list.size()) {
                    // 叠罗汉成功
                    list.add(w);
                } else {
                    // 更新叠罗汉数据，换人
                    list.set(insertPos, w);
                }
            }
        }
        return list.size();
    }
}
