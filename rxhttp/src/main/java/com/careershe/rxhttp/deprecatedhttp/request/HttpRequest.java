package com.careershe.rxhttp.deprecatedhttp.request;

import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.careershe.rxhttp.deprecatedhttp.tool.SPHepler;

/**
 * 类描述：Http请求类。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
public class HttpRequest {

    /** 第一层锁：保证变量可见性 */
    private volatile static ApiAddress instance ;

    private HttpRequest(){
    }

    /**
     * 获取默认 BaseUrl 的 Api 地址。
     *
     * @return
     */
    public static ApiAddress getInstance(){
        //第一次判空：无需每次都加锁，提高性能
        if (instance == null) {
            //第二层锁：保证线程同步
            synchronized (HttpRequest.class){
                //第二次判空：避免多线程同时执行getInstance()（此）方法，产生多个instance（本类）对象
                if (instance == null) {
                    instance = HttpFactory.getInstance(ApiAddress.class);
                }
            }
        }
        return instance ;
    }

    /**
     * 获取更改 BaseUrl 的 Api 地址。
     *
     * @param url
     * @return
     */
    public static ApiAddress getInstance(String url) {
        return HttpFactory.getChangeUrlInstance(url, ApiAddress.class);
    }

}
