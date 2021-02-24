package com.careershe.careershe.model.main;

import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.careershe.careershe.model.KnowledgeNavigationFragment;
import com.careershe.common.fragment.utils.dapter.TabFragmentPagerAdapter;


import com.careershe.basics.base.BaseFragment;
import com.careershe.careershe.R;

/**
 * 类描述：主Fragment(首页、问答、任务、我的)。
 *
 * @author HeHongdan
 * @date 2/24/21
 * @since v2/24/21
 */
public class MainFragment extends BaseFragment {

//    @BindView(R.id.vp_tab)
    ViewPager vp_tab;
    /** 底部的导航栏。 */
//    @BindView(R.id.ll_bb)
    LinearLayout ll_bb;

    private TabFragmentPagerAdapter.Page<TabEntity> mMinePage;
    private TabFragmentPagerAdapter<TabEntity> mTabFragmentPagerAdapter;



    public static MainFragment create() {
        return new MainFragment();
    }

    @Override
    protected int _onCreateView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        vp_tab = findViewById(R.id.vp_tab);
        ll_bb = findViewById(R.id.ll_bb);

        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter<>(getChildFragmentManager(), vp_tab, ll_bb, R.layout.tab_item_main);
//        mMinePage = new TabFragmentPagerAdapter.Page<>(MineFragment.create(), new TabEntity("我的", R.drawable.ic_bottom_bar_mine, -1), new MainTabAdapter());
        mMinePage = new TabFragmentPagerAdapter.Page<>(KnowledgeNavigationFragment.create(), new TabEntity("我的", R.drawable.ic_bottom_bar_mine, 4), new MainTabAdapter());
        mTabFragmentPagerAdapter.setPages(
//                new TabFragmentPagerAdapter.Page<>(HomeFragment.create(), new TabEntity("首页", R.drawable.ic_bottom_bar_home, -1), new MainTabAdapter()),
//                new TabFragmentPagerAdapter.Page<>(QuestionFragment.create(), new TabEntity("问答", R.drawable.ic_bottom_bar_wechat, -1), new MainTabAdapter()),
                new TabFragmentPagerAdapter.Page<>(KnowledgeNavigationFragment.create(), new TabEntity("首页", R.drawable.ic_bottom_bar_home, 123), new MainTabAdapter()),
                new TabFragmentPagerAdapter.Page<>(KnowledgeNavigationFragment.create(), new TabEntity("问答", R.drawable.ic_bottom_bar_wechat, -1), new MainTabAdapter()),
                new TabFragmentPagerAdapter.Page<>(KnowledgeNavigationFragment.create(), new TabEntity("体系", R.drawable.ic_bottom_bar_navi, -1), new MainTabAdapter()),
                mMinePage
        );
    }

    @Override
    public void lazyInit() {
        mMinePage.getData().setMsgCount(0);
        mTabFragmentPagerAdapter.notifyPageDataChanged();
    }

}
