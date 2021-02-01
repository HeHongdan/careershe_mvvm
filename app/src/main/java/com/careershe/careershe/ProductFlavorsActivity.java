package com.careershe.careershe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.careershe.ui.floatview.FloatToast;
import com.careershe.ui.floatview.debug.DebugIcon;
import com.careershe.ui.floatview.debug.DebugUtils;

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
        setContentView(R.layout.activity_product_flavors);

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

    }

    @Override
    protected void onResume() {
        super.onResume();

        ViewGroup.LayoutParams mParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );


        //LogUtils.i( "初始化= "+ ProductFlavorsActivity.this);

        //DebugUtils.setIconId(R.drawable.du_ic_icon_default);

//        ViewParent parent = DebugIcon.getInstance().getParent();
//        if (parent != null) {
//            ((ViewGroup) parent).removeView(DebugIcon.getInstance());
//        }
//        ((ViewGroup) ProductFlavorsActivity.this.findViewById(android.R.id.content)).addView(DebugIcon.getInstance(), mParams);
    }
}
