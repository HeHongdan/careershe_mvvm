package com.careershe.rxhttp.core;

import android.content.Context;
import androidx.annotation.NonNull;

import io.reactivex.Observable;
import com.careershe.rxhttp.core.exception.RxHttpUninitializedException;
import com.careershe.rxhttp.download.DownloadInfo;
import com.careershe.rxhttp.download.RxDownload;
import com.careershe.rxhttp.download.setting.DefaultDownloadSetting;
import com.careershe.rxhttp.download.setting.DownloadSetting;
import com.careershe.rxhttp.request.RxRequest;
import com.careershe.rxhttp.request.base.BaseResponse;
import com.careershe.rxhttp.request.exception.NullRequestSettingException;
import com.careershe.rxhttp.request.setting.RequestSetting;

/**
 * 类描述：结合Rxjava网络请求。
 *
 * @author HeHongdan
 * @version v2020/11/21
 * @date 2020/11/21
 */
public class RxHttp {

    /** 本类单例。 */
    private static RxHttp INSTANCE = null;
    /** （应用）上下文。 */
    private final Context mAppContext;
    /** 请求网络配置。 */
    private RequestSetting mRequestSetting = null;
    /** 请求下载配置。 */
    private DownloadSetting mDownloadSetting = null;


    /**
     * 构造方法。
     *
     * @param context （应用）上下文。
     */
    private RxHttp(Context context) {
        mAppContext = context;
    }

    /**
     * 初始化单例（一般在Application创建时调用）。
     *
     * @param context 上下文。
     */
    public static void init(@NonNull Context context) {
        INSTANCE = new RxHttp(context.getApplicationContext());
    }

    /**
     * 获取本类实例（单例）。
     *
     * @return 本类实例。
     */
    public static RxHttp getInstance() {
        if (INSTANCE == null) {
            throw new RxHttpUninitializedException();
        }
        return INSTANCE;
    }

    /**
     * 初始化请求配置。
     *
     * @param setting 请求配置。
     */
    public static void initRequest(@NonNull RequestSetting setting) {
        getInstance().mRequestSetting = setting;
    }

    /**
     * 初始化下载配置。
     *
     * @param setting 下载配置。
     */
    public static void initDownload(@NonNull DownloadSetting setting) {
        getInstance().mDownloadSetting = setting;
    }

    /**
     * 获取应用上下文。
     *
     * @return 应用上下文。
     */
    @NonNull
    public static Context getAppContext() {
        return getInstance().mAppContext;
    }

    /**
     * 获取请求配置。
     *
     * @return 请求配置。
     */
    @NonNull
    public static RequestSetting getRequestSetting() {
        RequestSetting setting = getInstance().mRequestSetting;
        if (setting == null) {
            throw new NullRequestSettingException();
        }
        return setting;
    }

    /**
     * 获取下载配置。
     *
     * @return 下载配置。
     */
    @NonNull
    public static DownloadSetting getDownloadSetting() {
        DownloadSetting setting = getInstance().mDownloadSetting;
        if (setting == null) {
            setting = new DefaultDownloadSetting();
        }
        return setting;
    }

    /**
     * 请求网络。
     *
     * @param observable 被观察者。
     * @param <T>
     * @param <R>
     * @return 网络请求。
     */
    public static <T, R extends BaseResponse<T>> RxRequest<T, R> request(@NonNull Observable<R> observable) {
        return RxRequest.create(observable);
    }

    /**
     * 下载请求。
     *
     * @param info 需要下载信息。
     * @return 下载请求。
     */
    public static RxDownload download(@NonNull DownloadInfo info) {
        return RxDownload.create(info);
    }

}
