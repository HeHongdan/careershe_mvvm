package com.careershe.rxhttp.deprecatedhttp.tool;

import com.blankj.utilcode.util.SPUtils;

/**
 * 类描述：SP辅助类。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class SPHelper {

    /** 关键字：SP。 */
    public static final class HawkCode {
        /** 关键字：SP的文件名称。 */
        public static final String SP_NAME = "deprecatedhttp";

        public static final String LOGIN_DATA = "login_data";
        public static final String COOKIE = "cookie";
        public static final String HEADER_IMAGE = "header_image";
        public static final String SPLASH_IMAGE_URL = "splash_image_url";

    }

    public static SPUtils getHttpSp() {
        return SPUtils.getInstance(HawkCode.SP_NAME);

        //getHttpSp().getInt(view.getClass().getSimpleName() + ".heightL", height);
        //getHttpSp().put(view.getClass().getSimpleName() + ".alpha", alpha);
    }

}
