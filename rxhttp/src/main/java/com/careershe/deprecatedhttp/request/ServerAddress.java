package com.careershe.deprecatedhttp.request;


/**
 * 服务器地址
 *
 * @author devel
 */
public class ServerAddress {

    /** 必应。 */
    public static final String API_BING = "https://www.bing.com/";
    /** 玩Android。 */
    public static final String API_DEFAULT_HOST = "https://wanandroid.com/";

    /**
     * 获取默认的baseUrl。
     *
     * @return
     */
    public static String getApiDefaultHost() {
        return API_DEFAULT_HOST;
    }
}
