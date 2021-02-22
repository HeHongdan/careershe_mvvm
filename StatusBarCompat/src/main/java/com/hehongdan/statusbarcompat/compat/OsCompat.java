package com.hehongdan.statusbarcompat.compat;

import android.app.Activity;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * 类描述：针对不同系统抽象出统一的亮、暗色状态栏图标接口。
 *
 * @author HeHongdan
 * @date 2021/1/16
 * @since v2021/1/16
 *
 * @author Cuizhen
 * @date 2019/3/1
 */
public interface OsCompat {
    /**
     * 判断是否暗色状态栏图标。
     *
     * @param fragment 碎片。
     * @return 是否暗色图标。
     */
    boolean isDarkIconMode(@NonNull Fragment fragment);

    /**
     * 判断是否暗色状态栏图标。
     *
     * @param activity 活动。
     * @return 是否暗色图标。
     */
    boolean isDarkIconMode(@NonNull Activity activity);

    /**
     * 判断是否暗色状态栏图标。
     *
     * @param window 窗口。
     * @return 是否暗色图标。
     */
    boolean isDarkIconMode(@NonNull Window window);


    /**
     * 设置是否为暗色状态栏图标。
     *
     * @param fragment 碎片。
     * @param darkIconMode 是否暗色。
     */
    void setDarkIconMode(@NonNull Fragment fragment, boolean darkIconMode);

    /**
     * 设置是否为暗色状态栏图标。
     *
     * @param activity 活动。
     * @param darkIconMode 是否暗色。
     */
    void setDarkIconMode(@NonNull Activity activity, boolean darkIconMode);

    /**
     * 设置是否为暗色状态栏图标。
     *
     * @param window 窗口。
     * @param darkIconMode 是否暗色。
     */
    void setDarkIconMode(@NonNull Window window, boolean darkIconMode);
}
