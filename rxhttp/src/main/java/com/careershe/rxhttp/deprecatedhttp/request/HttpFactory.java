package com.careershe.rxhttp.deprecatedhttp.request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 类描述：Retrofit工厂类(网络请求)。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
public class HttpFactory {

    public static String HTTP_HOST_URL = "";

    private static Retrofit retrofit = null;
    private HttpFactory() {
    }

    /**
     * 获取默认 BaseUrl 的 Retrofit。
     *
     * @param apiAddressClass
     * @param <T>
     * @return
     */
    public static <T> T  getInstance(Class<T> apiAddressClass) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(HTTP_HOST_URL)
                    .addConverterFactory(ResponseConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(HTTP_CLIENT)
                    .build();
        }
        return retrofit.create(service);
    }

    /**
     * 获取更改 BaseUrl 的 Retrofit。
     *
     * @param url 新的url。
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getChangeUrlInstance(String url, Class<T> service) {
        return new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(HTTP_CLIENT)
                .build()
                .create(service);
    }

    /**
     * 设置HttpClient。
     */
    private static OkHttpClient HTTP_CLIENT =
            new OkHttpClient.Builder()
                    //添加自定义拦截器
                    .addInterceptor(new HttpInterceptor())
                    .addInterceptor(new AddCookiesInterceptor())
                    //设置超时时间
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();



}
