package com.careershe.rxhttp.deprecatedhttp.tool;

import android.text.TextUtils;

import com.careershe.rxhttp.deprecatedhttp.data.HttpBaseResponse;


/**
 * 类描述：自定义抛出异常。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class HttpException extends RuntimeException {

    /** 异常码。 */
    private int code;
    /** 异常信息。 */
    private String message;
//    /** 请求返回响应。 */
//    private HttpBaseResponse response;

    /**
     * 构造方法。
     *
     * @param message 异常信息。
     */
    public HttpException(String message) {
        this.message = message;
    }

    /**
     * 构造方法。
     *
     * @param code
     * @param message
     */
    public HttpException(int code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return TextUtils.isEmpty(message) ? "" : message;
    }


    public int getCode() {
        return code;
    }

}
