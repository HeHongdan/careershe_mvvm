package com.careershe.deprecatedhttp.request;

import com.careershe.deprecatedhttp.bean.ArticleListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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




    /**
     * 获取微信公众号文章列表
     *
     * @param id   id
     * @param page 页码
     * @return
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<ArticleListBean> getWechatArticleList(@Path("id") int id, @Path("page") int page);
    /**
     * 获取体系文章列表
     *
     * @param cid  id
     * @param page 页码
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<ArticleListBean> getSystemArticle(@Path("page") int page, @Query("cid") int cid);
    /**
     * 获取项目文章列表
     *
     * @param cid  id
     * @param page 页码
     * @return
     */
    @GET("project/list/{page}/json")
    Observable<ArticleListBean> getProjectArticle(@Path("page") int page, @Query("cid") int cid);


}
