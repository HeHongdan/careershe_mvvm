package com.careershe.careershe.model;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.careershe.basics.base.BaseFragment;
import com.careershe.careershe.R;
import com.careershe.careershe.common.Config;
import com.careershe.careershe.common.ScrollTop;
import com.careershe.careershe.widget.actionbar.ActionBar;
import com.careershe.common.fragment.utils.dapter.FixedFragmentPagerAdapter;

/**
 * @author CuiZhen
 * @date 2019/5/19
 * GitHub: https://github.com/goweii
 */
public class KnowledgeNavigationFragment extends BaseFragment implements ScrollTop {

//    @BindView(R.id.ab)
    ActionBar ab;
//    @BindView(R.id.vp)
    ViewPager vp;

    private FixedFragmentPagerAdapter mAdapter;
    private long lastClickTime = 0L;
    private int lastClickPos = 0;

    public static KnowledgeNavigationFragment create() {
        return new KnowledgeNavigationFragment();
    }


    @Override
    protected int _onCreateView() {
        return R.layout.fragment_knowledge_navigation;
    }

    @Override
    protected void initView() {
//        mAdapter = new FixedFragmentPagerAdapter(getChildFragmentManager());
//        mAdapter.setTitles("体系", "导航");
//        mAdapter.setFragmentList(
//                KnowledgeFragment.create(),
//                NaviFragment.create()
//        );
//        vp.setAdapter(mAdapter);
//        MagicIndicatorUtils.commonNavigator(ab.getView(R.id.mi), vp, mAdapter, new SimpleCallback<Integer>(){
//            @Override
//            public void onResult(Integer data) {
//                notifyScrollTop(data);
//            }
//        });
    }

    @Override
    public void lazyInit() {

    }


    @Override
    public void scrollTop() {
        if (isAdded() && !isDetached()) {
            Fragment fragment = mAdapter.getItem(vp.getCurrentItem());
            if (fragment instanceof ScrollTop) {
                ScrollTop scrollTop = (ScrollTop) fragment;
                scrollTop.scrollTop();
            }
        }
    }

    private void notifyScrollTop(int pos) {
        long currClickTime = System.currentTimeMillis();
        if (lastClickPos == pos && currClickTime - lastClickTime <= Config.SCROLL_TOP_DOUBLE_CLICK_DELAY) {
            Fragment fragment = mAdapter.getItem(vp.getCurrentItem());
            if (fragment instanceof ScrollTop) {
                ScrollTop scrollTop = (ScrollTop) fragment;
                scrollTop.scrollTop();
            }
        }
        lastClickPos = pos;
        lastClickTime = currClickTime;
    }
}
