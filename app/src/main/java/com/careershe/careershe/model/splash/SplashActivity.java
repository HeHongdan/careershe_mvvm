package com.careershe.careershe.model.splash;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.careershe.basics.base.BaseMvvmActivity;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.ActivitySplashBinding;
import com.careershe.deprecatedhttp.data.HttpBaseResponse;
import com.careershe.deprecatedhttp.tool.HttpException;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/3/1
 * @since v2021/3/1
 */
public class SplashActivity extends BaseMvvmActivity<ActivitySplashBinding, SplashViewModel> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int setContentView_() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        LogUtils.d("初始化-》图片");
        //加载闪屏页图片
        mViewModel.getImageData().observe(this, imageBean -> {
            if (imageBean != null) {
                String url = imageBean.getImages().get(0).getBaseUrl() + imageBean.getImages().get(0).getUrl();

                LogUtils.d("请求响应的图片= " + url);

                Glide.with(SplashActivity.this)
                        .load(url)
                        .into(mDataBinding.ivSplash);
            } else {
                //从网络获取图片失败，加载本地默认图片
                Glide.with(SplashActivity.this)
                        .load(R.mipmap.ic_launcher)
                        .into(mDataBinding.ivSplash);

                LogUtils.d("请求响应的图片为空");
            }
        });
    }

}
