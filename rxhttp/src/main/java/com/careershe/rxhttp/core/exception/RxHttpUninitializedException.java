package com.careershe.rxhttp.core.exception;

import com.careershe.rxhttp.core.RxHttp;


/**
 * 类描述：在调用网络请求之前应该先进行初始化，建议在Application中初始化。
 * {@link RxHttp#init(android.content.Context)}
 *
 * @author HeHongdan
 * @version v2020/11/21
 * @date 2020/11/21
 */
public class RxHttpUninitializedException extends RuntimeException {

    /**
     * 构造方法：网络请求之前未初始化。
     *
     * 覆盖父类异常信息。
     */
    public RxHttpUninitializedException() {
        super("RxHttp未初始化");
    }

}

