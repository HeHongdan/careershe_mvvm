package com.careershe.careershe.model.main;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.careershe.basics.base.BaseActivity;
import com.careershe.careershe.R;
import com.careershe.common.fragment.utils.dapter.FixedFragmentPagerAdapter;

public class MainActivity extends BaseActivity {

//    @BindView(R.id.vp)
    ViewPager vp;

    private FixedFragmentPagerAdapter mPagerAdapter;
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar ab = findViewById(R.id.ab);
//        ab.setOnRightIconClickListener(new OnActionBarChildClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MainActivity.class));
//            }
//        });

        vp = findViewById(R.id.vp);

        vp.addOnPageChangeListener(onPageChangeListener);
        vp.setOffscreenPageLimit(1);
        mPagerAdapter = new FixedFragmentPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mPagerAdapter);
//        mPagerAdapter.setFragmentList(KnowledgeNavigationFragment.create(), MainFragment.create());
        mPagerAdapter.setFragmentList(MainFragment.create());
        vp.setCurrentItem(1);
        onPageChangeListener.onPageSelected(vp.getCurrentItem());
//        initCopiedTextProcessor();
//        showPrivacyPolicyDialog();
    }

}
