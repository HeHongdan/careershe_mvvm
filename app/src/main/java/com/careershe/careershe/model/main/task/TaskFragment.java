package com.careershe.careershe.model.main.task;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmFragment;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.FragmentTaskBinding;
import com.careershe.careershe.model.main.task.decoration.GridSpaceItemDecoration;
import com.careershe.careershe.widget.actionbar.ActionBar;
import com.careershe.ui.screenmatch.utils.ConvertUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CuiZhen
 * @date 2019/5/19
 * GitHub: https://github.com/goweii
 */
public class TaskFragment extends BaseMvvmFragment<FragmentTaskBinding, TaskViewModel> {

    public static TaskFragment create() {
        return new TaskFragment();
    }


    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
    }

    @Override
    protected int _onCreateView() {
        return R.layout.fragment_task;
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    public void lazyInit() {
        LogUtils.e("懒加载");

    }



}
