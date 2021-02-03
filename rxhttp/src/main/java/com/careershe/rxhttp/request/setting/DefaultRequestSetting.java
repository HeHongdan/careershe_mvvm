package com.careershe.rxhttp.request.setting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import com.careershe.rxhttp.request.exception.ExceptionHandle;

/**
 * 类描述：（默认）网络请求设置。
 * <p>
 *     超时       ==  5000
 *     缓存路径     ==  SD卡/"rxhttp_cache"
 *     缓存大小     ==  10 * 1024 * 1024
 * </p>
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */

public abstract class DefaultRequestSetting implements RequestSetting {

    /**
     * 默认缓存路径。
     */
    private static final String DEFAULT_CACHE_DIR= "rxhttp_cache";
    /**
     * 默认缓存(空间)大小。
     */
    private static final long DEFAULT_CACHE_SIZE= 10 * 1024 * 1024;
    /**
     * 默认超时时间。
     */
    private static final long DEFAULT_TIMEOUT= 5000;


    @Nullable
    @Override
    public Map<String, String> getRedirectBaseUrl() {
        return null;
    }

    @Nullable
    @Override
    public Map<Class<?>, String> getServiceBaseUrl() {
        return null;
    }

    @Override
    public int[] getMultiSuccessCode() {
        return null;
    }

    @Override
    public long getTimeout() {
        return DEFAULT_TIMEOUT;
    }

    @Override
    public long getConnectTimeout() {
        return 0;
    }

    @Override
    public long getReadTimeout() {
        return 0;
    }

    @Override
    public long getWriteTimeout() {
        return 0;
    }

    @NonNull
    @Override
    public String getCacheDirName() {
        return DEFAULT_CACHE_DIR;
    }

    @Override
    public long getCacheSize() {
        return DEFAULT_CACHE_SIZE;
    }

    @Nullable
    @Override
    public Map<String, String> getStaticPublicQueryParameter() {
        return null;
    }

    @Nullable
    @Override
    public Map<String, ParameterGetter> getDynamicPublicQueryParameter() {
        return null;
    }

    @Nullable
    @Override
    public Map<String, String> getStaticHeaderParameter() {
        return null;
    }

    @Nullable
    @Override
    public Map<String, ParameterGetter> getDynamicHeaderParameter() {
        return null;
    }

    @Nullable
    @Override
    public <E extends ExceptionHandle> E getExceptionHandle() {
        return null;
    }

    @Nullable
    @Override
    public Interceptor[] getInterceptors() {
        return null;
    }

    @Nullable
    @Override
    public Interceptor[] getNetworkInterceptors() {
        return null;
    }

    @Override
    public boolean ignoreSslForHttps() {
        return false;
    }

    @Override
    public boolean enableTls12BelowAndroidKitkat() {
        return true;
    }

    @Override
    public void setOkHttpClient(OkHttpClient.Builder builder) {
    }

    /**
     * 自定义Gson解析器。
     *
     * @return Gson对象。
     */
    @Nullable
    @Override
    public Gson getGson() {
        return null;
    }

    @Override
    public boolean isDebug() {
        return false;
    }
}
