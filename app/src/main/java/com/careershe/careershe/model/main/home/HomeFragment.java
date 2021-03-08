package com.careershe.careershe.model.main.home;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmFragment;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.FragmentHomeBinding;
import com.careershe.careershe.model.main.home.banner.MyRecyclerViewAdapter;

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

    @Override
    public void lazyInit() {
        LogUtils.w("执行顺序");


        mDataBinding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.rv.setAdapter(new MyRecyclerViewAdapter(getContext()));
    }
}
