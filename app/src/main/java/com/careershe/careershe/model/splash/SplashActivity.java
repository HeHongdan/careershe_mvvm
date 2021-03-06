package com.careershe.careershe.model.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.careershe.basics.base.BaseMvvmActivity;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.ActivitySplashBinding;
import com.careershe.careershe.model.K2NavigationFragment;
import com.careershe.http.bean.QnaListBean;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/3/1
 * @since v2021/3/1
 */
public class SplashActivity extends BaseMvvmActivity<ActivitySplashBinding, SplashViewModel> {
    /** 关键字：指定(首页、问答、任务、我的)标签。 */
    public static final String TAB_ = "TAB_";
    public static final int TAB_INDEX= 0;
    public static final int TAB_QNA= 1;
    public static final int TAB_TASK = 2;
    public static final int TAB_ME = 3;


    /** 底部导航栏按钮文本。 */
    private String[] tabText = {"首页", "问答", "任务", "我的"};
    /** 底部导航栏按钮（未选）图标。 */
    private int[] normalIcon = {R.drawable.ic_index, R.drawable.ic_qna, R.drawable.ic_task, R.drawable.ic_me};
    /** 底部导航栏按钮（选中）图标。 */
    private int[] selectIcon = {R.drawable.ic_index_select, R.drawable.ic_qna_select, R.drawable.ic_task_select, R.drawable.ic_me_select};
    /** 指定的标签。 */
    private int tabPosition = 0;
    /** 底部导航栏对应Fragment。 */
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int setContentView_() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        LogUtils.d("初始化（Activity）initViewModel");
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }




    //ArticleListFragment
    @Override
    protected void init() {
        LogUtils.w("请求问答（init）");
//        mViewModel.loadQna(ONE_PAGE_COUNT, currPage);

        mViewModel.getQnaData().observe(this, qnaListBean -> {
            if (qnaListBean != null) {
                LogUtils.w("请求问答 成功= " + qnaListBean.getResult().size());
            } else {
                LogUtils.e("请求问答 失败");
            }
        });

        if (false){
            LogUtils.d("初始化-》图片");
            //加载闪屏页图片
            mViewModel.getImageData().observe(this, imageBean -> {
                if (imageBean != null) {
                    String url = imageBean.getImages().get(0).getBaseUrl() + imageBean.getImages().get(0).getUrl();

                    LogUtils.d("请求响应的图片= " + url);

                    Glide.with(SplashActivity.this)
                            .load(url)
                            .into(mDataBinding.ivSplash);
                } else {
                    //从网络获取图片失败，加载本地默认图片
                    Glide.with(SplashActivity.this)
                            .load(R.mipmap.ic_launcher)
                            .into(mDataBinding.ivSplash);

                    LogUtils.d("请求响应的图片为空");
                }
            });
        }


        fragments.add(K2NavigationFragment.create());
        fragments.add(K2NavigationFragment.create());
        fragments.add(K2NavigationFragment.create());
        fragments.add(K2NavigationFragment.create());

        mDataBinding.enb.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .smoothScroll(false)  //点击Tab  Viewpager切换是否有动画
                .canScroll(false)    //Viewpager能否左右滑动
                .hasPadding(true)    //false=ViewPager布局和导航栏有重叠

//                .mode(EasyNavigationBar.NavigationMode.MODE_ADD)
                .setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {
                        if (position == 4) {
                            Toast.makeText(SplashActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            //return true则拦截事件、不进行页面切换
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean onTabReSelectEvent(View view, int position) {
                        return false;
                    }

                })

                .navigationBackground(ContextCompat.getColor(this, R.color.foreground))

//                .iconSize(20)     //Tab图标大小
                .tabTextSize(10)   //Tab文字大小
                .tabTextTop(3)     //Tab文字距Tab图标的距离
                .normalTextColor(ContextCompat.getColor(this, R.color.third))   //Tab未选中时字体颜色
                .selectTextColor(ContextCompat.getColor(this, R.color.main))   //Tab选中时字体颜色

                .hintPointTop(-3)

//                .hintPointLeft(-3)  //调节提示红点的位置hintPointLeft hintPointTop（看文档说明）
//                .msgPointLeft(-10)  //调节数字消息的位置msgPointLeft msgPointTop（看文档说明）
//                .hintPointSize(9)    //提示红点的大小
                .msgPointTextSize(10)  //数字消息中字体大小
                .msgPointSize(16)    //数字消息红色背景的大小
                .setMsgPointMoreRadius(16)
//                .setMsgPointMoreWidth(24)


                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() { //Tab加载完毕回调
                    @Override
                    public void onTabLoadCompleteEvent() {
                        mDataBinding.enb.setMsgPointCount(TAB_INDEX, 123);
                        mDataBinding.enb.setHintPoint(TAB_QNA, true);
                        mDataBinding.enb.selectTab(tabPosition, false);
                    }
                })

                .build();
    }

}
