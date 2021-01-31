package com.careershe.rxhttp.deprecatedhttp.request;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 类描述：网络请求接口地址。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
public interface ApiAddress {
    /**
     * 登陆
     *
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<String> Login(@Field("username") String username, @Field("password") String password);

}
