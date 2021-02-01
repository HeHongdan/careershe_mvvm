package com.careershe.rxhttp.deprecatedhttp.data;

import com.google.gson.Gson;

/**
 * 类描述：Http请求返回数据接口。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public interface HttpResponseInterface {

    /**
     * 获取处理掉code和msg后的信息。
     *
     * @param gson 处理Json工具。
     * @param response 响应内容。
     * @return 响应数据。
     */
    String getResponseData(Gson gson, String response);

}
