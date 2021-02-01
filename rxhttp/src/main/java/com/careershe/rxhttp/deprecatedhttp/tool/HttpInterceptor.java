package com.careershe.rxhttp.deprecatedhttp.tool;


import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 类描述：自定义请求拦截器。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        if (!NetworkUtils.isConnected()){
            throw new HttpException("网络连接异常，请检查网络后重试");
        }

        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
//            SPUtils.getInstance()

        }

        return originalResponse;
    }

}
