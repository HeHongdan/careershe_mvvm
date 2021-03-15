package com.careershe.careershe.model.main.me;


import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmFragment;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.FragmentMeBinding;
import com.careershe.ui.widget.DcTextViewRunNumber;

/**
 * @author devel
 */
public class MeFragment extends BaseMvvmFragment<FragmentMeBinding, MeViewModel> {

    public static MeFragment create() {
        return new MeFragment();
    }

    @Override
    protected void initViewModel() {
        LogUtils.v("执行顺序");
    }

    @Override
    protected int _onCreateView() {
        LogUtils.d("执行顺序");
        return R.layout.fragment_me;
    }

    @Override
    protected void bindViewModel() {
        LogUtils.i("执行顺序");
    }

    @Override
    public void lazyInit() {
        LogUtils.w("执行顺序");

        int c = 1234567890;
        DcTextViewRunNumber numberRunView = (DcTextViewRunNumber) mDataBinding.numberRunView;
//        numberRunView.setShowNum("221.918899");
        numberRunView.setShowNum("10.10003");
//        numberRunView.setShowNum(c, 0);//终止的数字，小数点，这里为0所以没有小数点
        numberRunView.setRunCount(25);//(最终数值/总耗时)//动画执行的次数，50次执行完
        numberRunView.startRun();

        numberRunView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberRunView.startRun();
            }
        });
    }
}
