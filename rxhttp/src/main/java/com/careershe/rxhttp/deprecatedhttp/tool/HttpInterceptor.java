package com.careershe.rxhttp.deprecatedhttp.tool;


import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.careershe.rxhttp.deprecatedhttp.bean.LoginBean;
import com.careershe.rxhttp.deprecatedhttp.request.APIConstants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 类描述：自定义请求拦截器(打印请求前、响应...)。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class HttpInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        if (!NetworkUtils.isConnected()){
            throw new HttpException(APIConstants.NETWORK_ERROR);
        }

        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            SPHelper.getHttpSp().put(SPHelper.HawkCode.COOKIE, cookies);
        }

        Request request = chain.request();
        request = getHeaderRequest(request);
        logRequest(request);
        Response response = chain.proceed(request);
        logResponse(response);

        return originalResponse;
    }

    /**
     * 打印请求信息。
     *
     * @param request
     */
    private void logRequest(Request request) {
        LogUtils.v(APIConstants.LOG_TAG,  "请求方法= "+request.method());
        LogUtils.v(APIConstants.LOG_TAG,  "请求路径= "+request.url().toString());
        LogUtils.v(APIConstants.LOG_TAG,  "请求头= "+request.headers().toString());
        if (request.method().equals("GET")) {
            return;
        }
        try {
            RequestBody requestBody = request.body();
            String parameter = null;
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            parameter = buffer.readString(UTF8);
            buffer.flush();
            buffer.close();
            LogUtils.v(APIConstants.LOG_TAG, "请求参数= " + parameter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加请求头(填写用户token、Cookie...)。
     *
     * @param request
     * @return
     */
        private Request getHeaderRequest (Request request){
        LoginBean loginData = GsonUtils.fromJson(SPHelper.getHttpSp().getString(SPHelper.HawkCode.LOGIN_DATA), LoginBean.class);
        Request headRequest;
        if (loginData != null){
            headRequest = request.newBuilder()
                    .addHeader("token", loginData.getToken())
                    .addHeader("Cookie", "loginUserName=" + loginData.getUsername())
                    .addHeader("Cookie", "loginUserPassword=" + loginData.getPassword())
                    .build();
        } else {
            headRequest = request
                    .newBuilder()
                    .addHeader("platform", "android")
                    .addHeader("version", "1.0")
                    .build();
        }
        return headRequest;
    }

    /**
     * 打印返回(响应)结果。
     *
     * @param response
     */
    private void logResponse(Response response) {
        try {
            ResponseBody responseBody = response.body();
            String rBody = null;
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null){
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            rBody = buffer.clone().readString(charset);
            LogUtils.v(APIConstants.LOG_TAG, "返回值= " + rBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
