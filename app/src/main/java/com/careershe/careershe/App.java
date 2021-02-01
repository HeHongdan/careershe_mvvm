package com.careershe.careershe;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.multidex.MultiDexApplication;

import com.careershe.basics.base.BaseApp;
import com.careershe.ui.floatview.debug.DebugIcon;

import org.jetbrains.annotations.NotNull;


/**
 * 类描述：应用类。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class App extends BaseApp {


    @Override
    public void onCreate() {
        super.onCreate();

        initActivityManager();
        init();
    }


    /**
     * 管理Activity
     */
    private void initActivityManager() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NotNull Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(@NotNull Activity activity) {
            }

            @Override
            public void onActivityResumed(@NotNull Activity activity) {
                ViewGroup.LayoutParams mParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ViewParent parent = DebugIcon.getInstance().getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(DebugIcon.getInstance());
                }
                ((ViewGroup) activity.findViewById(android.R.id.content)).addView(DebugIcon.getInstance(), mParams);
            }

            @Override
            public void onActivityPaused(@NotNull Activity activity) {
            }

            @Override
            public void onActivityStopped(@NotNull Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(@NotNull Activity activity) {
            }
        });
    }

    /**
     * 一些第三方库和本地代码的初始化设置
     */
    private void init() {

    }

}
