package com.careershe.deprecatedhttp.tool;

import android.text.TextUtils;

import com.careershe.deprecatedhttp.request.HttpFactory;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类描述：处理服务器返回数据，将数据转换成对象。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class ResponseConverterFactory extends Converter.Factory {

    /** 数据、对象转换工具。 */
    private final Gson mGson;

    /**
     * 构造方法。
     *
     * @param gson
     */
    public ResponseConverterFactory(Gson gson) {
        this.mGson = gson;
    }

    /**
     * 创建本类实例。
     *
     * @return
     */
    public static ResponseConverterFactory create() {
        return create(new Gson());
    }

    /**
     * 创建本类实例。
     *
     * @param gson
     * @return
     */
    public static ResponseConverterFactory create(Gson gson) {
        if (gson == null){
            throw new NullPointerException("gson不能为空。");
        }
        return new ResponseConverterFactory(gson);
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new BaseResponseBodyConverter(type);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return GsonConverterFactory.create().requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }



    /**
     * 自定义响应返回转换器。
     *
     * @param <T>
     */
    private class BaseResponseBodyConverter<T> implements Converter<ResponseBody,T> {

        private Type mType;

        private BaseResponseBodyConverter(Type mType) {
            this.mType = mType;
        }

        @Override
        public T convert(@NotNull ResponseBody response) {
            //返回响应对象
            Object object;
            try {
                String strResponse = response.string();
                if (TextUtils.isEmpty(strResponse)){
                    throw new HttpException("服务器返回空值");
                }
                if (HttpFactory.httpResponseInterface == null) {
                    throw new HttpException("请实现 HttpResponseInterface 接口");
                } else {
                    String strData = HttpFactory.httpResponseInterface.getResponseData(mGson, strResponse);
                    object = mGson.fromJson(strData,mType);
                }

            } catch (Exception e) {
                throw new HttpException(e.getMessage());
            } finally {
                response.close();
            }
            return (T) object;
        }
    }
}
