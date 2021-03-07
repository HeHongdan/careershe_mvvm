package com.careershe.careershe.model.main.qna;


import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmFragment;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.FragmentQnaBinding;

/**
 * @author devel
 */
public class QnaFragment extends BaseMvvmFragment<FragmentQnaBinding, QnaViewModel> {


    public static QnaFragment create() {
        LogUtils.v("执行顺序");
        return new QnaFragment();
    }

    @Override
    protected void initViewModel() {
        LogUtils.d("执行顺序");
    }

    @Override
    protected int _onCreateView() {
        LogUtils.i("执行顺序");
        return R.layout.fragment_qna;
    }

    @Override
    protected void bindViewModel() {
        LogUtils.w("执行顺序");

    }

    @Override
    public void lazyInit() {
        TextView tv = findViewById(R.id.tv_test);
        tv.setText("你好 lazyInit");
        LogUtils.e("执行顺序");

        LogUtils.e("懒加载");
    }
}
