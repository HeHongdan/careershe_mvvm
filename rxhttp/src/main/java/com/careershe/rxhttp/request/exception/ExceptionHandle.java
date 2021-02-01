package com.careershe.rxhttp.request.exception;

import android.util.Log;

import com.careershe.rxhttp.BuildConfig;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLException;

import com.careershe.rxhttp.request.setting.RequestSetting;
import com.careershe.rxhttp.request.utils.NetUtils;
import retrofit2.HttpException;

/**
 * 类描述：集中处理请求中的（自定义）异常，可通过继承自定义，
 * 在{@link RequestSetting#getExceptionHandle()}中返回。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 *
 * @author CuiZhen
 * @date 2018/10/14
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class ExceptionHandle {

    /** 网络异常。 */
    private Throwable e;
    /** 异常代码。 */
    private int code;
    /** 异常信息。 */
    private String msg;

    /**
     * 异常处理。
     *
     * @param e 异常。
     */
    public final void handle(Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.e("【RxHttp】","归类网络错误前= "+e);
        }

        this.e = e;
        this.code = onGetCode(e);
        this.msg = onGetMsg(code);
    }

    /**
     * 处理异常信息。
     *
     * @param code 异常代码。
     * @return 异常信息。
     */
    private String onGetMsg(int code) {
        String msg;
        switch (code) {
            default:
                msg = "网络未知错误，请稍后重试";
                break;
            case Code.NET:
                msg = "网络连接失败，请检查网络设置";
                break;
            case Code.TIMEOUT:
                msg = "网络不稳定，请稍后重试";
                break;
            case Code.JSON:
                msg = "网络数据解析异常";
                break;
            case Code.HTTP:
                msg = "网络请求错误，请稍后重试";
                break;
            case Code.HOST:
                msg = "服务器连接失败，请检查网络设置";
                break;
            case Code.SSL:
                msg = "网络证书验证失败";
                break;
        }
        return msg;
    }

    /**
     * 处理异常代码。
     *
     * @param e 异常。
     * @return 异常代码。
     */
    private int onGetCode(Throwable e) {
        //如果断网
        if (!NetUtils.isConnected()) {
            return Code.NET;
        } else {
            if (e instanceof SocketTimeoutException) {
                return Code.TIMEOUT;
            } else if (e instanceof HttpException) {
                return Code.HTTP;
            } else if (e instanceof UnknownHostException || e instanceof ConnectException) {
                return Code.HOST;
            } else if (e instanceof JsonParseException || e instanceof ParseException || e instanceof JSONException) {
                return Code.JSON;
            } else if (e instanceof SSLException) {
                return Code.SSL;
            } else {
                return Code.UNKNOWN;
            }
        }
    }

    /**
     * 获取异常代码。
     *
     * @return 异常代码。
     */
    public final int getCode() {
        return code;
    }

    /**
     * 获取异常信息。
     *
     * @return 异常信息。
     */
    public final String getMsg() {
        return msg;
    }

    /**
     * 获取网络异常。
     *
     * @return 网络异常。
     */
    public final Throwable getException() {
        return e;
    }


    /**
     * 网络异常代码。
     */
    public interface Code {
        int UNKNOWN = -1;
        /** 无网络。 */
        int NET = 0;
        /** 网络超时。 */
        int TIMEOUT = 1;
        /** Json处理。 */
        int JSON = 2;
        /** HTTP响应。 */
        int HTTP = 3;
        /** 服务器。 */
        int HOST = 4;
        /** SSL异常。 */
        int SSL = 5;
    }

    @Override
    public String toString() {
        return "网络请求异常={" +
                ", code=" + code +
                ", msg='" + msg +
                "e=" + e + '\'' +
                '}';
    }
}