package com.boo.app

import android.app.Application
import android.util.Log
import com.boo.app.utils.SetUtils
import com.boo.app.utils.SpfUtils
import com.boo.app.utils.UiUtils

class MyApplication : Application() {

    companion object {
        private const val TAG = "MyApplication"
        lateinit var appContext: MyApplication
            private set // 确保外部不能修改
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        Log.d(TAG, "onCreate: ")
        //使用SharedPreferences保存数据需要初始化
        SpfUtils.init(this)
        //初始化UI皮肤
        setSelectUi()
    }

    /**
     * 初始化UI皮肤
     */
    private fun setSelectUi() {
        val uiName: String = SetUtils.getProperty(appContext, UiUtils.UI_NAME, UiUtils.UI_A)
        Log.d(TAG, "setSelectUi: uiName = $uiName")
        when (uiName) {
            UiUtils.UI_A -> SpfUtils.saveData(UiUtils.SAVE_UI, UiUtils.UI_A)
            UiUtils.UI_B -> SpfUtils.saveData(UiUtils.SAVE_UI, UiUtils.UI_B)
            else -> SpfUtils.saveData(UiUtils.SAVE_UI, UiUtils.UI_A)
        }
    }
}