package com.careershe.rxhttp.core.utils;

/**
 * 类描述：检查BaseUrl是否以"/"结尾，不是"/"结尾就添加"/"。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class BaseUrlUtils {

    public static String checkBaseUrl(String url) {
        if (url.endsWith("/")) {
            return url;
        } else {
            return url + "/";
        }
    }
}
