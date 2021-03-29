package com.careershe.careershe.model.main.task;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.careershe.basics.base.BaseViewModel;
import com.careershe.careershe.model.main.qna.bean.QnaBean;
import com.careershe.careershe.model.main.qna.bean.QnaListBean;
import com.careershe.common.LoadState;
import com.careershe.http.BaseRequest;
import com.careershe.http.CareersheApi;
import com.careershe.http.response.ResponseListener;
import com.careershe.rxhttp.request.exception.ExceptionHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author devel
 */
public class TaskViewModel extends BaseViewModel {

    private MutableLiveData<QnaListBean> mQnaLists;
    private List<QnaBean> mQnaList;

    public TaskViewModel() {
        mQnaLists = new MediatorLiveData<>();
        mQnaList = new ArrayList<>();
    }

    public LiveData<QnaListBean> getQnaList() {
        return mQnaLists;
    }

    /**
     * 刷新
     */
    public void refreshData(Boolean refresh) {
        if (refresh) {
            currPage = START_PAGE;
        } else {
            currPage++;
        }
        mRefresh = true;
        loadQnaList();
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

        currPage = 0;
        mRefresh = false;
        loadQnaList();
    }


    /**
     * 加载文章列表
     */
    private void loadQnaList() {
        //判断网络
        if (!NetworkUtils.isConnected()) {
            loadState.postValue(LoadState.NO_NETWORK);
            return;
        }

        loadWeChatArticleList();
    }

    /**
     * 加载微信公众号数据
     */
    private void loadWeChatArticleList() {
        BaseRequest.netBean(mRxLife,
                CareersheApi.api().getAskingListNew(ONE_PAGE_COUNT, currPage),
                new ResponseListener<QnaListBean>() {
                    @Override
                    public void onSuccess(int code, QnaListBean data) {
                        LogUtils.d(code + "问答列表= " + data);

                        if (data.getPageResult() != null) {
                            currPage = data.getPageResult().getCurPage();
                            LogUtils.d("职业百科，当前页数，后台返回= " + data.getPageResult().toString());

                            if (currPage == START_PAGE) {
                                //第一次加载或刷新成功//清空列表，重新载入数据，设置刷新成功状态
                                mQnaList.clear();
                                mQnaList.addAll(data.getResult());
                                mQnaLists.postValue(data);
                            } else {
                                //下拉加载更多成功//添加数据，设置下拉加载成功状态
                                mQnaList.addAll(data.getResult());
//                                data.setResult(mQnaList);
                                mQnaLists.postValue(data);
                            }
                        } else {
                            loadState.postValue(LoadState.NO_DATA);
                        }
                    }

                    @Override
                    public void onFailed(int code, String msg) {

                    }

                    @Override
                    public void onStart() {
//                        isRefreshedQnaList = false;
                    }


                    @Override
                    public void onError(ExceptionHandle handle) {

                    }

                    @Override
                    public void onFinish() {
//                        isRefreshedQnaList = true;
//                        getNetFinish();
                    }
                });
    }



}
