package com.careershe.rxhttp.request.interceptor;

import androidx.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.careershe.rxhttp.request.Api;
import com.careershe.rxhttp.request.utils.NetUtils;

/**
 * 类描述：缓存(网络)拦截器。
 * 用于为Response配置缓存策略。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class CacheControlNetworkInterceptor extends BaseCacheControlInterceptor {
    private CacheControlNetworkInterceptor() {
    }

    public static void addTo(@NonNull OkHttpClient.Builder builder) {
        builder.addNetworkInterceptor(new CacheControlNetworkInterceptor());
    }

    @NonNull
    @Override
    protected Request getCacheRequest(Request request, int age) {
        return request.newBuilder()
                .removeHeader(Api.Header.CACHE_ALIVE_SECOND)
                .build();
    }

    @NonNull
    @Override
    protected Response getCacheResponse(Response response, int age) {
        if (NetUtils.isConnected()) {
            if (age <= 0) {
                return response.newBuilder()
                        .removeHeader("Cache-Control")
                        .build();
            } else {
                return response.newBuilder()
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + age)
                        .build();
            }
        } else {
            return response.newBuilder()
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + Integer.MAX_VALUE)
                    .build();
        }
    }
}
