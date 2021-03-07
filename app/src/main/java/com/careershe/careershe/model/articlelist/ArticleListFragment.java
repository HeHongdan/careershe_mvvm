package com.careershe.careershe.model.articlelist;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmFragment;
import com.careershe.careershe.BR;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.FragmentListBinding;
import com.careershe.deprecatedhttp.bean.ArticleBean;
import com.scwang.smart.refresh.header.ClassicsHeader;

/**
 * @author devel
 */
public class ArticleListFragment extends BaseMvvmFragment<FragmentListBinding, ArticleListViewModel> {

    private int type;
    private int id;

    public static final String PARAM1 = "param_1";
    public static final String PARAM2 = "param_2";
    
    
    
    public static ArticleListFragment newInstance(int type, int id) {
        ArticleListFragment articleListFragment = new ArticleListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM1, type);
        bundle.putInt(PARAM2, id);
        articleListFragment.setArguments(bundle);
        return articleListFragment;
    }

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        type = args.getInt(PARAM1);
        id = args.getInt(PARAM2);
    }

    @Override
    protected boolean isSupportLoad() {
        return true;
    }


    @Override
    protected void initViewModel() {

        mViewModel = ViewModelProviders.of(this).get(ArticleListViewModel.class);
    }

    @Override
    protected int _onCreateView() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void lazyInit() {

    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {

        mViewModel.setType(type);
        mViewModel.setId(id);
        mViewModel.loadData();

        initRefreshLayout();

        initRecyclerView();
    }

    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setPrimaryColorsId(android.R.color.white, R.color.colorPrimary);
        mDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> mViewModel.refreshData(true));
        mDataBinding.refreshLayout.setOnLoadMoreListener(refresh -> mViewModel.refreshData(false));
    }


    private void initRecyclerView() {

        CommonAdapter commonAdapter = new CommonAdapter<ArticleBean>(R.layout.item_article, BR.articleBean) {
            @Override
            public void addListener(View root, ArticleBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.card_view).setOnClickListener(v ->
//                        DetailsActivity.start(getActivity(), itemData.getLink())
                                LogUtils.d("点击跳转")
                );

                root.findViewById(R.id.iv_collect).setOnClickListener(v -> {
                    itemData.setCollect(!itemData.isCollect());
                    notifyDataSetChanged();
                    LogUtils.d("收藏");
//                    mViewModel.changeArticleCollect(itemData.isCollect(), itemData.getId());
                });
            }
        };
        mDataBinding.recycle.setAdapter(commonAdapter);
        mDataBinding.recycle.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getArticleList().observe(this, articleListBean -> {
            if (articleListBean.getCurPage() >= articleListBean.getPageCount()) {
                mDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
            }
            mDataBinding.refreshLayout.finishRefresh();
            mDataBinding.refreshLayout.finishLoadMore();

            commonAdapter.onItemDatasChanged(articleListBean.getDatas());
        });
    }

    /**
     * 滚动到顶部
     */
    public void scrollToTop() {
        mDataBinding.recycle.smoothScrollToPosition(0);
    }
}
