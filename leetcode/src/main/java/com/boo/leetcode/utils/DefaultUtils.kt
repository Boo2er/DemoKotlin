package com.boo.leetcode.utils

import android.util.Log

object DefaultUtils {
    const val TAG = "DefaultUtils"

    /**
     * Source: LeetCode 面试题 17.08.马戏团人塔
     * Company: 南京市宝马诚迈信息技术
     * Info: height=身高、weight=体重
     */
    fun bestSeqAtIndex1(height: IntArray, weight: IntArray):Int {
        //身高、体重数组为空则返回人塔数为0
        if (height.isEmpty() || weight.isEmpty()) {
            return 0
        }

        //按照身高、体重一一对应的关系合并数组，并按照身高降序排序，若身高相同则再根据体重降序排序
        val people = height.zip(weight).sortedWith(compareBy({ -it.first }, { -it.second }))
        Log.d(TAG, "bestSeqAtIndex: combine="+people)

        //用于存储有效的叠罗汉序列,至少可以有一个人单独站立，叠罗汉为1
        val result = IntArray(people.size){1}

        // 可以使用indices来遍历集合的每个元素
        for (i in people.indices) {
            //遍历已完成叠罗汉的数据，进行身高体重对比
            for (j in 0 until i){
                //若当前壮汉比底部壮汉的身高低、体重轻，则继续叠罗汉
                if (people[i].first < people[j].first && people[i].second < people[j].second){
                    result[i]  = maxOf(result[i], result[j] + 1)
                }
            }
        }

        //循环检测剩下的人，对比身高是否比基准值要小，若身高比基准低则检测体重是否比基准低，若身高和体重都比基准低，则叠罗汉+1，若身高/体重小于等于基准则检测下一个人，下一个身高比基准低则检测体重是否比基准低，若身高和体重都比基准低，则叠罗汉+1，依此类推
        return result.maxOrNull() ?: 0
    }

    fun bestSeqAtIndex(height: IntArray, weight: IntArray): Int {
        //身高、体重数组为空则返回人塔数为0
        if (height.isEmpty() || weight.isEmpty()) {
            return 0
        }

        // 合并身高和体重，并按身高升序排序，如果身高相同则按体重降序排序
        val people = height.zip(weight).sortedWith(compareBy({ it.first }, { -it.second }))
        Log.d(TAG, "bestSeqAtIndex: combine="+people)

        // 提取排序后的体重列表
        val sortedWeight = people.map { it.second }
        Log.d(TAG, "bestSeqAtIndex: sortedWeight="+sortedWeight)

        // 初始化叠罗汉列表
        val list = mutableListOf<Int>()

        // 遍历排序后的体重数据
        for (w in sortedWeight) {
            // binarySearch二分查找算法，如果没有找到目标元素：返回-(insertion point)-1
            val pos = list.binarySearch(w)
            // 叠罗汉列表没有添加这个体重数据
            if (pos < 0) {
                // 实际应该存入的位置
                val insertPos = -(pos + 1)
                //插入的位置应该在列表下一个数据，由于身高递增，当前体重一定不能比上一个轻
                if (insertPos == list.size) {
                    // 叠罗汉成功
                    list.add(w)
                } else {
                    // 更新叠罗汉数据，换人
                    list[insertPos] = w
                }
            }
        }
        return list.size
    }
}