package com.careershe.basics.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.common.fragment.lazy.x.LazyFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 类描述：实现缓存、懒加载...。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
public abstract class BaseFragment extends LazyFragment {

//    /** 绑定View注解。 */
//    private Unbinder mUnbinder = null;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LogUtils.v("Fragment 视图= "+ getRootView());

        if (getRootView() == null) {
            final int layoutId = _onCreateView();
            setLayoutId(layoutId);
            LogUtils.v("Fragment 布局ID= "+ getLayoutId());
            if (layoutId > 0) {
                LogUtils.v("Fragment 布局ID(大于0)= "+ getLayoutId());
                setRootView(inflater.inflate(layoutId, container, false));
            }
        }

        initButterKnife();
        initView();

        LogUtils.w("Fragment 视图= "+ getRootView());
        return getRootView();
    }



    /**
     * 绑定View注解。
     */
    private void initButterKnife() {
//        if (view != null) {
//            mUnbinder = ButterKnife.bind(this, view);
//        }
    }



    /**
     * 初始化视图(资源ID)（onCreateView：CacheFragment）。
     *
     * @return 布局资源ID。
     */
    @Override
    protected abstract int _onCreateView();

    /**
     * 初始化视图（onCreateView：BaseFragment）。
     */
    protected abstract void initView();

    /**
     * 懒加载(数据)（onResume：LazyFragment）。
     */
    @Override
    public abstract void lazyInit();

}
