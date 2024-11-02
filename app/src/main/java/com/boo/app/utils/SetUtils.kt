package com.boo.app.utils

import android.content.Context
import android.provider.Settings

private const val TAG = "SettingsUtil"

object SetUtils {

    /**
     * 获取Strings类型的Settings值
     */
    fun getProperty(c: Context, name: String?): String? {
        var s: String? = null
        s = try {
            Settings.System.getString(c.contentResolver, name)
        } catch (e: Exception) {
            null
        }
        return s
    }

    /**
     * 获取Strings类型的Settings值为空则赋予默认值
     */
    fun getProperty(c: Context, name: String?, def: String?): String {
        var s: String? = null
        s = try {
            Settings.System.getString(c.contentResolver, name)
        } catch (e: Exception) {
            null
        }
        return s ?: def!!
    }

    /**
     * 设置Strings类型的Settings值
     */
    fun setProperty(c: Context, name: String, value: String) {
        try {
            Settings.System.putString(c.contentResolver, name, value)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 获取Int类型的Settings值
     */
    fun getIntProperty(c: Context, name: String?): Int {
        var s = 0
        s = try {
            Settings.System.getInt(c.contentResolver, name)
        } catch (e: Exception) {
            0
        }
        return s
    }

    /**
     * 设置Int类型的Settings值
     */
    fun setIntProperty(c: Context, name: String, value: Int) {
        try {
            Settings.System.putInt(c.contentResolver, name, value)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}