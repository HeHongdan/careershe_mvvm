package com.careershe.basics.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.careershe.basics.R;
import com.careershe.basics.databinding.ActivityBaseMvvmBinding;
import com.careershe.basics.databinding.ViewLoadErrorBinding;
import com.careershe.basics.databinding.ViewLoadingBinding;
import com.careershe.basics.databinding.ViewNoDataBinding;
import com.careershe.basics.databinding.ViewNoNetworkBinding;
import com.careershe.common.LoadState;

/**
 * 类描述：MVVM 架构 Activity。
 *
 * @author HHD
 * @version v2021/1/31
 * @since 2021/1/31
 */
public abstract class BaseMvvmActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity {

    /** 【fVBId】支持Lifecycle。 */
    public DB mDataBinding;
    /** 【V-*-M】。 支持Lifecycle。{@link #initViewModel()}初始化。 */
    protected VM mViewModel;

    /** 【fVBId】。子Activity的视图容器。 */
    private ActivityBaseMvvmBinding mActivityBaseBinding;

    /** 【fVBId】加载中视图。 */
    private ViewLoadingBinding mViewLoadingBinding;
    /** 【fVBId】加载出错视图。 */
    private ViewLoadErrorBinding mViewLoadErrorBinding;
    /** 【fVBId】没网络视图。 */
    private ViewNoNetworkBinding mViewNoNetworkBinding;
    /** 【fVBId】没数据视图。 */
    private ViewNoDataBinding mViewNoDataBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_mvvm);
        //把子Activity的视图填充进来并支持DataBinding
        mDataBinding = DataBindingUtil.inflate(getLayoutInflater(), setContentView_(), mActivityBaseBinding.flContentContainer,true);
        initViewModel();
        bindViewModel();
        mDataBinding.setLifecycleOwner(this);
        // ViewModel订阅生命周期事件
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }

        initLoadState();
        init();
    }

    /**
     * 初始化的多状态状态视图。
     */
    private void initLoadState() {
        if (mViewModel != null && isSupportLoad()) {
            mViewModel.loadState.observe(this, new Observer<LoadState>() {
                @Override
                public void onChanged(LoadState loadState) {
                    switchLoadView(loadState);
                }
            });
        }
    }

    /**
     * 切换多状态页面状态。
     *
     * @param loadState 页面状态。
     */
    private void switchLoadView(LoadState loadState) {
        removeLoadView();

        switch (loadState) {
            case LOADING:
                if (mViewLoadingBinding == null) {
                    mViewLoadingBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_loading,
                            mActivityBaseBinding.flContentContainer, false);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewLoadingBinding.getRoot());
                break;

            case NO_NETWORK:
                if (mViewNoNetworkBinding == null) {
                    mViewNoNetworkBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_network,
                            mActivityBaseBinding.flContentContainer, false);
                    mViewNoNetworkBinding.setViewModel(mViewModel);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoNetworkBinding.getRoot());
                break;

            case NO_DATA:
                if (mViewNoDataBinding == null) {
                    mViewNoDataBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_data,
                            mActivityBaseBinding.flContentContainer, false);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoDataBinding.getRoot());
                break;

            case ERROR:
                if (mViewLoadErrorBinding == null) {
                    mViewLoadErrorBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_load_error,
                            mActivityBaseBinding.flContentContainer, false);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewLoadErrorBinding.getRoot());
                break;

            default:
                break;
        }
    }

    /**
     * 移除掉之前填充的视图。
     */
    private void removeLoadView() {
        int childCount = mActivityBaseBinding.flContentContainer.getChildCount();
        if (childCount > 1) {
            mActivityBaseBinding.flContentContainer.removeViews(1, childCount - 1);
        }
    }


    /**
     * 获取当前页面的布局资源ID
     *
     * @return 布局资源ID
     */
    protected abstract int setContentView_();
    /**
     * 初始化ViewModel(子类必须赋值)。
     */
    protected abstract void initViewModel();

    /**
     * 绑定ViewModel
     */
    protected abstract void bindViewModel();

    /**
     * 初始化
     */
    protected abstract void init();


    /**
     * 是否支持多状态(加载)页面。默认不支持。
     *
     * @return true表示支持，false表示不支持
     */
    protected boolean isSupportLoad() {
        return false;
    }
}

