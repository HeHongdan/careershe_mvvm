package com.careershe.ui.floatview.p;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.careershe.ui.R;
import com.careershe.ui.floatview.listener.OnBackListener;
import com.careershe.ui.floatview.listener.OnRefreshListener;

/**
 * 类描述：(调试)浮动窗口。
 *
 * RelativeLayout
 *  ┗━━BaseFloatView
 *      ┗━━BaseContentFloatView
 *          ┗━━BaseContentView
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 *
 *
 * <pre>
 *     author: blankj
 *     blog  : http://blankj.com
 *     time  : 2019/09/01
 *     desc  :
 * </pre>
 */
public abstract class BaseContentView<T extends BaseContentFloatView<T>> extends FrameLayout
        implements OnBackListener {

    private T       mFloatView;
    private boolean isAddStack;

    private OnRefreshListener mRefreshRunnable;
    private boolean           mRefreshEnabled;

    public BaseContentView(@NonNull Context context) {
        super(context);
        setId(R.id.baseContentView);
        inflate(getContext(), bindLayout(), this);
    }

    public void attach(T floatView, boolean isAddStack) {
        this.mFloatView = floatView;
        this.isAddStack = isAddStack;
        floatView.replace(this, isAddStack);
    }

    @LayoutRes
    public abstract int bindLayout();

    public abstract void onAttach();

    public T getFloatView() {
        return mFloatView;
    }

    public boolean isAddStack() {
        return isAddStack;
    }

    public void setOnRefreshListener(RecyclerView rv, OnRefreshListener listener) {
        mRefreshRunnable = listener;
        mFloatView.setOnRefreshListener(listener);
        attachRefresh(rv);
    }

    @Override
    public void onBack() {
    }

    OnRefreshListener getOnRefreshListener() {
        return mRefreshRunnable;
    }

    boolean isRefreshEnabled() {
        return mRefreshEnabled;
    }

    private void attachRefresh(RecyclerView rv) {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mRefreshEnabled = newState == RecyclerView.SCROLL_STATE_IDLE && !recyclerView.canScrollVertically(-1);
                mFloatView.setRefreshEnabled(mRefreshEnabled);
            }
        });
        mRefreshEnabled = !rv.canScrollVertically(-1);
        mFloatView.setRefreshEnabled(mRefreshEnabled);
    }
}
