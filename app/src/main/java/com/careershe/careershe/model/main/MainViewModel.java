package com.careershe.careershe.model.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.careershe.basics.base.BaseViewModel;
import com.careershe.common.LoadState;
import com.careershe.deprecatedhttp.request.ImageBean;
import com.careershe.http.BaseRequest;
import com.careershe.http.CareersheApi;
import com.careershe.http.response.ResponseListener;
import com.careershe.careershe.model.main.qna.bean.QnaListBean;
import com.careershe.rxhttp.request.exception.ExceptionHandle;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/3/1
 * @since v2021/3/1
 */
public class MainViewModel extends BaseViewModel {

    /** 每日图片 */
    private MutableLiveData<ImageBean> mImage;
    private String url;


    /** 默认一页到条数。 */
    public static final int ONE_PAGE_COUNT = 10;//TODO 写到父类
    /** 开始的页数。 */
    private static final int START_PAGE = 0;//TODO 写到父类

    /** 当前的页数。 */
    private int currPage = START_PAGE;//TODO 写到父类
    /** 问答列表。 */
    private MutableLiveData<QnaListBean> qnaList;
    /** 是否为刷新数据。 */
    public boolean refresh;//TODO 写到父类



    public MainViewModel() {
        mImage = new MutableLiveData<>();
        qnaList = new MutableLiveData<>();
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        super.onCreate(owner);

        loadData();
    }



    public LiveData<QnaListBean> getQnaData() {
        return qnaList;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }





    /**
     * 第一次加载。TODO 写到父类
     */
    public void loadData() {
        loadState.postValue(LoadState.LOADING);

        currPage = START_PAGE;
        refresh = false;
        loadQna(ONE_PAGE_COUNT, currPage);
    }

    /**
     * 刷新。TODO 写到父类
     */
    public void refreshData(boolean refresh) {
        this.refresh = refresh;
        if (refresh) {
            currPage = START_PAGE;
            loadQna(ONE_PAGE_COUNT, currPage);
        } else {
            currPage++;
            loadQna(ONE_PAGE_COUNT, currPage);
        }
    }

    /**
     * 重新加载。
     */
    @Override
    public void reloadData() {
        refresh = false;
        loadQna(ONE_PAGE_COUNT, currPage);
    }










    /**
     * 获取问答。
     */
    public void loadQna(int onePageCount, int cPage) {
        if (!NetworkUtils.isConnected()) {
            //没有网络连接
        } else {
            LogUtils.w("使用 RxLife");

            BaseRequest.netBean(mRxLife,
                    CareersheApi.api().getAskingListNew(onePageCount, cPage),
                    new ResponseListener<QnaListBean>() {
                        @Override
                        public void onSuccess(int code, QnaListBean data) {
                            LogUtils.d(code + "问答列表= " + data);

                            qnaList.postValue(data);
//                            if (data.getPageResult() != null) {
//                                currPage = data.getPageResult().getCurPage();
//                                LogUtils.d("职业百科，当前页数，后台返回= " + data.getPageResult().toString());
//                            }
//                            mNetData = data;
//                            List<QnaBean> occupationList = data.getResult();
//                            if (occupationList == null || occupationList.isEmpty()) {
//                                if (currPage == START_PAGE) {
////                                msv.setViewState(MultiStateView.ViewState.EMPTY);
//                                }
//                            } else {
////                            msv.setViewState(MultiStateView.ViewState.CONTENT);
//
//                                if (currPage == START_PAGE) {
//                                    mAdapter.setNewInstance(occupationList);
//                                    mAdapter.getLoadMoreModule().setEnableLoadMore(true);
//                                } else {
//                                    mAdapter.addData(occupationList);
//                                    mAdapter.getLoadMoreModule().loadMoreComplete();
//                                }
//                            }


                        }

                        @Override
                        public void onFailed(int code, String msg) {
                            qnaList.postValue(null);
                        }

                        @Override
                        public void onStart() {
//                            isRefreshedQnaList = false;
                        }


                        @Override
                        public void onError(ExceptionHandle handle) {
                            qnaList.postValue(null);
                        }

                        @Override
                        public void onFinish() {
//                            isRefreshedQnaList = true;
//                            getNetFinish();
                        }
                    });
        }
    }


    /**
     * 跳转到每日图片详情界面
     */
    public void startMainImageDetail() {
        LogUtils.d("跳转图片详情页面");
    }

}
