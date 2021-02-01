package com.careershe.rxhttp.request.interceptor;

import androidx.annotation.NonNull;
import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.careershe.rxhttp.core.RxHttp;
import com.careershe.rxhttp.core.utils.BaseUrlUtils;
import com.careershe.rxhttp.request.Api;
import com.careershe.rxhttp.request.utils.NonNullUtils;

/**
 * 类描述：（BaseUrl）重定向拦截器。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class BaseUrlRedirectInterceptor implements Interceptor {

    private BaseUrlRedirectInterceptor() {
    }

    /**
     * 加入重定向拦截器。
     *
     * @param builder okHttp构建器。
     */
    public static void addTo(@NonNull OkHttpClient.Builder builder) {
        Map<String, String> urls = RxHttp.getRequestSetting().getRedirectBaseUrl();
        if (NonNullUtils.check(urls)) {
            builder.addInterceptor(new BaseUrlRedirectInterceptor());
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Map<String, String> urls = RxHttp.getRequestSetting().getRedirectBaseUrl();
        if (!NonNullUtils.check(urls)) {
            return chain.proceed(original);
        }
        List<String> urlNames = original.headers(Api.Header.BASE_URL_REDIRECT);
        if (!NonNullUtils.check(urlNames)) {
            return chain.proceed(original);
        }
        Request.Builder builder = original.newBuilder();
        //处理自定义的（缓存）拦截器
        builder.removeHeader(Api.Header.BASE_URL_REDIRECT);
        String urlName = urlNames.get(0);
        String newUrl = urls.get(urlName);
        if (newUrl == null) {
            return chain.proceed(original);
        }
        //HTTP或HTTPS
        HttpUrl newHttpUrl = HttpUrl.parse(BaseUrlUtils.checkBaseUrl(newUrl));
        if (newHttpUrl == null) {
            return chain.proceed(original);
        }
        HttpUrl oldHttpUrl = original.url();
        List<String> pathSegments = new ArrayList<>(oldHttpUrl.pathSegments());
        int oldCount = defaultBaseUrlPathSegmentCount();
        for (int i = 0; i < oldCount; i++) {
            pathSegments.remove(0);
        }
        HttpUrl.Builder newHttpUrlBuilder = oldHttpUrl.newBuilder()
                .scheme(newHttpUrl.scheme())
                .host(newHttpUrl.host())
                .port(newHttpUrl.port());
        //根据“/”分段校验
        int size1 = newHttpUrl.pathSegments().size();
        for (int i = size1 - 1; i >= 0; i--) {
            String segment = newHttpUrl.pathSegments().get(i);
            if (TextUtils.isEmpty(segment)){
                continue;
            }
            pathSegments.add(0, segment);
        }
        int size2 = oldHttpUrl.pathSegments().size();
        for (int i = 0; i < size2; i++) {
            newHttpUrlBuilder.removePathSegment(0);
        }
        for (int i = 0; i < pathSegments.size(); i++) {
            newHttpUrlBuilder.addPathSegment(pathSegments.get(i));
        }
        Request newRequest = builder.url(newHttpUrlBuilder.build()).build();
        return chain.proceed(newRequest);
    }

    /**
     * 基于HTTP或HTTPS分段并。
     *
     * @return
     */
    private int defaultBaseUrlPathSegmentCount(){
        HttpUrl oldHttpUrl = HttpUrl.parse(BaseUrlUtils.checkBaseUrl(RxHttp.getRequestSetting().getBaseUrl()));
        if (oldHttpUrl == null) {
            return 0;
        }
        List<String> oldSegments = oldHttpUrl.pathSegments();
        if (oldSegments == null || oldSegments.size() == 0){
            return 0;
        }
        int count = oldSegments.size();
        if (TextUtils.isEmpty(oldSegments.get(count - 1))) {
            count--;
        }
        return count;
    }
}
