package com.careershe.rxhttp.request.exception;

/**
 * 类描述：空请求配置异常。
 *
 * @author HeHongdan
 * @author HHD
 * @date 2020/11/22
 */
public class NullRequestSettingException extends RuntimeException {
    public NullRequestSettingException() {
        super("RequestSetting未设置");
    }
}

