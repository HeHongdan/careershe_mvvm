package com.careershe.rxhttp.deprecatedhttp.data;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * 类描述：封装映射服务器数据基类。。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class HttpBaseResponse<T> implements Serializable {

    /** 错误码。 */
    @Expose
    public int errorCode;

    /** 错误信息。 */
    @Expose
    public String errorMsg;

    /** 响应返回数据。 */
    @Expose
    public T data;

}