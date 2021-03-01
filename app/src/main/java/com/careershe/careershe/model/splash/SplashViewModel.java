package com.careershe.careershe.model.splash;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.careershe.basics.base.BaseViewModel;
import com.careershe.deprecatedhttp.data.HttpDisposable;
import com.careershe.deprecatedhttp.request.HttpFactory;
import com.careershe.deprecatedhttp.request.HttpRequest;
import com.careershe.deprecatedhttp.request.ImageBean;
import com.careershe.deprecatedhttp.request.ServerAddress;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/3/1
 * @since v2021/3/1
 */
public class SplashViewModel  extends BaseViewModel {

    /**
     * 每日图片
     */
    private MutableLiveData<ImageBean> mImage;
    private String url;

    public SplashViewModel() {
        mImage = new MutableLiveData<>();
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        super.onCreate(owner);

        loadImageView();
    }

    public LiveData<ImageBean> getImageData() {
        return mImage;
    }


    /**
     * 获取Bing每日图片
     */
    public void loadImageView() {
        if (!NetworkUtils.isConnected()) {
            //没有网络连接
        } else {
            HttpRequest.getInstance(ServerAddress.API_BING)
                    .getImage("js", 0, 1)
                    .compose(HttpFactory.schedulers())
                    .subscribe(new HttpDisposable<ImageBean>() {
                        @Override
                        public void success(ImageBean imageBean) {
                            LogUtils.i("请求网络成功= "+ imageBean);

                            mImage.postValue(imageBean);
                            url = imageBean.getImages().get(0).getCopyrightlink();
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.e("请求网络失败= "+ e);

                            mImage.postValue(null);
                        }
                    });
        }
    }

    /**
     * 跳转到每日图片详情界面
     */
    public void startSplashImageDetail() {
        LogUtils.d("跳转图片详情页面");
    }

}
