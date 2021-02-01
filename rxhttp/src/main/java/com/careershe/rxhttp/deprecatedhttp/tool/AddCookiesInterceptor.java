package com.careershe.rxhttp.deprecatedhttp.tool;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.careershe.rxhttp.deprecatedhttp.request.APIConstants;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类描述：添加Cookies的拦截器。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isConnected()) {
            throw new HttpException(APIConstants.NETWORK_ERROR);
        }

        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences  = (HashSet<String>) SPHelper.getHttpSp().getStringSet(SPHelper.HawkCode.COOKIE, null);
        if (preferences != null){
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                LogUtils.v(APIConstants.LOG_TAG,  "添加Cookie= "+cookie);
            }
        }
        return chain.proceed(builder.build());
    }



}
