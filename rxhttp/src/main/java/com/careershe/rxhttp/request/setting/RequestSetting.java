package com.careershe.rxhttp.request.setting;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import com.careershe.rxhttp.request.exception.ExceptionHandle;

/**
 * 类描述：网络请求配置。
 *
 * @author HeHongdan
 * @version v2020/11/22
 * @date 2020/11/22
 */
public interface RequestSetting {

    /**
     * 设置默认BaseUrl。
     *
     * @return BaseUrl。
     */
    @NonNull
    String getBaseUrl();

    /**
     * 不同的请求（重定向）的BaseUrl（拦截器）。
     * <p>
     *     配合Retrofit的@Headers注解使用，如：@Headers({RxHttp.BASE_URL_REDIRECT + ":" + 别名})
     * </p>
     *
     *
     * @return Map 别名，（重定向）的BaseUrl。
     */
    @Nullable
    Map<String, String> getRedirectBaseUrl();

    /**
     * 用于对一组接口设置BaseUrl。
     * <p>
     *     这种设置方法对资源占用较大，实现方式为每组的请求创建不同的Retrofit和OkHttpClient实例，设置均相同，及下面的设置
     *     建议在少数请求需要单独设置BaseUrl时使用{@link #getRedirectBaseUrl()}
     * </p>
     *
     * @return Map 接口类,BaseUrl
     */
    @Nullable
    Map<Class<?>, String> getServiceBaseUrl();

    /**
     * 获取请求成功的响应码。
     *
     * @return 响应码。
     */
    int getSuccessCode();

    /**
     * 获取请求（多个）成功的响应码。
     *
     * @return （多个）响应码。
     */
    @Nullable
    int[] getMultiSuccessCode();

    /**
     * 获取超时时长，单位为毫秒。
     *
     * @return 超时时长（最小1毫秒）。
     */
    @IntRange(from = 1)
    long getTimeout();

    /**
     * 获取连接超时时长，单位为毫秒。
     * <p>
     *     返回0则取{@link RequestSetting#getTimeout()}。
     * </p>
     *
     * @return 连接超时时长（最小0毫秒）。
     */
    @IntRange(from = 0)
    long getConnectTimeout();

    /**
     * 获取读取超时时长，单位为毫秒。
     * <p>
     *     返回0则取{@link RequestSetting#getTimeout()}。
     * </p>
     * @return 读取超时时长（最小0毫秒）。
     */
    @IntRange(from = 0)
    long getReadTimeout();

    /**
     * 获取写出超时时长，单位为毫秒。
     * <p>
     *     返回0则取{@link RequestSetting#getTimeout()}。
     * </p>
     * @return 写出超时时长（最小0毫秒）。
     */
    @IntRange(from = 0)
    long getWriteTimeout();

    /**
     * 获取网络缓存的文件夹名。
     *
     * @return 网络缓存路径。
     */
    @NonNull
    String getCacheDirName();

    /**
     * 获取网络缓存的最大值。
     *
     * @return 缓存的大小（最小1）。
     */
    @IntRange(from = 1)
    long getCacheSize();

    /**
     * 获取静态公共查询参数。
     *
     * @return 静态公共查询参数。
     */
    @Nullable
    Map<String, String> getStaticPublicQueryParameter();

    /**
     * 获取动态公共查询参数。
     *
     * @return 动态公共查询参数。
     */
    @Nullable
    Map<String, ParameterGetter> getDynamicPublicQueryParameter();

    /**
     * 获取静态头部分参数。
     *
     * @return 静态头部分参数。
     */
    @Nullable
    Map<String, String> getStaticHeaderParameter();

    /**
     * 获取动态态头部分参数。
     *
     * @return 动态态头部分参数。
     */
    @Nullable
    Map<String, ParameterGetter> getDynamicHeaderParameter();

    /**
     * 获取请求异常处理器。
     *
     * @param <E>
     * @return 请求异常。
     */
    @Nullable
    <E extends ExceptionHandle> E getExceptionHandle();

    /**
     * 获取（应用层）拦截器。
     *
     * @return 拦截器。
     */
    @Nullable
    Interceptor[] getInterceptors();

    /**
     * 获取（网络层）拦截器。
     *
     * @return 网络拦截器。
     */
    @Nullable
    Interceptor[] getNetworkInterceptors();

    /**
     * 忽略HTTPS的证书验证。
     * <P>
     *     仅在后台未正确配置且着急调试时可临时置为true。
     * </P>
     *
     * @return 建议为false。
     */
    boolean ignoreSslForHttps();

    /**
     * 开启Tls1.2。
     * <P>
     *     android4.4及以下版本默认未开启Tls1.2
     * </P>
     *
     * @return true=则强制开启。
     */
    boolean enableTls12BelowAndroidKitkat();

    /**
     * 设置OkHttp客户端Builder。
     * <P>
     *     在创建OkHttpClient之前调用，及框架完成所有配置后。
     * </P>
     *
     * @param builder OkHttp客户端Builder。
     */
    void setOkHttpClient(OkHttpClient.Builder builder);

    /**
     * Gson方式请求。
     * <P>
     *     在创建OkHttpClient之前调用，及框架完成所有配置后。
     * </P>
     *
     * @return
     */
    @Nullable
    Gson getGson();

    /**
     * 是否打开调试模式。
     *
     * @return 是否调试模式。
     */
    boolean isDebug();
}
