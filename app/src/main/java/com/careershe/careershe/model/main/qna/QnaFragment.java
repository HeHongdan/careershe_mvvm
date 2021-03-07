package com.careershe.careershe.model.main.qna;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmFragment;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.FragmentQnaBinding;
import com.careershe.careershe.model.main.qna.bean.QnaBean;
import com.careershe.careershe.model.main.qna.bean.QnaListBean;
import com.careershe.http.bean.PageBean;
import com.careershe.ui.screenmatch.utils.ConvertUtils;
import com.careershe.ui.widget.CustomLoadMoreView;
import com.careershe.ui.widget.mkitemdecoration.MKItemDecoration;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;

import java.util.List;

/**
 * @author devel
 */
public class QnaFragment extends BaseMvvmFragment<FragmentQnaBinding, QnaViewModel> {
    /** 问答适配器。 */
    private QnaAdapter mAdapter;
    /** 网络数据。 */
    private QnaListBean mNetData;

    public static QnaFragment create() {
        LogUtils.v("执行顺序");
        return new QnaFragment();
    }

    @Override
    protected void initViewModel() {
        LogUtils.d("执行顺序");

        mViewModel = new ViewModelProvider(this).get(QnaViewModel.class);
    }

    @Override
    protected int _onCreateView() {
        LogUtils.i("执行顺序");
        return R.layout.fragment_qna;
    }

    @Override
    protected void bindViewModel() {
        LogUtils.w("执行顺序");

        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    public void lazyInit() {
        LogUtils.e("执行顺序" + "懒加载");
        initAdapter();



        mViewModel.loadData();

        mViewModel.getQnaList().observe(this, qnaListBean -> {
            LogUtils.e("请求网络= " + qnaListBean.getResult().size());

            mNetData = qnaListBean;
            List<QnaBean> occupationList = qnaListBean.getResult();
            PageBean page = qnaListBean.getPageResult();
            if (occupationList != null || !occupationList.isEmpty()) {
                if (page!= null && page.getCurPage() == mViewModel.START_PAGE) {
                    mAdapter.setNewInstance(occupationList);
                    mAdapter.getLoadMoreModule().setEnableLoadMore(true);
                } else {
                    mAdapter.addData(occupationList);
                    mAdapter.getLoadMoreModule().loadMoreComplete();
                }
            }




            if (qnaListBean.getPageResult() != null){
//                mDataBinding.srl.finishLoadMoreWithNoMoreData();
            }
//            mDataBinding.srl.finishRefresh();
//            mDataBinding.srl.finishLoadMore();
//            commonAdapter.onItemDatasChanged(articleListBean.getDatas());
        });
    }



    private void initAdapter() {
        mAdapter = new QnaAdapter();
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mNetData.getPageResult().getOver()) {
                    mAdapter.getLoadMoreModule().loadMoreEnd();
                } else {
                    mViewModel.refreshData(false);
//                    getAllSign(++currPage);
//                    LogUtils.w("职业百科，当前页数= " + currPage);
                }
            }
        });
        mAdapter.getLoadMoreModule().setAutoLoadMore(true);
        mAdapter.getLoadMoreModule().setLoadMoreView(new CustomLoadMoreView());

        mDataBinding.rv.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        mDataBinding.rv.addItemDecoration(new MKItemDecoration.Builder()
                .height(ConvertUtils.dp2px(mDataBinding.rv.getContext(),0.5f))
                .drawable(ContextCompat.getDrawable(mDataBinding.rv.getContext(),R.drawable.dev2_bg_qna_decoration))
                .drawLast(false)
                .build());
        mDataBinding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    if (getActivity() != null) {
//                        getMainActivity().getMyGlobals().track(Zhuge.C04.C0407, "", "");
                    }
                }
            }
        });
        mDataBinding.rv.setAdapter(mAdapter);
    }
}
