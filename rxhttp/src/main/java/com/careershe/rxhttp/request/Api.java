package com.careershe.rxhttp.request;

import com.careershe.rxhttp.request.setting.RequestSetting;

/**
 * 类描述：类继承，用于创建一个API接口实例。
 * 新写一个无参静态方法调用{@link #api(Class)}去创建一个接口实例
 * 方法{@link #api(Class)}的参数为ServiceInterface，建议为内部类
 *
 * @author HeHongdan
 * @version v2020/11/25
 * @date 2020/11/25
 */
public class Api {

    /**
     * 自定义头部拦截器标记。
     */
    public interface Header {
        /**
         * （可重定向）添加以这个为名的Header可以让这个Request使用另一个BaseUrl。
         * {@link RequestSetting#getRedirectBaseUrl()}
         * 如： @Headers({Header.BASE_URL_REDIRECT + ":" + Config.BASE_URL_OTHER_NAME})。
         */
        String BASE_URL_REDIRECT = "RxHttp-BaseUrl-Redirect";
        /**
         * （保活）添加以这个为名的Header可以让这个Request支持缓存（有网联网获取，无网读取缓存）。
         * 如：@Headers({Header.CACHE_ALIVE_SECOND + ":" + 10})
         */
        String CACHE_ALIVE_SECOND = "RxHttp-Cache-Alive-Second";
    }

    /**
     * 创建一个（Retrofit）请求接口实例。
     *
     * @param clazz Retrofit的ServiceInterface，建议定义为子类的内部接口。
     * @param <T> ServiceInterface的名字。
     * @return 请求接口实例。
     */
    protected static <T> T api(Class<T> clazz) {
        return RequestClientManager.getService(clazz);
    }

}

