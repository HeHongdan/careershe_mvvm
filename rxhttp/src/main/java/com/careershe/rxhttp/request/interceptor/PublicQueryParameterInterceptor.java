package com.careershe.rxhttp.request.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.careershe.rxhttp.core.RxHttp;
import com.careershe.rxhttp.request.setting.ParameterGetter;
import com.careershe.rxhttp.request.utils.NonNullUtils;


/**
 * 类描述：添加公共请求参数。
 * UTF-8对参数编码，并添加到URL中。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class PublicQueryParameterInterceptor implements Interceptor {
    private PublicQueryParameterInterceptor() {
    }

    public static void addTo(@NonNull OkHttpClient.Builder builder) {
        Map<String, String> staticParameters = RxHttp.getRequestSetting().getStaticPublicQueryParameter();
        Map<String, ParameterGetter> dynamicParameters = RxHttp.getRequestSetting().getDynamicPublicQueryParameter();
        if (NonNullUtils.check(staticParameters, dynamicParameters)) {
            builder.addInterceptor(new PublicQueryParameterInterceptor());
        }
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl.Builder builder = original.url().newBuilder();

        Map<String, String> staticParameters = RxHttp.getRequestSetting().getStaticPublicQueryParameter();
        if (NonNullUtils.check(staticParameters)) {
            for (Map.Entry<String, String> entry : staticParameters.entrySet()) {
                builder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        Map<String, ParameterGetter> dynamicParameters = RxHttp.getRequestSetting().getDynamicPublicQueryParameter();
        if (NonNullUtils.check(dynamicParameters)) {
            for (Map.Entry<String, ParameterGetter> entry : dynamicParameters.entrySet()) {
                builder.addQueryParameter(entry.getKey(), entry.getValue().get());
            }
        }

        Request request = original.newBuilder()
                .method(original.method(), original.body())
                .url(builder.build())
                .build();

        return chain.proceed(request);
    }
}