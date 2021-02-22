package com.hehongdan.statusbarcompat.compat;

import android.app.Activity;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hehongdan.statusbarcompat.utils.DarkModeUtils;


/**
 * 类描述：默认(未知)系统的亮、暗色状态栏图标。
 *
 * @author HeHongdan
 * @date 2021/1/16
 * @since v2021/1/16
 *
 * @author Cuizhen
 * @date 2019/3/1
 */
public class OsCompatDef implements OsCompat {

    @Override
    public boolean isDarkIconMode(@NonNull Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            return false;
        }
        return isDarkIconMode(activity);
    }

    @Override
    public boolean isDarkIconMode(@NonNull Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            return false;
        }
        return isDarkIconMode(window);
    }

    @Override
    public boolean isDarkIconMode(@NonNull Window window) {
        return DarkModeUtils.isDarkIconMode(window);
    }

    @Override
    public void setDarkIconMode(@NonNull Fragment fragment, boolean darkIconMode) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        setDarkIconMode(activity, darkIconMode);
    }

    @Override
    public void setDarkIconMode(@NonNull Activity activity, boolean darkIconMode) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        setDarkIconMode(window, darkIconMode);
    }

    @Override
    public void setDarkIconMode(@NonNull Window window, boolean darkIconMode) {
        DarkModeUtils.setDarkIconMode(window, darkIconMode);
    }
}
