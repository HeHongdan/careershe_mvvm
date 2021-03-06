package com.careershe.http;

import com.careershe.rxhttp.request.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * 类描述：
 *
 * @author HeHongdan
 * @date 2020/11/25
 * @version v2020/11/25
 */
public class CareersheResponse<E> implements BaseResponse<E> {

    private boolean success;
    @SerializedName(value = "code", alternate = {"errorCode"})
    private int code;
    private String msg;
    private E data;
    private String status;
    private int total;

//    private String asking;

    @Override
    public int getCode() {
        return code;
    }
    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public E getData() {
        return data;
    }

    @Override
    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String getMsg() {
        return msg;
    }
    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    @Override
    public String toString() {
        return "响应基类= {" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}

