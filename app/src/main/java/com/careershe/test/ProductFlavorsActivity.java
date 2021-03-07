package com.careershe.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.careershe.careershe.BuildConfig;
import com.careershe.careershe.Config;
import com.careershe.careershe.R;
import com.careershe.deprecatedhttp.data.HttpBaseResponse;
import com.careershe.deprecatedhttp.data.HttpDisposable;
import com.careershe.deprecatedhttp.request.HttpFactory;
import com.careershe.deprecatedhttp.request.HttpRequest;
import com.careershe.deprecatedhttp.request.ImageBean;
import com.careershe.deprecatedhttp.request.ServerAddress;
import com.careershe.deprecatedhttp.tool.HttpException;

public class ProductFlavorsActivity extends AppCompatActivity {

    private TextView mTvVersion;
    private TextView mTvUseJava;
    private ImageView mImgGirl;
    private ImageView mImgAssetsGirl;
    private ImageView mImgNet;
    private TextView mTvQQId;
    private TextView mTvWxId;
    private TextView mTvLibraryText;
    private TextView mTvSharedUserIdTest;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_product_flavors);

        mTvVersion = findViewById(R.id.tvVersion);
        mTvUseJava = findViewById(R.id.tvUseJava);
        mImgGirl = findViewById(R.id.imgGirl);
        mImgAssetsGirl = findViewById(R.id.imgAssetsGirl);
        mImgNet = findViewById(R.id.imgNet);
        mTvQQId = findViewById(R.id.tvQQId);
        mTvWxId = findViewById(R.id.tvWxId);

        mTvLibraryText = findViewById(R.id.tvLibraryText);
        mTvSharedUserIdTest = findViewById(R.id.tvSharedUserIdTest);

        mTvVersion.setText(getString(R.string.version_) + BuildConfig.VERSION_NAME);
        mTvUseJava.setText(getString(R.string.use_java) + Config.CONFIG);

        mImgGirl.setImageResource(R.mipmap.girl);
        Glide.with(this)
                .load("file:///android_asset/img.jpg")
                .into(mImgAssetsGirl);

        Glide.with(this)
                .load(BuildConfig.LOGOURL)
                .into(mImgNet);

        mTvQQId.setText(getString(R.string.qq_id) + BuildConfig.QQ_ID);
        mTvWxId.setText(getString(R.string.wx_id) + BuildConfig.WX_ID);

        mTvLibraryText.setText(getString(R.string.url_text_change) + com.careershe.ui.BuildConfig.LIBRARY_URL);


        // 测试sharedUserId变更
        PreferenceManager
                .getDefaultSharedPreferences(this)
                .edit()
                .putString("app_pkg", BuildConfig.APPLICATION_ID)
                .commit(); // 将当前的包名保存到SharedPreferences

        // 如果是miuiApk，那么不参与
        if ("com.careershe.careershe".equals(BuildConfig.APPLICATION_ID)) {
            mTvSharedUserIdTest.setText(getString(R.string.shareduserid_info) + "正式版本是被测试对象，请打开其他进行观看该项。包名匹配");
            return;
        }

        try {
            // 获取miuiApk中保存的包名
            Context otherApkContext = createPackageContext("com.careershe.careershe",
                    Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);

            String str = PreferenceManager.getDefaultSharedPreferences(otherApkContext).getString("app_pkg", null);

            if (str == null) {
                mTvSharedUserIdTest.setText(getString(R.string.shareduserid_info) + "正式版本 sharedUserId 不匹配");
            } else {
                mTvSharedUserIdTest.setText(getString(R.string.shareduserid_info)
                        + "匹配成功-"
                        + str);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            mTvSharedUserIdTest.setText(getString(R.string.shareduserid_info) + "正式版本 sharedUserId 不匹配");
        }





        firstOpen = true;
        setHttpConfig();
    }


    public static boolean firstOpen;
    /**
     * 配置Http。
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
                throw new HttpException(httpResponse.errorCode, httpResponse.errorMsg);
            }

            return gson.toJson(httpResponse.data);
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        HttpRequest.getInstance(ServerAddress.API_BING)
                .getImage("js", 0, 1)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<ImageBean>() {
                    @Override
                    public void success(ImageBean imageBean) {
                        LogUtils.i("请求图片(成功)= " + (imageBean == null ? "" : imageBean.getImages().get(0).getCopyrightlink()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("请求图片(出错)= "+e);
                    }
                });
    }
}
