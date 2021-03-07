package com.careershe.careershe.model.articlelist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.careershe.basics.base.BaseViewModel;
import com.careershe.common.LoadState;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * @author devel
 */

public class CollectViewModel extends BaseViewModel {

    /**
     * 是否为刷新数据
     */
    public boolean mRefresh;

    private MutableLiveData<CollectArticleBean> mArticleList;
    private List<CollectArticleBean.CollectBean> mList;

    /**
     * 请求页码
     */
    private int mPage = 0;

    public CollectViewModel() {
        mArticleList = new MutableLiveData<>();
        mList = new ArrayList<>();
    }

    public LiveData<CollectArticleBean> getArticleList() {
        return mArticleList;
    }

    /**
     * 刷新
     */
    public void refreshData(Boolean refresh) {
        if (refresh) {
            mPage = 0;
        } else {
            mPage++;
        }
        mRefresh = true;
        loadArticleList();
    }


    /**
     * 重新加载
     */
    @Override
    public void reloadData() {
        loadData();
    }


    /**
     * 第一次加载数据
     */
    public void loadData() {
        loadState.postValue(LoadState.LOADING);
        mPage = 0;
        mRefresh = false;
        loadArticleList();
    }

    /**
     * 获取收藏文章列表
     */
    private void loadArticleList() {
    }



}