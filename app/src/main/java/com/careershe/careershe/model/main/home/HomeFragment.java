package com.careershe.careershe.model.main.home;


import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmFragment;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.FragmentHomeBinding;
import com.careershe.careershe.model.main.home.banner.MyRecyclerViewAdapter;
import com.hehongdan.actionbarex.ActionBarEx;

/**
 * 类描述：首页。
 *
 * @author HHD
 * @version v2021/3/8
 * @since 2021/3/8
 */
public class HomeFragment extends BaseMvvmFragment<FragmentHomeBinding, HomeViewModel> {

    public static HomeFragment create() {
        return new HomeFragment();
    }

    @Override
    protected void initViewModel() {
        LogUtils.v("执行顺序");
    }

    @Override
    protected int _onCreateView() {
        LogUtils.d("执行顺序");
        return R.layout.fragment_home;
    }

    @Override
    protected void bindViewModel() {
        LogUtils.i("执行顺序");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void lazyInit() {
        LogUtils.w("执行顺序");

        ImageView iv_left = mDataBinding.ab.findViewById(R.id.iv_left);

        mDataBinding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.rv.setAdapter(new MyRecyclerViewAdapter(getContext()));
        mDataBinding.rv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LogUtils.d("滑动的视图= " + v + "；" + scrollX + "，" + scrollY + "，" + oldScrollX + "，" + oldScrollY);

                if (colorInt >= 255) {
                    colorInt = 51;
                }
                colorInt++;
                int color = ColorUtils.getColor(R.color.invert);
                color = setRedComponent(color, colorInt);
                LogUtils.w("滑动的颜色= " + ColorUtils.int2RgbString(color));
                iv_left.setColorFilter(color);

                if (colorInt % 2 == 0) {
                    mDataBinding.ab.setStatusBarModeNow(ActionBarEx.StatusBarMode.LIGHT);
                    iv_left.setColorFilter(ColorUtils.getColor(R.color.foreground));
                } else {
                    mDataBinding.ab.setStatusBarModeNow(ActionBarEx.StatusBarMode.DARK);
                    iv_left.setColorFilter(ColorUtils.getColor(R.color.invert));
                }

            }
        });
    }

    public static int setRedComponent(@ColorInt int color, @IntRange(from = 0x0, to = 0xFF) int colorInt) {
        return ((((((color & 0xff00ffff) | (colorInt << 16)) & 0xffff00ff) | (colorInt << 8)) & 0xffffff00) | colorInt);
    }

    private int colorInt = 51;

}
