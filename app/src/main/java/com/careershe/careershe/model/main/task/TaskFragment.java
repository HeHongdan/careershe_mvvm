package com.careershe.careershe.model.main.task;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.basics.base.BaseMvvmFragment;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.FragmentTaskBinding;
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

//    @BindView(R.id.ab)
    ActionBar ab;

    private SchoolAdapter adapter = new SchoolAdapter();

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

        final int spanCount = 3;

        adapter.setList(genData());
        GridLayoutManager manager = new GridLayoutManager(getContext(), spanCount);

        if (true) {
            mDataBinding.rv.addItemDecoration(new GridItemDecoration(spanCount, ConvertUtils.dp2px(getContext(),12)));
        } else {
            mDataBinding.rv.addItemDecoration(new GridDividerItemDecoration(getContext(),
                    ConvertUtils.dp2px(getContext(), 12f), ConvertUtils.dp2px(getContext(), 12f),
                    true, true,
                    ContextCompat.getColor(getContext(), R.color.main)));
        }
        mDataBinding.rv.setLayoutManager(manager);
        mDataBinding.rv.setAdapter(adapter);
    }


    private List<SchoolBean> genData() {
        ArrayList<SchoolBean> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String name = "学校 " + i;
            SchoolBean school = new SchoolBean(name);
            list.add(school);
        }
        return list;
    }
}
