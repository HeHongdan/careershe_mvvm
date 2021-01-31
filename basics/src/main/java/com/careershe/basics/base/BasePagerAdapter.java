package com.careershe.basics.base;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/1/31
 * @since v2021/1/31
 */
@Deprecated
public class BasePagerAdapter<T> extends FragmentPagerAdapter {

    /** Fragment容器。 */
    protected SparseArray<Fragment> mFragmentMap = new SparseArray<>();
    /** 数据容器。 */
    protected List<T> mDataList;

    public BasePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentMap.get(position);
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }



    /**
     * 设置数据列表
     *
     * @param dataList 数据列表
     */
    public void setDataList(List<T> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    /**
     * 释放缓存的Fragment
     */
    public void release() {
        mFragmentMap.clear();
    }

}
