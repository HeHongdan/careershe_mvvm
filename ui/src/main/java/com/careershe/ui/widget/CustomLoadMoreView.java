package com.careershe.ui.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.careershe.ui.R;
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

//import com.chad.library.adapter.base.loadmore.LoadMoreView;

/**
 * 类描述：自定义（没有更多）底部布局。
 *
 * @author HeHongdan
 * @version v12/3/20
 * @date 12/3/20
 */
public class CustomLoadMoreView extends BaseLoadMoreView {//CustomLoadMoreView

    @NotNull
    @Override
    public View getRootView(@NotNull ViewGroup parent) {
        //view_load_more
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_foot_loading, parent, false);
    }

    @NotNull
    @Override
    public View getLoadingView(@NotNull BaseViewHolder holder) {
        //load_more_loading_view
        return holder.findView(R.id.load_more_loading_view);
    }

    @NotNull
    @Override
    public View getLoadComplete(@NotNull BaseViewHolder holder) {
        //load_more_load_complete_view
        return holder.findView(R.id.load_more_load_complete_view);
    }

    @NotNull
    @Override
    public View getLoadEndView(@NotNull BaseViewHolder holder) {
        //load_more_load_end_view
        return holder.findView(R.id.no_more_view);
    }

    @NotNull
    @Override
    public View getLoadFailView(@NotNull BaseViewHolder holder) {
        //load_more_load_fail_view
        return holder.findView(R.id.load_more_load_fail_view);
    }

//public class CustomFootView extends LoadMoreView {
//    @Override
//    public int getLayoutId() {
//        return R.layout.item_rv_foot_loading;
//    }
//
//    /**
//     * 如果返回true，数据全部加载完毕后会隐藏加载更多
//     * 如果返回false，数据全部加载完毕后会显示getLoadEndViewId()布局
//     */
//    @Override public boolean isLoadEndGone() {
//        return true;
//    }
//
//    @Override
//    protected int getLoadingViewId() {
//        return R.id.load_more_loading_view;
//    }
//
//    @Override
//    protected int getLoadFailViewId() {
//        return R.id.load_more_load_fail_view;
//    }
//
//    @Override
//    protected int getLoadEndViewId() {
//        return R.id.no_more_view;
//    }


}
