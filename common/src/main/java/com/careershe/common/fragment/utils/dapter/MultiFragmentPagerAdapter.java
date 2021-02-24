package com.careershe.common.fragment.utils.dapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 类描述：不定(多)个数的Fragment适配器。
 *
 * @author HeHongdan
 * @date 2021/1/12
 * @since v2021/1/12
 */
public class MultiFragmentPagerAdapter<E, F extends Fragment> extends FragmentStatePagerAdapter {

    /** Fragment创建器。 */
    private final FragmentCreator<E, F> mCreator;
    /** 填充每个(Fragment)的数据集合。 */
    private List<E> mDataList = null;


    /**
     * 构造方法。
     *
     * @param fm Fragment管理器。
     * @param creator Fragment创建器。
     */
    public MultiFragmentPagerAdapter(FragmentManager fm, FragmentCreator<E, F> creator) {
//        super(fm);
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mCreator = creator;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return mCreator.create(mDataList.get(i), i);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mCreator.getTitle(mDataList.get(position));
    }

    public List<E> getDataList() {
        return mDataList;
    }

    public void setDataList(List<E> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    /**
     * Fragment创建器。
     *
     * @param <E> 数据类型。
     * @param <F> Fragment类型。
     */
    public interface FragmentCreator<E, F> {
        /** 创建Fragment。 */
        F create(E data, int pos);
        /** Fragment标题。 */
        String getTitle(E data);
    }
}
