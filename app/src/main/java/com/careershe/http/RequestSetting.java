package com.careershe.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.careershe.rxhttp.request.setting.DefaultRequestSetting;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * 类描述：网络（请求）配置。
 *
 * @author HHD
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class RequestSetting extends DefaultRequestSetting {

    /** Cookie。 */
    private final PersistentCookieJar mCookieJar;
    /** 处理Json数据工具。 */

    public RequestSetting(PersistentCookieJar cookieJar) {
        mCookieJar = cookieJar;
    }

    @Override
    public boolean isDebug() {
        return CareersheApi.ApiConfig.DEBUG;
    }

    @Override
    public long getTimeout() {
        return CareersheApi.ApiConfig.HTTP_TIMEOUT;
    }

    @NonNull
    @Override
    public String getBaseUrl() {
//        return Config.BASE_URL;
        return CareersheApi.ApiConfig.BASE_URL;
    }

//    @Override
//    public Map<String, String> getRedirectBaseUrl() {
//        Map<String, String> urls = new HashMap<>(3);
//        urls.put(CareersheApi.ApiConfig.BASE_URL_OTHER_NAME, CareersheApi.ApiConfig.BASE_URL_OTHER);
//        urls.put(CareersheApi.ApiConfig.BASE_URL_ERROR_NAME, CareersheApi.ApiConfig.BASE_URL_ERROR);
//        urls.put(CareersheApi.ApiConfig.BASE_URL_HTTPS_NAME, CareersheApi.ApiConfig.BASE_URL_HTTPS);
//        return urls;
//    }

    @Override
    public int getSuccessCode() {
        return CareersheApi.ApiCode.SUCCESS;
    }


    @Nullable
    @Override
    public Gson getGson() {
        return new GsonBuilder().registerTypeAdapter(Date.class, new DateAdapter()).create();
    }

    //    @Override
//    public void setOkHttpClient(OkHttpClient.Builder builder) {
//        super.setOkHttpClient(builder);
//        HttpsCompat.enableTls12ForOkHttp(builder);
//        HttpsCompat.enableTls12ForHttpsURLConnection();
//        builder.cookieJar(mCookieJar);
//    }
//    @Override
//    public void setOkHttpClient(OkHttpClient.Builder builder) {
//        builder.hostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });
//    }
//
//    @Override
//    public Map<String, String> getStaticPublicQueryParameter() {
//        Map<String, String> parameters = new HashMap<>(2);
//        parameters.put("system", "android");
//        parameters.put("version_code", AppUtils.getAppVersionCode() + "");
//        parameters.put("device_num", DeviceUtils.getAndroidID());
//        return parameters;
//    }
//
//    @Override
//    public Map<String, ParameterGetter> getDynamicPublicQueryParameter() {
//        Map<String, ParameterGetter> parameters = new HashMap<>(2);
//        parameters.put("user_id", new ParameterGetter() {
//            @Override
//            public String get() {
//                return "100001";
//            }
//        });
//        return parameters;
//    }
//
}
