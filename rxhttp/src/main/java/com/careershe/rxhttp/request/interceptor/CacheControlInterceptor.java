package com.careershe.rxhttp.request.interceptor;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.careershe.rxhttp.request.utils.NetUtils;

/**
 * 类描述：缓存拦截器。
 * 用于为Request配置缓存策略。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class CacheControlInterceptor extends BaseCacheControlInterceptor {

    private CacheControlInterceptor() {
    }

    public static void addTo(@NonNull OkHttpClient.Builder builder) {
        builder.addInterceptor(new CacheControlInterceptor());
    }

    @NonNull
    @Override
    protected Request getCacheRequest(Request request, int age) {
        if (NetUtils.isConnected()) {
            if (age <= 0) {
                return request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();
            } else {
                return request.newBuilder()
                        .cacheControl(new CacheControl.Builder().maxAge(age, TimeUnit.SECONDS).build())
                        .build();
            }
        } else {
            return request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
    }
}