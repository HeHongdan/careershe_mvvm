package com.careershe.http;

//import com.careershe.careershe.ApplicationClass;
import com.careershe.careershe.BuildConfig;
import com.careershe.deprecatedhttp.request.ImageBean;
import com.careershe.careershe.model.main.qna.bean.QnaListBean;
import com.careershe.rxhttp.request.Api;
//import com.careershe.careershe.QNA;
//import com.careershe.careershe.dev2.entity.AskingBean;
//import com.careershe.careershe.dev2.entity.FavouriteBean;
//import com.careershe.careershe.dev2.entity.IsHaveBean;
//import com.careershe.careershe.dev2.entity.MainEven;
//import com.careershe.careershe.dev2.entity.MembershipBean;
//import com.careershe.careershe.dev2.entity.OccupationBean;
//import com.careershe.careershe.dev2.entity.OccupationListBean;
//import com.careershe.careershe.dev2.entity.ProfessionChooseBean;
//import com.careershe.careershe.dev2.entity.ProfessionEmploymentBean;
//import com.careershe.careershe.dev2.entity.ProfessionInfoBean;
//import com.careershe.careershe.dev2.entity.ProfessionIntroduceBean;
//import com.careershe.careershe.dev2.entity.RecommendListBean;
//import com.careershe.careershe.dev2.entity.SchoolListBean;
//import com.careershe.careershe.dev2.entity.SelectionCountBean;
//import com.careershe.careershe.dev2.entity.TestOccupation;
//import com.careershe.careershe.dev2.entity.UserBean;
//import com.careershe.careershe.dev2.module_mvc.gold.ExchangeBean;
//import com.careershe.careershe.dev2.module_mvc.gold.GoldBean;
//import com.careershe.careershe.dev2.module_mvc.main.bean.MeBean;
//import com.careershe.careershe.dev2.module_mvc.main.bean.NewUserTaskBean;
//import com.careershe.careershe.dev2.module_mvc.main.bean.QnaBean;
//import com.careershe.careershe.dev2.module_mvc.main.bean.QnaListBean;
//import com.careershe.careershe.dev2.module_mvc.main.bean.SignGoldBean;
//import com.careershe.careershe.dev2.module_mvc.main.bean.TaskBean;
//import com.careershe.careershe.dev2.module_mvc.profession.bean.ProfessionBean_20210118;
//import com.careershe.careershe.dev2.test.Test_Time;
//import com.careershe.careershe.wxapi.OrderBean;
//import com.careershe.common.utils.refreshutils.SmartRefreshUtils;
//import com.careershe.core.rxhttp.request.Api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 类描述：{@code CareersheApi}千职鹤Java后台接口，
 * <a href="http://39.104.167.224:8080/careerplanning/admin/index.html">管理页面</a>。
 * <p>IP地址有两个，分别为UAT和测试服务器{@link //ApplicationClass#BASE_URL}。
 *
 * @author <a href="mailto:Jordan.he@careershe.com">HeHongdan</a>
 * @version v2020.11.25
 * @date 2020/11/25
 * @see //ApplicationClass#BASE_URL
 * <p>
 * pre>
 *     <ul>
 *         <li>
 *         </li>
 *     </ul>
 * </pre>
 * @since 2020/11/25
 */
public class CareersheApi extends Api {


    /**
     * 自定义Api回调码。
     */
    public interface ApiCode {
        /** 请求网络出错。 */
        int ERROR = 0;
        /** 请求网络成功。 */
        int SUCCESS = 200;
        /** 没有缓存（直接请求缓存时）。 */
        int FAILED_NO_CACHE = -9000;
        /** 请先登录（请求网络失败时）。 */
        int FAILED_NOT_LOGIN = -1001;
    }

    /**
     * 服务器配置信息。
     */
    public static class ApiConfig {

        /** 服务器(开发)。 */
        public static final String BASE_URL_TEST = "http://39.104.167.224/";
        /** 服务器(仿真-UA)。 */
        public static final String BASE_URL_UAT = "http://39.99.159.144/";
        /** 服务器(正式)。 */
        public static final String BASE_URL_FORMAL = "https://www.careershe.com/";
        /** 服务器URL(最终采用)。 */
        public static String BASE_URL = BASE_URL_TEST;

        /** 项目名称(最终采用)。https://www.careershe.com/qzhv1.7/ */
        public static final String PROJECT = "api";
        /** 项目名称(开发) */
        public static final String PROJECT_TEST = "api";
        /** 项目名称(仿真-UA)。 */
        public static final String PROJECT_UAT = "qzhv1.7";
        /** 项目名称(正式)。 */
        public static final String PROJECT_FORMAL = "qzhv1.7";


        /** 是否 debug 网络请求。 */
        public static final boolean DEBUG = BuildConfig.DEBUG;
        /** 网络超时时间。 */
        public static final long HTTP_TIMEOUT = 5 * 1000;
        /** 默认一页到条数。 */
        public static final int ONE_PAGE_COUNT = 20;
    }

    /**
     * 请求服务器（集中在这里）。
     */
    public interface ApiUrl {
        /** 请求院校库（分页）。 */
        String SEARCH_LIST_PAGE = ApiConfig.PROJECT + "/biz/university/searchListPage";
        /** 推荐学校列表。 */
        String GET_COURSE_SELECTION = ApiConfig.PROJECT + "/biz/professional/get_course_selection";
        /** 筛选院校列表。 */
        String GET_LISTPAGE = ApiConfig.PROJECT + "/biz/university/getListPage";
        /** 选科结果推荐专业职业。 */
        String GET_COURSE_SELECTION_CHOISE = ApiConfig.PROJECT + "/biz/course_selection/get_course_selection_choise";
        //String GET_COURSE_SELECTION_CHOISE = ApiConfig.PROJECT + "/biz/course_selection/get_course_selection_choise";
        /** 选科结果推荐相关职业。 */
        String GET_COURSE_SELECTION_OCCUPATION = ApiConfig.PROJECT + "/biz/course_selection/get_course_selection_occupation";
        /** 验证是否收藏。 */
        String VERIFY_FAVOURITE = ApiConfig.PROJECT + "/biz/favourite/verifyFavourite";
        /** 添加收藏。 */
        String ADD_FAVOURITE = ApiConfig.PROJECT + "/biz/favourite/addFavourite";
        /** 删除收藏。 */
        String DEL_FAVOURITE = ApiConfig.PROJECT + "/biz/favourite/delFavourite";
        /** 上传头像。 */
        String UPLOAD_PROFILE_PICTURE = ApiConfig.PROJECT + "/profilePicture/uploadProfilePicture";
        /** 查询金币兑换列表。 */
        String GET_GOLD_LIST = ApiConfig.PROJECT + "/gold_exchange/get_gold_list";
        /** 查询总金币。 */
        String GET_GOLD_COUNT = ApiConfig.PROJECT + "/gold_exchange/get_gold_Count";
        /** 专业详情页。 */
        String GET_PROFESSIONAL = ApiConfig.PROJECT + "/biz/professional/get_professional";
        /** 专业基本信息接口 不需要Token。 */
        String GET_PROFESSIONAL_INFO = ApiConfig.PROJECT + "/biz/professional/getProfessionalInfo";
        /** 专业介绍接口 不需要Token。 */
        String GET_PROFESSIONAL_INTRODUCE = ApiConfig.PROJECT + "/biz/professional/getProfessionalIntroduce";
        /** 选考建议接口 不需要Token。 */
        String GET_PROFESSIONAL_CHOOSE = ApiConfig.PROJECT + "/biz/professional/getProfessionalChooseProposal";
        /** 就业信息接口 不需要Token。 */
        String GET_PROFESSIONAL_EMPLOYMENT = ApiConfig.PROJECT + "/biz/professional/getProfessionalEmploymentInformation";
        /** 查询总金币。 */
        String GOLD_EXCHANGE = ApiConfig.PROJECT + "/gold_exchange/gold_exchange";
        /** 获取订单：微信支付。 */
        String CREATE_PAYMENT_ORDER_WECHAT = ApiConfig.PROJECT + "/payment/createPaymentOrderWechat1";
        /** 获取订单：支付宝支付。 */
        String CREATE_PAYMENT_ORDER_ALIPAY = ApiConfig.PROJECT + "/payment/createPaymentOrderAlipay1";
        /** 获取充值会员订单信息。 */
        String GET_MEMBERSHIP_PRICE = ApiConfig.PROJECT + "/MembershipPrice/getMembershipPrice";
        /** 判断学校是否存在。 */
        String IS_HAVE_ACADEMY = ApiConfig.PROJECT + "/biz/academy/isHaveAcademy";
        /** 查询选科剩余的次数。 */
        String GET_USER_COURSE_COUNT = ApiConfig.PROJECT + "/user/getUserCourseCount";
        /** 选科查询扣减。 */
        String GET_COURSE_SELECTION_COUNT = ApiConfig.PROJECT + "/biz/course_selection/get_course_selection_count";
        /** 验证学号开通会员接口。 */
        String CHECK_USER_STUDENT_NUMBER_OPEN_VIP = ApiConfig.PROJECT + "/user/checkUserStudentNumberOpenVip";
        /** 查询活动列表。 */
        String GET_EVEN = ApiConfig.PROJECT + "/biz/event/getEven";
        /** 职业百科(所有分类)。 */
        String GET_OCCUPATION_MENU_ALL = ApiConfig.PROJECT + "/biz/occupation_menu/get_occupation_menu_all";
        /** 职业百科(所有分类)。 */
        String get_occupation_menu = ApiConfig.PROJECT + "/biz/occupation_menu/get_occupation_menu";
        /** 职业百科(所有分类)。 */
        String GET_OCCUPATION_MENU = ApiConfig.PROJECT + "/biz/occupation_menu/getOccupationMenu";
        /** 用户信息(我的页面)。 */
        String GET_USER_INFO_NEW = ApiConfig.PROJECT + "/userProfile/getUserInfoNew";
        /** 查询头像(审核状态)。 */
        String GET_PROFILE_PICTURE_STATUS = ApiConfig.PROJECT + "/profilePicture/getProfilePictureStatus";
        /** 查询用户信息(获取头像)。 */
        String GET_USER_IMAGE = ApiConfig.PROJECT + "/user/getUserImage";
        /** 任务页面()。 */
        String GET_MISSION_AWARD = ApiConfig.PROJECT + "/biz/mission/get_mission_award";
        /** 金币总数(任务页面)。 */
        String GET_USER_GOLD = ApiConfig.PROJECT + "/user/get_user_gold";
        /** 任务列表的数据(任务页面)。 */
        String CHECK_USER_MISSION = ApiConfig.PROJECT + "/biz/mission/check_user_mission";
        /** (任务页面)。 */
        String GET_SIGN_DETAIL = ApiConfig.PROJECT + "/biz/signDetail/get_sign_detail";
        /** 金币总数(任务页面)。 */
        String DO_SIGN = ApiConfig.PROJECT + "/biz/signDetail/doSign";
        /** 金币总数(任务页面)。 */
        String GET_ALL_SIGN = ApiConfig.PROJECT + "/sign/get_all_sign";

        /** 问答列表(主页-问答)。 */
        String GET_ASKING_LIST_NEW = ApiConfig.PROJECT + "/biz/asking/getAskingListNew";
        /** 问答列表(主页-问答)。 */
        String GET_ASKING_LIST = ApiConfig.PROJECT + "/biz/asking/getAskingList";
        /** 问答详情。 */
        String GET_ANSWER_PAGE = ApiConfig.PROJECT + "/biz/answer/getAnswerPage";
        /** 我的提问。 */
        String GET_USER_ASKING_PAGE = ApiConfig.PROJECT + "/biz/asking/getUserAskingPage";
        /** 我的回答。 */
        String GET_USER_ANSWER_PAGE = ApiConfig.PROJECT + "/biz/answer/getUserAnswerPage";
        /** 添加提问。 */
        String ADD_ASKING = ApiConfig.PROJECT + "/biz/asking/addAsking";

    }


    /**
     * 获取Retrofit请求接口实例。
     *
     * @return 请求接口实例。
     */
    public static Service api() {
        return Api.api(Service.class);
    }

    /**
     * 请求服务器（集中在这里）。
     */
    public interface Service {

//        /**
//         * 测试其他网络API。
//         */
//        @GET("getTime")
//        Observable<CareersheResponse<Test_Time>> testGetTime();
//
////        @Headers({Header.CACHE_ALIVE_SECOND + ":" + 10})
////        @Headers({Header.BASE_URL_REDIRECT + ":" + Config.BASE_URL_OTHER_NAME})
//
//        /**
//         * 测试正式服务器。
//         *
//         * @return 响应结果。
//         */
//        @GET("occupation/get_new_occupation")
//        Observable<CareersheResponse<List<TestOccupation>>> testNewOccupation();
//
//        //==========================================================================================
//
//        /**
//         * （分页）请求院校库。
//         *
//         * @param pageSize   页面大小。
//         * @param pageNumber 当前页。
//         * @param name       大学名称（必须）。
//         * @return 院校当页列表。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.SEARCH_LIST_PAGE)
//        Observable<CareersheResponse<SchoolListBean>> searchListPage(@Field("pageSize") int pageSize,
//                                                                     @Field("pageNumber") int pageNumber,
//                                                                     @Field("name") String name
//        );
//
//        /**
//         * 推荐学校列表。
//         *
//         * @param hToken           请求头：Token。
//         * @param hUserId          请求头：用户ID。
//         * @param first_choice     物理/历史。
//         * @param proportion       80%(带百分号)。
//         * @param Re_choice        不提科目要求。
//         * @param name             哲学。
//         * @param subject_category 哲学类。
//         * @return 院校列表.
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_COURSE_SELECTION)
//        Observable<CareersheResponse<SchoolListBean>> getCourseSelection(@Header("token") String hToken,
//                                                                         @Header("userId") String hUserId,
//
//                                                                         @Field("pageSize") int pageSize,
//                                                                         @Field("pageNumber") int pageNumber,
//                                                                         @Field("subject_category") String subject_category,
//                                                                         @Field("name") String name,
//                                                                         @Field("proportion") String proportion,
//                                                                         @Field("first_choice") String first_choice,
//                                                                         @Field("Re_choice") String Re_choice
//
//
//        );
//
//        /**
//         * 筛选院校列表。
//         *
//         * @param pageSize        一页条数
//         * @param pageNumber      当前页
//         * @param region          地区
//         * @param education_level 教育级别
//         * @param type            教育类型
//         * @return 院校列表.
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_LISTPAGE)
//        Observable<CareersheResponse<SchoolListBean>> getListPage(@Field("pageSize") int pageSize,
//                                                                  @Field("pageNumber") int pageNumber,
//                                                                  @Field("region") String region,
//                                                                  @Field("education_level") String education_level,
//                                                                  @Field("type") String type
//
//
//        );
//
//        /**
//         * 选科结果推荐专业职业。
//         *
//         * @param hToken       请求头：Token。
//         * @param hUserId      请求头：用户ID。
//         * @param first_choice 首先选科目。
//         * @param Re_choice    再选科目。
//         * @param pageSize     一页个数。
//         * @param pageNumber   第几页。
//         * @param userId       用户id。
//         * @return 列表.
//         * @deprecated {@link #getCourseSelectionChoise(String, String, String, String, String, String, int, int)}20210205后请使用前面的接口。
//         */
//        @Deprecated
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_COURSE_SELECTION_CHOISE)
//        Observable<CareersheResponse<RecommendListBean>> getCourseSelectionChoise_(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                                   @Field("first_choice") String first_choice,
//                                                                                   @Field("Re_choice") String Re_choice,
//                                                                                   @Field("userId") String userId,
//                                                                                   @Field("pageSize") int pageSize,
//                                                                                   @Field("pageNumber") int pageNumber
//
//        );
//
//        /**
//         * 选科结果推荐专业职业。
//         *
//         * @param hToken       请求头：Token。
//         * @param hUserId      请求头：用户ID。
//         * @param first_choice 首先选科目。
//         * @param Re_choice    再选科目。
//         * @param study_categor 学科比如工学,如果是不限传递空。
//         * @param pageSize     一页个数。
//         * @param pageNumber   第几页。
//         * @param userId       用户id。
//         * @return 列表.
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_COURSE_SELECTION_CHOISE)
//        Observable<CareersheResponse<RecommendListBean>> getCourseSelectionChoise(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                                  @Field("first_choice") String first_choice,
//                                                                                  @Field("Re_choice") String Re_choice,
//                                                                                  @Field("study_categor") String study_categor,
//                                                                                  @Field("userId") String userId,
//                                                                                  @Field("pageSize") int pageSize,
//                                                                                  @Field("pageNumber") int pageNumber
//
//        );
//
//        /**
//         * 选科结果推荐相关职业。
//         *
//         * @param hToken       请求头：Token。
//         * @param hUserId      请求头：用户ID。
//         * @param first_choice 首先选科目。
//         * @param Re_choice    再选科目。
//         * @param pageSize     一页个数。
//         * @param pageNumber   第几页。
//         * @param userId       用户id。
//         * @return 列表.
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_COURSE_SELECTION_OCCUPATION)
//        Observable<CareersheResponse<OccupationListBean>> getCourseSelectionOccupation(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                                       @Field("first_choice") String first_choice,
//                                                                                       @Field("Re_choice") String Re_choice,
//                                                                                       @Field("userId") String userId,
//                                                                                       @Field("pageSize") int pageSize,
//                                                                                       @Field("pageNumber") int pageNumber
//        );
//
//        /**
//         * 验证是否收藏。
//         *
//         * @param hToken      请求头：Token。
//         * @param hUserId     请求头：用户ID。
//         * @param type        收藏类型（职业类型 1.Occupation 问答类型 2.Asking 专业类型 3. Profession）。
//         * @param favouriteId 收藏职业 专业 问答 Id。
//         * @param userId      用户id。
//         * @return 收藏结果。
//         * <p>
//         * {
//         * "success": true,
//         * "errorCode": 200,
//         * "msg": "获取收藏数据状态!",
//         * "status": null,
//         * "data": {
//         * "favouriteStatus": false
//         * },
//         * "occupationRandomVo": null
//         * }
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.VERIFY_FAVOURITE)
//        Observable<CareersheResponse<FavouriteBean>> verifyFavourite(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                     @Field("type") String type,
//                                                                     @Field("favouriteId") String favouriteId,
//                                                                     @Field("userId") String userId
//        );
//
//        /**
//         * 添加收藏。
//         *
//         * @param hToken      请求头：Token。
//         * @param hUserId     请求头：用户ID。@Header("token") String hToken, @Header("userId") String hUserId,
//         * @param type        （职业类型 1.Occupation 问答类型 2.Asking 专业类型 3. Profession）。
//         * @param favouriteId 收藏职业 专业 问答 Id。
//         * @param userId      用户id。
//         * @return "success": true,"errorCode":200,"msg": "收藏成功!（200 收藏成功，400 已收藏 500 接口错误）.
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.ADD_FAVOURITE)
//        Observable<CareersheResponse<FavouriteBean>> addFavourite(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                  @Field("type") String type,
//                                                                  @Field("favouriteId") String favouriteId,
//                                                                  @Field("userId") String userId
//
//        );
//
//
//        /** 职业类型。 */
//        String TYPE_FAVOURITE_OCCUPATION = "Occupation";
//        /** 问答类型。 */
//        String TYPE_FAVOURITE_ASKING = "Asking";
//        /** 专业类型。 */
//        String TYPE_FAVOURITE_PROFESSION = "Profession";
//
//        /**
//         * 删除收藏。
//         * <p>
//         * //* @param type （职业类型 1.Occupation 问答类型 2.Asking 专业类型 3. Profession）。
//         *
//         * @param hToken      请求头：Token。
//         * @param hUserId     请求头：用户ID。
//         * @param favouriteId 收藏职业 专业 问答 Id。
//         * @param userId      用户id。
//         * @return "success": true,"errorCode":200,"msg": "删除收藏成功!.
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.DEL_FAVOURITE)
//        Observable<CareersheResponse<FavouriteBean>> delFavourite(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                  //@Field("type") String type,//FIXME 后台设置传类型，出现碰撞的概率会很大
//                                                                  @Field("favouriteId") String favouriteId,
//                                                                  @Field("userId") String userId
//        );
//
//        /**
//         * 查询头像(审核状态)接口。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户id。
//         * @return 用户组id，1：超级管理员；2：普通用户。{@code {
//         * "success": true,
//         * "errorCode": 200,
//         * "msg": "返回数据成功!",
//         * "data": {
//         * "userId": "j8gtURt29X",
//         * "newProfilePicture": "http://39.104.167.224/images/1172d0d0c36c4c5c9846966fc9b207fd.png",
//         * "profilePictureStatus": "待审核"
//         * },
//         * "status": null,
//         * "spiderDiagram": null
//         * }
//         * }
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_PROFILE_PICTURE_STATUS)
//        Observable<CareersheResponse<UserBean>> getProfilePictureStatus(@Header("token") String hToken,
//                                                                        @Header("userId") String hUserId,
//                                                                        @Field("id") String userId
//
//        );
//
//        /**
//         * 查询用户信息(获取头像)。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户id。
//         * @return
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_USER_IMAGE)
//        Observable<CareersheResponse<UserBean>> getUserInfo(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                            @Field("userId") String userId
//
//        );
//
//        /**
//         * 获取用户信息(我的页面)。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_USER_INFO_NEW)
//        Observable<CareersheResponse<MeBean>> getUserInfoNew(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                             @Field("id") String userId
//        );
//
//        /**
//         * 上传头像接口。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         *                //* @param userId      用户id。
//         * @param file    上传文件。
//         * @return 。
//         * <p>
//         * {
//         * "success": true,
//         * "errorCode": 200,
//         * "msg": "返回数据成功!",
//         * "data": null,
//         * "status": null,
//         * "spiderDiagram": null
//         * }
//         */
//        @Multipart
//        @POST(ApiUrl.UPLOAD_PROFILE_PICTURE)
//        Observable<CareersheResponse<CareersheResponse>> uploadProfilePicture(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                              //@Part("userId") String userId,
//                                                                              @Part MultipartBody.Part file
//        );
//
//        /**
//         * 查询金币兑换列表。
//         *
//         * @param userId 用户id。
//         * @return 。  {
//         * "success": true,
//         * "errorCode": 200,
//         * "msg": "返回数据成功",
//         * "status(不用管)": 1,
//         * "data": [
//         * {
//         * "_id": 98590007995924500,
//         * "images": "http",
//         * "dayCount": 1,
//         * "member": "VIP会员",
//         * "golds": 1,
//         * "_created_at": "2020-12-29 14:22:48",
//         * "_updated_at": "2020-12-29 14:22:48"
//         * }
//         * ],
//         * "occupationRandomVo(不用管)": null
//         * }
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_GOLD_LIST)
//        Observable<CareersheResponse<List<ExchangeBean>>> getGoldList(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                      @Field("userId") String userId
//        );
//
//        /**
//         * 查询总金币。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户id。
//         * @return 。 {
//         * "errorCode": 200,
//         * "msg": "返回数据成功",
//         * "status": 1,
//         * "data": {"gold":100},
//         * "occupationRandomVo": null
//         * }
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_GOLD_COUNT)
//        Observable<CareersheResponse<GoldBean>> getGoldCount(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                             @Field("userId") String userId
//        );
//
//        /**
//         * 查询总金币。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户id。
//         * @param _id     金币兑换天数id。
//         * @return 。 {
//         * "errorCode": 200,
//         * "msg": "返回数据成功",
//         * "status": 1,
//         * "data": {"gold":100},
//         * "occupationRandomVo": null
//         * }
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GOLD_EXCHANGE)
//        Observable<CareersheResponse<GoldBean>> goldExchange(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                             @Field("userId") String userId,
//                                                             @Field("_id") long _id
//        );
//
//        /**
//         * 专业详情页。
//         *
//         * @param id 专业ID。
//         * @return 院校列表.
//         */
//        @GET(ApiUrl.GET_PROFESSIONAL)
//        Observable<CareersheResponse<ProfessionBean_20210118>> getProfessional(@Query("_id") String id);
//
//        /**
//         * 专业头部(专业详情)。
//         *
//         * @param id 专业ID。
//         * @return 院校列表.
//         */
//        @GET(ApiUrl.GET_PROFESSIONAL_INFO)
//        Observable<CareersheResponse<ProfessionInfoBean>> getProfessionalInfo(@Query("_id") String id);
//
//        /**
//         * 专业介绍(专业详情)。
//         *
//         * @param id 专业ID。
//         * @return 院校列表.
//         */
//        @GET(ApiUrl.GET_PROFESSIONAL_INTRODUCE)
//        Observable<CareersheResponse<ProfessionIntroduceBean>> getProfessionalIntroduce(@Query("_id") String id);
//
//        /**
//         * 选考建议(专业详情)。
//         *
//         * @param id 专业ID。
//         * @return 院校列表.
//         */
//        @GET(ApiUrl.GET_PROFESSIONAL_CHOOSE)
//        Observable<CareersheResponse<ProfessionChooseBean>> getProfessionalChoose(@Query("_id") String id);
//
//        /**
//         * 就业信息(专业详情)。
//         *
//         * @param id 专业ID。
//         * @return 院校列表.
//         */
//        @GET(ApiUrl.GET_PROFESSIONAL_EMPLOYMENT)
//        Observable<CareersheResponse<ProfessionEmploymentBean>> getProfessionalEmployment(@Query("_id") String id);
//
//        /**
//         * 获取订单：微信支付。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         * @param orderId 订单Id。
//         *                <p>
//         *                测试链接：http://39.99.159.144/api/payment/createPaymentOrderWechat1?userId=0GLsL02v2s&orderId=6m
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.CREATE_PAYMENT_ORDER_WECHAT)
//        Observable<CareersheResponse<OrderBean>> createPaymentOrderWechat(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                          @Field("userId") String userId,
//                                                                          @Field("orderId") String orderId
//        );
//
//        /**
//         * 获取订单：支付宝支付。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         * @param orderId 订单Id。
//         *                <p>
//         *                测试链接：http://39.99.159.144/api/payment/createPaymentOrderAlipay1?userId=0GLsL02v2s&orderId=6m
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.CREATE_PAYMENT_ORDER_ALIPAY)
//        Observable<CareersheResponse<OrderBean>> createPaymentOrderAlipay(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                          @Field("userId") String userId,
//                                                                          @Field("orderId") String orderId
//        );
//
//        /**
//         * 获取充值会员订单信息。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_MEMBERSHIP_PRICE)
//        Observable<CareersheResponse<MembershipBean>> getMembershipPrice(//@Header("token") String hToken, @Header("userId") String hUserId,@Field("userId") String userId,
//                                                                         @Field("description") String description
//        );
//
//
//        /**
//         * 判断学校是否存在。
//         *
//         * @param name 学校名称。
//         * @return 是否存在。
//         */
//        @GET(ApiUrl.IS_HAVE_ACADEMY)
//        Observable<CareersheResponse<IsHaveBean>> isHaveAcademy(@Query("name") String name);
//
//        /**
//         * 查询选科剩余的次数。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId 用户Id。
//         * @return 次数。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_USER_COURSE_COUNT)
//        Observable<CareersheResponse<SelectionCountBean>> getUserCourseCount(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                             @Field("userId") String userId);
//
//
//        /**
//         * 选科查询扣减。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         * @return 次数。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_COURSE_SELECTION_COUNT)
//        Observable<CareersheResponse<SelectionCountBean>> getCourseSelectionCount(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                                  @Field("userId") String userId);
//
//        /**
//         * 验证学号开通会员。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param name 用户名
//         * @param studentId 学号。
//         * @param userId  用户Id。
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.CHECK_USER_STUDENT_NUMBER_OPEN_VIP)
//        Observable<CareersheResponse<UserBean>> checkUserStudentNumberOpenVip(@Header("token") String hToken, @Header("headerUserId") String hUserId,//@Header("userId") String hUserId,
//                                                                              @Field("name") String name,
//                                                                              @Field("studentId") String studentId,
//                                                                              @Field("userId") String userId
//        );
//
//        /**
//         * 查询活动列表。
//         *
//         //* @param hToken  请求头：Token。
//         //* @param hUserId 请求头：用户ID。
//         *
//         * @return 。
//         */
//        @GET(ApiUrl.GET_EVEN)
//        Observable<CareersheResponse<MainEven>> getEven(//@Header("token") String hToken, @Header("headerUserId") String hUserId,//@Header("userId") String hUserId,
//        );
//
//        /**
//         * 验证学号开通会员。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param content 提问内容。
//         * @param keywordId 话题。
//         * @param userId  用户Id。
//         *
//         * @return 。
//         *
//         * /biz/asking/addAsking?
//         * userId="+ParseUser.getCurrentUser().getObjectId()+
//         * "&keywordId="+keyword_selected+
//         * "&name="+content
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.ADD_ASKING)
//        Observable<CareersheResponse<AskingBean>> addAsking(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                            @Field("name") String content,
//                                                            @Field("keywordId") String keywordId,
//                                                            @Field("userId") String userId
//        );
//
//
//        /**
//         * 职业百科(所有分类)。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         *
//         * @return 。
//         */
//        @GET(ApiUrl.GET_OCCUPATION_MENU_ALL)
//        Observable<CareersheResponse<List<OccupationBean>>> getOccupationMenuAll(@Header("token") String hToken, @Header("userId") String hUserId);
//
//        /**
//         * 职业百科(所有分类)。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         *
//         * @return 。
//         */
//        @GET(ApiUrl.GET_OCCUPATION_MENU_ALL)
//        Observable<CareersheResponse<List<OccupationBean>>> getOccupationMenuAll_(@Header("token") String hToken, @Header("userId") String hUserId);
//
//
//        /**
//         * 分类ID查询对应职业。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param _id 职业Id(传空就=全部)。
//         * @param userId  用户Id。
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.get_occupation_menu)
//        Observable<CareersheResponse<List<OccupationBean>>> getOccupationMenu(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                              @Field("_id") String _id,
//                                                                              @Field("userId") String userId
//        );
//
//        /**
//         * 分类ID查询对应职业。
//         *
//         //* @param hToken  请求头：Token。
//         //* @param hUserId 请求头：用户ID。
//         * @param _id 职业Id(传空就=全部)。
//         //* @param userId  用户Id。
//         *
//         * @return 。
//         * biz/occupation_menu/getOccupationMenu
//         * &_id=YlGFBbRrIo
//         * &userId=hJDDJTIyGB
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_OCCUPATION_MENU)
//        Observable<CareersheResponse<OccupationListBean>> getOccupationMenu(//@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                            @Field("_id") String _id,
//                                                                            @Field("pageNumber") int pageNumber,
//                                                                            @Field("pageSize") int pageSize,
//                                                                            @Field("userId") String userId
//        );
//
//
//
//
//        /**
//         * TODO 补充Data
//         *
//         * 金币总数。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_USER_GOLD)
//        Observable<CareersheResponse<GoldBean>> getUserGold(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                            @Field("userId") String userId
//        );
//
//        /**
//         * TODO 补充Data
//         *
//         * 验证(今天是否已)签到(GET)。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_SIGN_DETAIL)
//        Observable<CareersheResponse<SignGoldBean>> getSignDetail(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                  @Field("userId") String userId
//        );
//
//        /**
//         * TODO 补充Data
//         *
//         * 领取任务金币(是否已经领取)。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param missionId 任务ID。
//         * @param userId  用户Id。
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_MISSION_AWARD)
//        Observable<CareersheResponse<TaskBean>> getMissionAward(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                @Field("missionId") String missionId,
//                                                                @Field("userId") String userId
//        );
//
//        /**
//         * 任务列表的数据(标题、金币权重、是否完成...)//验证是否完成所有任务。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.CHECK_USER_MISSION)
//        Observable<CareersheResponse<List<NewUserTaskBean>>> checkUserMission(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                              @Field("userId") String userId
//        );
//
//
//
//
//
//        /**
//         * 签到(每日签到)。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         *                StringRequest stringRequest2 = new StringRequest(Request.Method.GET
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.DO_SIGN)
//        Observable<CareersheResponse<SignGoldBean>> doSign(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                           @Field("userId") String userId
//        );
//
//        /**
//         * TODO
//         * 获取已签到(第1-7天)信息(GET)。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         *                "/sign/get_all_sign?userId=" + ParseUser.getCurrentUser().getObjectId();
//         *             StringRequest stringRequest2 = new StringRequest(Request.Method.GET
//         *
//         *                _id	String	签到id
//         * name	String	签到天数
//         * gold	int	签到金币数
//         * sort	int	签到排序
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_ALL_SIGN)
//        Observable<CareersheResponse<List<SignGoldBean>>> getAllSign(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                     @Field("userId") String userId
//        );




        /**
         * 获取闪屏页图片
         *
         * @param format
         * @param idx
         * @param n
         * @return
         */
        @Deprecated
        @GET("https://www.bing.com/HPImageArchive.aspx")
        Observable<CareersheResponse<ImageBean>> getImage(@Query("format") String format, @Query("idx") int idx, @Query("n") int n);


        /**
         * 获取问答列表。
         *
         * @param pageSize 一页几条。
         * @param pageNo  第几页。
         *
         * @return 提问列表。
         */
        @GET(ApiUrl.GET_ASKING_LIST_NEW)
        Observable<CareersheResponse<QnaListBean>> getAskingListNew(//@Header("token") String hToken, @Header("userId") String hUserId,
                                                                    @Query("pageSize") int pageSize,
                                                                    @Query("pageNo") int pageNo
        );


