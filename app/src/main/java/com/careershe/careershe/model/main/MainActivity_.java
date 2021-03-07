package com.careershe.careershe.model.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.careershe.basics.base.BaseActivity;
import com.careershe.careershe.R;
import com.careershe.careershe.model.K2NavigationFragment;
import com.careershe.careershe.model.KnowledgeNavigationFragment;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;
@Deprecated
public class MainActivity_ extends BaseActivity {

    /** 底部导航栏。 */
    private EasyNavigationBar navigationBar;

    /** 底部导航栏按钮文本。 */
    private String[] tabText = {"首页", "问答", "任务", "我的"};
    /** 底部导航栏按钮（未选）图标。 */
    private int[] normalIcon = {R.drawable.ic_index, R.drawable.ic_qna, R.drawable.ic_task, R.drawable.ic_me};
    /** 底部导航栏按钮（选中）图标。 */
    private int[] selectIcon = {R.drawable.ic_index_select, R.drawable.ic_qna_select, R.drawable.ic_task_select, R.drawable.ic_me_select};
    /** 底部导航栏对应Fragment。 */
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        navigationBar = findViewById(R.id.navigationBar);
        fragments.add(KnowledgeNavigationFragment.create());
        fragments.add(K2NavigationFragment.create());
        fragments.add(KnowledgeNavigationFragment.create());
        fragments.add(KnowledgeNavigationFragment.create());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .smoothScroll(false)  //点击Tab  Viewpager切换是否有动画
                .canScroll(true)    //Viewpager能否左右滑动
                .hasPadding(false)    //true ViewPager布局在导航栏之上 false有重叠

//                .mode(EasyNavigationBar.NavigationMode.MODE_ADD)
                .setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {
                        if (position == 3) {
                            Toast.makeText(MainActivity_.this, "请先登录", Toast.LENGTH_SHORT).show();
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

                .navigationHeight(50)  //导航栏高度
                .lineHeight(1)         //分割线高度  默认1px
                .lineColor(ContextCompat.getColor(this, R.color.main))
                .navigationBackground(ContextCompat.getColor(this, R.color.background_navigationbar_alpha))   //导航栏背景色

                .iconSize(20)     //Tab图标大小
                .tabTextSize(10)   //Tab文字大小
                .tabTextTop(3)     //Tab文字距Tab图标的距离
                .normalTextColor(ContextCompat.getColor(this, R.color.third))   //Tab未选中时字体颜色
                .selectTextColor(ContextCompat.getColor(this, R.color.main))   //Tab选中时字体颜色

                .hintPointLeft(-3)  //调节提示红点的位置hintPointLeft hintPointTop（看文档说明）
                .hintPointTop(-3)
                .hintPointSize(9)    //提示红点的大小
                .msgPointLeft(-10)  //调节数字消息的位置msgPointLeft msgPointTop（看文档说明）
                .msgPointTop(-10)
                .msgPointTextSize(11)  //数字消息中字体大小
                .msgPointSize(16)    //数字消息红色背景的大小
                .setMsgPointMoreWidth(24)  //消息99+宽度
                .setMsgPointColor(ContextCompat.getColor(this, R.color.accent)) //数字消息、红点背景颜色

                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() { //Tab加载完毕回调
                    @Override
                    public void onTabLoadCompleteEvent() {
                        navigationBar.setMsgPointCount(0, 1);
                        navigationBar.setMsgPointCount(1, 99);
                        navigationBar.setMsgPointCount(2, 123);
                        navigationBar.setHintPoint(3, true);
                    }
                })

                .build();

        //跳转第2页
//        navigationBar.selectTab(1, true);
    }

}
