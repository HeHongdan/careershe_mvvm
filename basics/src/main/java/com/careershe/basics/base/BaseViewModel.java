package com.careershe.basics.base;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.R;
import com.careershe.common.LoadState;
import com.careershe.rxhttp.core.RxLife;

/**
 * 类描述：实现 Lifecycle 的 ViewModel。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
public abstract class BaseViewModel extends ViewModel implements DefaultLifecycleObserver {

    /** 管理网络请求生命周期。 */
    protected RxLife mRxLife;

    /** 资源。 */
    public Resources resources;
    /** 多状态页面(当前)状态。*/
    public MutableLiveData<LoadState> loadState = new MutableLiveData<>();
    /** 加载出错文本。 */
    public MutableLiveData<String> errorMsg = new MutableLiveData<String>(getResources().getString(R.string.load_error));


    public Resources getResources() {
        if (resources == null) {
            resources = BaseApp.getContext().getResources();
        }
        return resources;
    }


    /**
     * 重新加载数据。没有网络，点击重试时回调。
     */
    public void reloadData() {

    }



    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        mRxLife = RxLife.create();
        LogUtils.w("初始化 RxLife");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        LogUtils.w("初始化 RxLife");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (mRxLife != null) {
            mRxLife.destroy();
            LogUtils.w("销毁 RxLife");
        }
    }

}
