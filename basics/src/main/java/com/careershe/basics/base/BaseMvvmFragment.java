package com.careershe.basics.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.careershe.basics.R;
import com.careershe.basics.databinding.FragmentBaseMvvmBinding;
import com.careershe.basics.databinding.ViewLoadErrorBinding;
import com.careershe.basics.databinding.ViewLoadingBinding;
import com.careershe.basics.databinding.ViewNoDataBinding;
import com.careershe.basics.databinding.ViewNoNetworkBinding;
import com.careershe.common.LoadState;

/**
 * 类描述：MVVM 架构 Fragment。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
public abstract class BaseMvvmFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment {


    /** 【fVBId】支持Lifecycle。 */
    public DB mDataBinding;
    /** 【V-*-M】。 支持Lifecycle。{@link #initViewModel()}初始化。 */
    protected VM mViewModel;

    /** 【fVBId】。子Activity的视图容器。 */
    private FragmentBaseMvvmBinding mFragmentBaseBinding;

    /** 【fVBId】加载中视图。 */
    private ViewLoadingBinding mViewLoadingBinding;
    /** 【fVBId】加载出错视图。 */
    private ViewLoadErrorBinding mViewLoadErrorBinding;
    /** 【fVBId】没网络视图。 */
    private ViewNoNetworkBinding mViewNoNetworkBinding;
    /** 【fVBId】没数据视图。 */
    private ViewNoDataBinding mViewNoDataBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //传参数
        Bundle args = getArguments();
        if (args != null) {
            handleArguments(args);
        }

        initViewModel();
        // ViewModel订阅生命周期事件
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base_mvvm, container, false);
        mDataBinding = DataBindingUtil.inflate(inflater, onCreateView_(), mFragmentBaseBinding.flContentContainer, true);
        bindViewModel();
        mDataBinding.setLifecycleOwner(this);
        initLoadState();
        init();

        return mFragmentBaseBinding.getRoot();
    }


    /**
     * 处理参数
     *
     * @param args 参数容器。
     */
    protected void handleArguments(Bundle args) {

    }

    /**
     * 初始化的多状态状态视图。
     */
    private void initLoadState() {
        if (mViewModel != null && isSupportLoad()) {
            mViewModel.loadState.observe(getViewLifecycleOwner(), new Observer<LoadState>() {
                @Override
                public void onChanged(LoadState loadState) {
                    switchLoadView(loadState);
                }
            });
        }
    }

    /**
     * 切换多状态页面状态(根据状态)。
     *
     * @param loadState 页面状态。
     */
    private void switchLoadView(LoadState loadState) {
        removeLoadView();

        switch (loadState) {
            case LOADING:
                if (mViewLoadingBinding == null) {
                    mViewLoadingBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_loading,
                            mFragmentBaseBinding.flContentContainer, false);
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewLoadingBinding.getRoot());
                break;

            case NO_NETWORK:
                if (mViewNoNetworkBinding == null) {
                    mViewNoNetworkBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_network,
                            mFragmentBaseBinding.flContentContainer, false);
                    mViewNoNetworkBinding.setViewModel(mViewModel);
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewNoNetworkBinding.getRoot());
                break;

            case NO_DATA:
                if (mViewNoDataBinding == null) {
                    mViewNoDataBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_data,
                            mFragmentBaseBinding.flContentContainer, false);
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewNoDataBinding.getRoot());
                break;

            case ERROR:
                if (mViewLoadErrorBinding == null) {
                    mViewLoadErrorBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_load_error,
                            mFragmentBaseBinding.flContentContainer, false);
                    mViewLoadErrorBinding.setViewModel(mViewModel);
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewLoadErrorBinding.getRoot());
                break;

            default:
                break;
        }
    }

    /**
     * 移除掉之前填充的视图。
     */
    private void removeLoadView() {
        int childCount = mFragmentBaseBinding.flContentContainer.getChildCount();
        if (childCount > 1) {
            mFragmentBaseBinding.flContentContainer.removeViews(1, childCount - 1);
        }
    }


    /**
     * 初始化ViewModel(子类必须赋值)。
     */
    protected abstract void initViewModel();
    /**
     * 获取当前页面的布局资源ID
     *
     * @return 布局资源ID
     */
    protected abstract int onCreateView_();
    /**
     * 绑定ViewModel
     */
    protected abstract void bindViewModel();

    /**
     * 是否支持多状态(加载)页面。默认不支持。
     *
     * @return true表示支持，false表示不支持
     */
    protected boolean isSupportLoad() {
        return false;
    }
    /**
     * 初始化
     */
    protected abstract void init();
}
