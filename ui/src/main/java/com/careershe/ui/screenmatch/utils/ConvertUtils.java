package com.careershe.ui.screenmatch.utils;

import android.content.Context;

/**
 * 类描述：转换工具类(资源定位符屏幕适配)。
 *
 * @author HeHongdan
 * @date 2/22/21
 * @since v2/22/21
 *
 * 获取sp值
 * float pxValue=getResources().getDimension(R.dimen.sp_15);//获取对应资源文件下的sp值
 * int spValue=ConvertUtils.px2sp(this,pxValue);//将px值转换成sp值
 * mTvShowParams.setTextSize(spValue);//设置文字大小
 *
 * 获取dp值
 * float pxValue2=getResources().getDimension(R.dimen.dp_360);//获取对应资源文件下的dp值
 * int dpValue=ConvertUtils.px2dp(this,pxValue2);//将px值转换成dp值
 *
 * https://github.com/wildma/ScreenAdaptation/tree/master/app/src/main/java/com/wildma/androidscreenadaptation/utils
 * Author       wildma
 * Date         2018/4/11
 * Desc	        ${转换工具类}
 */
public class ConvertUtils {

    /**
     * dp值转换成px值
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px值转换成dp值
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, final float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp值转换成px值
     *
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(Context context, final float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px值转换成sp值
     *
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, final float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
