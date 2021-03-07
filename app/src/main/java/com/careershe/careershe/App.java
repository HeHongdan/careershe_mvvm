package com.careershe.careershe;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;

//import com.blankj.utildebug.icon.DebugIcon;
import com.careershe.basics.base.BaseApp;
import com.careershe.careershe.common.BuglyTask;
import com.careershe.careershe.common.RxHttpInitTask;
import com.careershe.careershe.common.UtilsInitTask;
import com.careershe.careershe.common.CacheInitTask;
import com.careershe.common.InitTaskRunner;
import com.careershe.deprecatedhttp.data.HttpBaseResponse;
import com.careershe.deprecatedhttp.request.HttpFactory;
import com.careershe.deprecatedhttp.request.ServerAddress;
import com.careershe.deprecatedhttp.tool.HttpException;
import com.careershe.ui.widget.floatview.debug.DebugIcon;

import org.jetbrains.annotations.NotNull;


/**
 * 类描述：应用类。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        initActivityManager();
        init();
    }


    /**
     * 管理Activity
     */
    private void initActivityManager() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NotNull Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(@NotNull Activity activity) {
            }

            @Override
            public void onActivityResumed(@NotNull Activity activity) {

                ViewGroup.LayoutParams mParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

//                ViewParent parent = com.blankj.utildebug.icon.DebugIcon.getInstance().getParent();
//                if (parent != null) {
//                    ((ViewGroup) parent).removeView(com.blankj.utildebug.icon.DebugIcon.getInstance());
//                }
//                ((ViewGroup) activity.findViewById(android.R.id.content)).addView(com.blankj.utildebug.icon.DebugIcon.getInstance(), mParams);

                ViewParent parent = DebugIcon.getInstance().getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(DebugIcon.getInstance());
                }
                ((ViewGroup) activity.findViewById(android.R.id.content)).addView(DebugIcon.getInstance(), mParams);

            }

            @Override
            public void onActivityPaused(@NotNull Activity activity) {
            }

            @Override
            public void onActivityStopped(@NotNull Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(@NotNull Activity activity) {
            }
        });
    }

    /**
     * 一些第三方库和本地代码的初始化设置
     */
    private void init() {
        setHttpConfig();

        //初始化(第三方)库
        new InitTaskRunner(this)
                .add(new UtilsInitTask())
                .add(new RxHttpInitTask())
                .add(new CacheInitTask())
                .add(new BuglyTask())
//                .add(new DoKitInitTask())
                .run();
    }

    /**
     * 请求配置
     */
    public static void setHttpConfig() {
        HttpFactory.HTTP_HOST_URL = ServerAddress.getApiDefaultHost();
        HttpFactory.httpResponseInterface = (gson, response) -> {
            if (firstOpen) {
                firstOpen = false;
                return response;
            }
            HttpBaseResponse httpResponse = gson.fromJson(response, HttpBaseResponse.class);
            if (httpResponse.errorCode != 0) {
                throw new HttpException(httpResponse.errorCode,httpResponse.errorMsg);
            }
            return gson.toJson(httpResponse.data);
        };
    }

}
