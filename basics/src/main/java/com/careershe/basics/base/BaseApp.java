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
        initActivityManager();
        init();
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 管理Activity
     */
    private void initActivityManager() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

    /**
     * 一些第三方库和本地代码的初始化设置
     */
    private void init() {

    }

}
