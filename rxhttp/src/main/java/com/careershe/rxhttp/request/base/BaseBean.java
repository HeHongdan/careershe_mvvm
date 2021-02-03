package com.careershe.rxhttp.request.base;

import com.google.gson.Gson;

import java.io.Serializable;

import com.careershe.rxhttp.request.utils.JsonFormatUtils;

/**
 * 类描述：网络响应返回的实体类基类。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class BaseBean implements Serializable {

    /**
     * 实例转Json字符串。
     *
     * @return Json字符串。
     */
    public String toJson() {
        return new Gson().toJson(this);
    }

    /**
     * 实例转Json（格式化的）字符串。
     *
     * @return Json（格式化的）字符串。
     */
    public String toFormatJson() {
        return JsonFormatUtils.format(toJson());
    }
}