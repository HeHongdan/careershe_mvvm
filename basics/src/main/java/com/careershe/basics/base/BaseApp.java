package com.careershe.basics.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.multidex.MultiDexApplication;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.BuildConfig;


/**
 * 类描述：基础的应用类。
 *
 * @author HHD
 * @version v2021/1/31
 * @since 2021/1/31
 *
 * @author : devel
 * @date : 2020/2/19 11:30
 * @desc :application
 */
public class BaseApp extends MultiDexApplication {

    /** 整个应用的上下文。 */
    private static Context context;
    /** 首次打开应用。 */
    public static boolean firstOpen;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        firstOpen = true;
        LogUtils.getConfig().setGlobalTag("[千职鹤]");
        LogUtils.getConfig().setConsoleSwitch(BuildConfig.DEBUG);
    }

    public static Context getContext() {
        return context;
    }

}
