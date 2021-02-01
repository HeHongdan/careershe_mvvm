package com.careershe.rxhttp.request.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.careershe.rxhttp.core.RxHttp;

/**
 * 类描述：判断网络的工具类。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class NetUtils {

    /**
     * 判断是否有网络。
     *
     * @return 是否有网络。
     */
    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) RxHttp.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

}
