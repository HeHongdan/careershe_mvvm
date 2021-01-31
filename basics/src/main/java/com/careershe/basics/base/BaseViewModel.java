package com.careershe.basics.base;

import android.content.res.Resources;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.careershe.basics.R;
import com.careershe.common.LoadState;

/**
 * 类描述：实现 Lifecycle 的 ViewModel。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
public abstract class BaseViewModel extends ViewModel implements DefaultLifecycleObserver {

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
     * 重新加载数据。没有网络，点击重试时回调
     */
    public void reloadData() {

    }

}
