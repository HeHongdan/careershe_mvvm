package com.careershe.careershe.common

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.careershe.common.AsyncInitTask
import com.careershe.common.SyncInitTask
import com.careershe.http.RequestSetting
import com.careershe.http.cache.CareersheCache
import com.careershe.rxhttp.core.RxHttp

//import com.tencent.bugly.crashreport.CrashReport

//import com.didichuxing.doraemonkit.DoraemonKit

/**
 * 类描述：工具类始化任务。
 *
 * @author HHD
 * @version v2020/11/29
 * @date 2020/11/29
 */
class UtilsInitTask : SyncInitTask() {
    override fun init(application: Application) {
        Utils.init(application)
    }

    override fun onlyMainProcess(): Boolean {
        return true
    }

    override fun level(): Int {
        return 0
    }
    override fun asyncTaskName(): String {
        return "工具类"
    }
}



/**
 * 类描述：初始化腾讯bug管理平台。
 *
 * @author HHD
 * @version v2020/12/30
 * @date 2020/12/30
 */
class BuglyTask : SyncInitTask() {
    override fun init(application: Application) {
//        var userId = SPStaticUtils.getString(Config.SP_USER_ID)
//
//        if (BuildConfig.DEBUG) {
//            userId = "开发者(不登记)"
//            return
//        } else {
//            if (TextUtils.isEmpty(userId)) {
//                userId = "未知用户"
//            }
//        }
//
//        val strategy = CrashReport.UserStrategy(application)
//        //Bugly会在启动20s后联网同步数据
//        //strategy.appReportDelay = 20000
//        CrashReport.setIsDevelopmentDevice(application, BuildConfig.DEBUG)
//        CrashReport.setUserId(application, userId)
//        CrashReport.initCrashReport(application, Config.BUGLY_APP_ID, BuildConfig.DEBUG, strategy)
//
//        //Toast.makeText(application,"【初始化Bugly】是否调试= " + BuildConfig.DEBUG + "，用户= " + userId + "，AppId= " + Config.BUGLY_APP_ID,Toast.LENGTH_LONG).show();
//        Log.v(LogUtils.TAG,
//                "【初始化Bugly】是否调试= " + BuildConfig.DEBUG +
//                        "，用户= " + userId +
//                        "，AppId= " + Config.BUGLY_APP_ID
//        )
    }
}

/**
 * 类描述：网络初始化任务。
 *
 * @author HHD
 * @version v2020/11/29
 * @date 2020/11/29
 */
class RxHttpInitTask : SyncInitTask() {
    override fun init(application: Application) {
        RxHttp.init(application)
        RxHttp.initRequest(RequestSetting(null))
//        RxHttp.initRequest(RxHttpRequestSetting(WanApp.getCookieJar()))
    }

    override fun onlyMainProcess(): Boolean {
        return true
    }

    override fun level(): Int {
        return 0
    }
    override fun asyncTaskName(): String {
        return "HTTP网络组件"
    }
}

/**
 * 类描述：磁盘缓存初始化任务。
 *
 * @author HHD
 * @version v2020/11/29
 * @date 2020/11/29
 */
class CacheInitTask : SyncInitTask() {
    override fun init(application: Application) {
        CareersheCache.init()
    }

    override fun onlyMainProcess(): Boolean {
        return true
    }

    override fun level(): Int {
        return 0
    }

    override fun asyncTaskName(): String {
        return "磁盘缓存"
    }
}

/**
 * 类描述：开发工具初始化任务。
 *
 * @author HHD
 * @version v2020/11/29
 * @date 2020/11/29
 */
class DoKitInitTask : AsyncInitTask() {
//class DoKitInitTask : SyncInitTask() {
    override fun init(application: Application) {
        //（2/2）滴滴研发助手。
//        DoraemonKit.install(application)
    }

    override fun onlyMainProcess(): Boolean {
        return true
    }

    override fun level(): Int {
        return 1
    }

    override fun asyncTaskName(): String {
        return "滴滴研发助手"
    }

}

