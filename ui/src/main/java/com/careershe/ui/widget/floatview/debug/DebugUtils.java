package com.careershe.ui.widget.floatview.debug;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.DrawableRes;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import org.jetbrains.annotations.NotNull;

/**
 * <pre>
 *     author: blankj
 *     blog  : http://blankj.com
 *     time  : 2019/08/28
 *     desc  : utils about debug
 * </pre>
 */
public class DebugUtils {

    private DebugUtils() {
        throw new UnsupportedOperationException("不能实例调试工具类。");
    }

    static {
//        List<IDebug> debugList = new ArrayList<>();
//        AbsToolDebug.addToolDebugs(debugList);
//        DebugMenu.getInstance().setDebugs(debugList);
        getApp().registerActivityLifecycleCallbacks(ActivityLifecycleImpl.instance);
        LogUtils.v("初始化= " + ActivityLifecycleImpl.instance);
    }

    public static void setIconId(final @DrawableRes int icon) {
        DebugIcon.getInstance().setIconId(icon);
    }

//    public static void addDebugs(final List<IDebug> debugs) {
//        DebugMenu.getInstance().addDebugs(debugs);
//    }

    public static Application getApp() {
        LogUtils.v("初始化= " + Utils.getApp());
        return Utils.getApp();
    }

    static class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {

        private static ActivityLifecycleImpl instance = new ActivityLifecycleImpl();

        private int                    mConfigCount = 0;
        private ViewGroup.LayoutParams mParams      = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        @Override
        public void onActivityCreated(@NotNull Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(@NotNull Activity activity) {
            if (mConfigCount < 0) {
                ++mConfigCount;
            }
        }

        /**
         * Activity在顶部时，添加DebugIcon。
         *
         * @param activity 活动。
         */
        @Override
        public void onActivityResumed(@NotNull Activity activity) {
            ViewParent parent = DebugIcon.getInstance().getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(DebugIcon.getInstance());
            }
            ((ViewGroup) activity.findViewById(android.R.id.content)).addView(DebugIcon.getInstance(), mParams);

            LogUtils.d("【HHD】", "添加View");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            ((ViewGroup) activity.findViewById(android.R.id.content)).removeView(DebugIcon.getInstance());
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                --mConfigCount;
            }
        }

        @Override
        public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NotNull Activity activity) {

        }
    }
}
