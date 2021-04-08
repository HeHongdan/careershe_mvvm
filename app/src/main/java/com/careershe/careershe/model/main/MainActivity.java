package com.careershe.careershe.model.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.dialog.NumberProgressBar;
import com.azhon.appupdate.dialog.UpdateDialog;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListenerAdapter;
import com.azhon.appupdate.manager.DownloadManager;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmActivity;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.ActivityMainBinding;
import com.careershe.careershe.model.main.home.HomeFragment;
import com.careershe.careershe.model.main.me.MeFragment;
import com.careershe.careershe.model.main.task.TaskFragment;
import com.careershe.ui.widget.enb.EasyNavigationBar;
import com.careershe.careershe.model.main.qna.QnaFragment;
import com.careershe.common.fragment.utils.dapter.MultiFragmentPagerAdapter_;
import com.next.easynavigation.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/3/1
 * @since v2021/3/1
 */
public class MainActivity extends BaseMvvmActivity<ActivityMainBinding, MainViewModel> {
    /** 关键字：指定(首页、问答、任务、我的)标签。 */
    public static final String TAB_ = "TAB_";
    public static final int TAB_INDEX= 0;
    public static final int TAB_QNA= 1;
    public static final int TAB_TASK = 2;
    public static final int TAB_ME = 3;


    private CustomViewPager vp;
    private EasyNavigationBar enb;

    /** 指定的标签。 */
    private int tabPosition = 0;
    /** 底部导航栏按钮文本。 */
    private String[] tabText = {"首页", "问答", "任务", "我的"};
    /** 底部导航栏按钮（未选）图标。 */
    private int[] normalIcon = {R.drawable.ic_index, R.drawable.ic_qna, R.drawable.ic_task, R.drawable.ic_me};
    /** 底部导航栏按钮（选中）图标。 */
    private int[] selectIcon = {R.drawable.ic_index_select, R.drawable.ic_qna_select, R.drawable.ic_task_select, R.drawable.ic_me_select};

    /** 底部导航栏对应Fragment。 */
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startUpdate();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    NumberProgressBar progressBar;
    Button btn_update;
    /** 升级的下载管理器。 */
    private DownloadManager manager;
    /** 升级apk地址。 */
    private String url = "http://s.duapps.com/apks/own/ESFileExplorer-cn.apk";

    /**
     * 网络请求示例。
     */
    private void startUpdate() {
        /*
         * 整个库允许配置的内容
         * 非必选
         */
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //设置按钮的文字颜色
                .setDialogButtonTextColor(Color.WHITE)
                //设置是否显示通知栏进度
                .setShowNotification(false)
                //设置是否提示后台下载toast
                .setShowBgdToast(false)
                //设置是否上报数据
                .setUsePlatform(true)
                //设置强制更新
                .setForcedUpgrade(false)
                //设置对话框按钮的点击监听
                .setButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonClick(int id) {
                        LogUtils.d(String.valueOf(id));
                    }
                })
                //设置下载过程的监听
                .setOnDownloadListener(new OnDownloadListenerAdapter() {
                    /**
                     * 下载中
                     *
                     * @param max      总进度
                     * @param progress 当前进度
                     */
                    @Override
                    public void downloading(int max, int progress) {
                        int curr = (int) (progress / (double) max * 100.0);
                        LogUtils.d("下载的进度= " + curr);
                        if (progressBar != null) {
                            if (btn_update != null) {
                                btn_update.setVisibility(View.GONE);
                            }
                            progressBar.setVisibility(View.VISIBLE);
                            progressBar.setMax(100);
                            progressBar.setProgress(curr);
                        }
                    }
                })
                //设置自定义的下载
                //.setHttpManager()
                //设置对话框背景图片 (图片规范参照demo中的示例图)
                //.setDialogImage(R.drawable.ic_dialog)
                //设置按钮的颜色
                //.setDialogButtonColor(Color.parseColor("#E743DA"))
                //设置对话框强制更新时进度条和文字的颜色
                //.setDialogProgressBarColor(Color.parseColor("#E743DA"))
                ;

        manager = DownloadManager.getInstance(this);
        manager.setApkName("careershe.apk")
                .setApkUrl(url)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowNewerToast(false)
                .setConfiguration(configuration)
                .setApkVersionCode(AppUtils.getAppVersionCode() + 1)
                .setApkVersionName("0.1")
                .setApkSize("43.21")
                .setApkDescription("1.支持M N O P Q\\n2.支持自定义下载过程\\n3.支持动态权限\\n4.支持通知栏进度条")
//                .setApkMD5("DC501F04BBAA458C9DC33008EFED5E7F")
                .download();

        UpdateDialog defaultDialog = manager.getDefaultDialog();
        if (defaultDialog != null){
            View view = defaultDialog.getView();
            progressBar = view.findViewById(R.id.np_bar);
            progressBar.setVisibility(View.GONE);
            btn_update = view.findViewById(R.id.btn_update);
            TextView tv_size = view.findViewById(R.id.tv_size);
            tv_size.setVisibility(View.GONE);
        }
    }
















    @Override
    protected int setContentView_() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewModel() {
        //Fragment
        // userModel=new ViewModelProvider(getActivity(),
        // ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(XXXModel.class);
//        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        LogUtils.d("初始化（Activity）initViewModel");
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }





    /** 不定个数的VP适配器。 */
    private MultiFragmentPagerAdapter_<String, Fragment> mAdapter;
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

        fragments.add(HomeFragment.create());
        fragments.add(QnaFragment.create());
        fragments.add(TaskFragment.create());
        fragments.add(MeFragment.create());

        enb = mDataBinding.enb;
        enb.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
//                .mode(EasyNavigationBar.NavigationMode.MODE_ADD)
                .tabTextSize(10)
                .msgPointTextSize(10)
                .setMsgPointMoreRadius(16)

                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() { //Tab加载完毕回调
                    @Override
                    public void onTabLoadCompleteEvent() {
                        mDataBinding.enb.setMsgPointCount(TAB_INDEX, 123);
                        mDataBinding.enb.setHintPoint(TAB_QNA, true);
                        mDataBinding.enb.selectTab(tabPosition, false);
                    }
                })
                .setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {

                        LogUtils.d("未读消息文本大小= "+enb.getMsgPointLeft());

                        if (position == 4) {
                            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
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
//                .setupWithViewPager(vp)

                .build();
    }

}