//
//        /**
//         * 获取问答列表。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param pageSize 一页几条。
//         * @param pageNo  第几页。
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_ASKING_LIST)
//        Observable<CareersheResponse<List<QNA>>> getAskingList(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                               @Field("pageSize") int pageSize,
//                                                               @Field("pageNo") int pageNo
//        );
//
//        /**
//         * 我的提问。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         * @param pageSize
//         * @param pageNo
//         * @return 提问列表。
//         */
//        @GET(ApiUrl.GET_USER_ASKING_PAGE)
//        Observable<CareersheResponse<List<QnaBean>>> getUserAskingPage(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                       @Query("pageSize") int pageSize,
//                                                                       @Query("pageNo") int pageNo,
//                                                                       @Query("userId") String userId
//        );
//
//
//        /**
//         * 我的回答。
//         *
//         * @param hToken  请求头：Token。
//         * @param hUserId 请求头：用户ID。
//         * @param userId  用户Id。
//         * @param pageSize 一页几条。
//         * @param pageNo  第几页。
//         * @return 回答列表。
//         */
//        @GET(ApiUrl.GET_USER_ANSWER_PAGE)
//        Observable<CareersheResponse<IsHaveBean>> getUserAnswerPage(@Header("token") String hToken, @Header("userId") String hUserId,
//                                                                    @Query("pageSize") String pageSize,
//                                                                    @Query("pageNo") String pageNo,
//                                                                    @Query("userId") String userId
//        );
//
//
//        /**
//         * 问答详情。
//         *
//         //* @param hToken  请求头：Token。
//         //* @param hUserId 请求头：用户ID。
//         * @param askingId 问答ID。
//         * @param pageNumber 页数。
//         * @param pageSize  第几页。
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        @POST(ApiUrl.GET_ANSWER_PAGE)
//        Observable<CareersheResponse<QnaBean>> getAnswerPage(//@Header("token") String hToken, @Header("headerUserId") String hUserId,//@Header("userId") String hUserId,
//                                                             @Field("askingId") String askingId,
//                                                             @Field("pageNumber") int pageNumber,
//                                                             @Field("pageSize") int pageSize
//        );
//
//        /**
//         * 问答详情。
//         *
//         //* @param hToken  请求头：Token。
//         //* @param hUserId 请求头：用户ID。
//         * @param askingId 问答ID。
//         *
//         * @return 。
//         */
//        @FormUrlEncoded
//        //@POST("http://39.104.167.224//api/biz/answer/getAnswerPage?pageSize=15&pageNo=1&askingId=lmgokpOc46")
//        @POST(ApiUrl.GET_ANSWER_PAGE)
//        Observable<CareersheResponse<QnaBean>> getAnswerPage(@Field("askingId") String askingId);
//


    }
}