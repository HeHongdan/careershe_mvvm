package com.careershe.rxhttp.request.exception;

/**
 * 类描述：服务器请求成功，返回数据时失败，抛出的异常。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class ApiException extends Exception {
    /** 异常代码。 */
    private final int code;
    /** 异常信息。 */
    private final String msg;

    public ApiException(int code, String msg) {
        super(msg + "(code=" + code + ")");
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}