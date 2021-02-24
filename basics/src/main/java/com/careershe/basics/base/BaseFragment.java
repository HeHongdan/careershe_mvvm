package com.careershe.basics.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    /** onCreateView填充的视图。 */
    protected View view = null;
//    /** 绑定View注解。 */
//    private Unbinder mUnbinder = null;



    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (view == null) {
            final int layoutId = _onCreateView();
            if (layoutId > 0) {
                view = inflater.inflate(_onCreateView(), container, false);
            }
        }

        initButterKnife();
        initView();

        return view;
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
     * 初始化视图(资源ID)。
     */
    @Override
    protected abstract int _onCreateView();

    /**
     * 初始化视图。
     */
    protected abstract void initView();

    /**
     * 懒加载(数据)。
     */
    @Override
    public abstract void lazyInit();
}
