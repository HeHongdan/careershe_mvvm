package com.careershe.careershe.model.main.me;


import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmFragment;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.FragmentMeBinding;

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
    }
}
