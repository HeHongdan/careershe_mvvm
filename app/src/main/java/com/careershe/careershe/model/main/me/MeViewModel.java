package com.careershe.careershe.model.main.me;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.NetworkUtils;
import com.careershe.basics.base.BaseViewModel;
import com.careershe.common.LoadState;
import com.careershe.deprecatedhttp.bean.ArticleBean;
import com.careershe.deprecatedhttp.bean.ArticleListBean;
import com.careershe.deprecatedhttp.data.HttpDisposable;
import com.careershe.deprecatedhttp.request.HttpFactory;
import com.careershe.deprecatedhttp.request.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author devel
 */
public class MeViewModel extends BaseViewModel {

    /**
     * 是否为刷新数据
     */
    public boolean mRefresh;

    private int mType = 0;
    private int mPage = 0;
    private int mId = 0;
    private MutableLiveData<ArticleListBean> mArticleList;
    private List<ArticleBean> mList;

    public MeViewModel() {
        mArticleList = new MediatorLiveData<>();
        mList = new ArrayList<>();
    }

    public void setType(int type) {
        this.mType = type;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public LiveData<ArticleListBean> getArticleList() {
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
     * 加载文章列表
     */
    private void loadArticleList() {
        //判断网络
        if (!NetworkUtils.isConnected()) {
            loadState.postValue(LoadState.NO_NETWORK);
            return;
        }

        if (mType == 0) {
            loadWeChatArticleList();
        } else if (mType == 1){
            loadSystemArticleList();
        }else {
            loadProjectArticleList();
        }
    }

    /**
     * 加载微信公众号数据
     */
    private void loadWeChatArticleList() {
        //微信公众号的页码是从1开始的
        mPage++;
        HttpRequest.getInstance()
                .getWechatArticleList(mId, mPage)
                .compose(HttpFactory.<ArticleListBean>schedulers())
                .subscribe(new HttpDisposable<ArticleListBean>() {
                    @Override
                    public void success(ArticleListBean mArticleListBean) {

                        if (mArticleListBean != null) {
                            loadState.postValue(LoadState.SUCCESS);
                            if (mPage == 1) {
                                //第一次加载或刷新成功//清空列表，重新载入数据，设置刷新成功状态
                                mList.clear();
                                mList.addAll(mArticleListBean.getDatas());
                                mArticleList.postValue(mArticleListBean);
                            } else {
                                //下拉加载更多成功//添加数据，设置下拉加载成功状态
                                mList.addAll(mArticleListBean.getDatas());
                                mArticleListBean.setDatas(mList);
                                mArticleList.postValue(mArticleListBean);
                            }
                        } else {
                            loadState.postValue(LoadState.NO_DATA);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }

    /**
     * 加载体系文章数据
     */
    private void loadSystemArticleList() {

        HttpRequest.getInstance()
                .getSystemArticle(mPage, mId)
                .compose(HttpFactory.<ArticleListBean>schedulers())
                .subscribe(new HttpDisposable<ArticleListBean>() {
                    @Override
                    public void success(ArticleListBean mArticleListBean) {

                        if (mArticleListBean != null) {
                            loadState.postValue(LoadState.SUCCESS);
                            if (mPage == 0) {
                                //第一次加载或刷新成功//清空列表，重新载入数据，设置刷新成功状态
                                mList.clear();
                                mList.addAll(mArticleListBean.getDatas());
                                mArticleList.postValue(mArticleListBean);
                            } else {
                                //下拉加载更多成功//添加数据，设置下拉加载成功状态
                                mList.addAll(mArticleListBean.getDatas());
                                mArticleListBean.setDatas(mList);
                                mArticleList.postValue(mArticleListBean);
                            }
                        } else {
                            loadState.postValue(LoadState.NO_DATA);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }

    /**
     * 加载项目文章数据
     */
    private void loadProjectArticleList() {
        mPage++;

        HttpRequest.getInstance()
                .getProjectArticle(mPage, mId)
                .compose(HttpFactory.<ArticleListBean>schedulers())
                .subscribe(new HttpDisposable<ArticleListBean>() {
                    @Override
                    public void success(ArticleListBean mArticleListBean) {

                        if (mArticleListBean != null) {
                            loadState.postValue(LoadState.SUCCESS);

                            if (mPage == 1) {
                                //第一次加载或刷新成功//清空列表，重新载入数据，设置刷新成功状态
                                mList.clear();
                                mList.addAll(mArticleListBean.getDatas());
                                mArticleList.postValue(mArticleListBean);
                            } else {
                                //下拉加载更多成功//添加数据，设置下拉加载成功状态
                                mList.addAll(mArticleListBean.getDatas());
                                mArticleListBean.setDatas(mList);
                                mArticleList.postValue(mArticleListBean);
                            }
                        } else {
                            loadState.postValue(LoadState.NO_DATA);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }
}
