package com.careershe.basics.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.multidex.MultiDexApplication;


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
        firstOpen = true;
        context = this;
    }

    public static Context getContext() {
        return context;
    }

}
