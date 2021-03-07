package com.careershe.http.response;

import com.careershe.rxhttp.request.exception.ExceptionHandle;

/**
 * 类描述：请求结果(响应)回调接口（合并过程+结果）。
 *
 * @author HeHongdan
 * @version v2020/11/29
 * @date 2020/11/29
 */
public interface ResponseListener<E> {
    /**
     * 请求成功。
     *
     * @param code 响应码。
     * @param data 响应数据。
     */
    void onSuccess(int code, E data);

    /**
     * 请求失败。
     *
     * @param code 响应码。
     * @param msg 响应信息。
     */
    void onFailed(int code, String msg);

    //----------------------------------------------------------------------------------------------

    /**
     * 开始请求。
     */
    void onStart();

    /**
     * 请求出错。
     *
     * @param handle 异常处理器。
     */
    void onError(ExceptionHandle handle);

    /**
     * 请求完成。
     */
    void onFinish();

}
