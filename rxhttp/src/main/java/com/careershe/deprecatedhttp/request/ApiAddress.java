package com.careershe.deprecatedhttp.request;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 类描述：网络请求接口地址。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
public interface ApiAddress {
    /**
     * 获取闪屏页图片
     *
     * @param format
     * @param idx
     * @param n
     * @return
     */
    @GET("HPImageArchive.aspx")
    Observable<ImageBean> getImage(@Query("format") String format, @Query("idx") int idx, @Query("n") int n);

    /**
     * 登陆
     *
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<String> Login(@Field("username") String username, @Field("password") String password);

}
