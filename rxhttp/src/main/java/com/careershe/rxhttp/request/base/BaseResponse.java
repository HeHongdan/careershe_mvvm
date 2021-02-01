package com.careershe.rxhttp.request.base;

/**
 * 类描述：请求响应返回json格式对应的实体类。
 *
 * @author HeHongdan
 * @version v2020/11/22
 * @date 2020/11/22
 */
public interface BaseResponse<E> {
    /**
     * 获取响应码。
     *
     * @return 响应码。
     */
    int getCode();

    /**
     * 设置响应码。
     *
     * @param code 响应码。
     */
    void setCode(int code);

    /**
     * 获取响应数据（已转实体类）。
     *
     * @return 响应数据。
     */
    E getData();

    /**
     * 设置响应数据。
     *
     * @param data 响应数据。
     */
    void setData(E data);

    /**
     * 获取响应信息。
     *
     * @return 响应信息。
     */
    String getMsg();

    /**
     * 设置响应信息。
     *
     * @param msg 响应信息。
     */
    void setMsg(String msg);
}