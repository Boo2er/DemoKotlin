package com.boo.app.utils

import android.content.Context
import android.content.SharedPreferences

object SpfUtils {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    /**
     * SharedPreferences初始化
     */
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(Constants.PREFS_FILE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    /**
     * 存储Int数据
     */
    fun saveData(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }
    /**
     * 存储String数据
     */
    fun saveData(key: String, value: String) {
        editor.putString(key, value).apply()
    }
    /**
     * 存储Long数据
     */
    fun saveData(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }
    /**
     * 存储Boolean数据
     */
    fun saveData(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }
    /**
     * 查询Int数据
     */
    fun getInt(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }
    /**
     * 查询String数据
     */
    fun getString(key: String, defValue: String?): String {
        return sharedPreferences.getString(key, defValue).toString()
    }
    /**
     * 查询Long数据
     */
    fun getLong(key: String, defValue: Long): Long {
        return sharedPreferences.getLong(key, defValue)
    }
    /**
     * 查询Boolean数据
     */
    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }
}