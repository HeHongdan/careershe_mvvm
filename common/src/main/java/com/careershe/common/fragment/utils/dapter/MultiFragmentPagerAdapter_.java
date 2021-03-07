package com.careershe.common.fragment.utils.dapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/3/7
 * @since v2021/3/7
 */
public class MultiFragmentPagerAdapter_<E, F extends Fragment> extends FragmentStatePagerAdapter {

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
    public MultiFragmentPagerAdapter_(FragmentManager fm, FragmentCreator<E, F> creator) {
        this(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, creator);
    }

    public MultiFragmentPagerAdapter_(@NonNull FragmentManager fm, int behavior, FragmentCreator<E, F> creator) {
        super(fm, behavior);

        mCreator = creator;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mCreator.create(mDataList.get(position), position);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    @SuppressWarnings({"ReferenceEquality", "deprecation"})
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // fragment 为待显示的Fragment,
        // mCurrentPrimaryItem 为当前显示的Fragment
        Fragment fragment = (Fragment)object;
//        if (fragment != mCurrentPrimaryItem) {
//            if (mCurrentPrimaryItem != null) {
//                //...
//                // 当传入MyFragmentStatePagerAdapter中的为FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT时,将当前展示的Fragment设置为onStart
//                if (mBehavior == BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//                    //...
//                    mCurTransaction.setMaxLifecycle(mCurrentPrimaryItem, Lifecycle.State.STARTED);
//                } else {
//                    // 如果传入的是BEHAVIOR_SET_USER_VISIBLE_HINT,则会执行这行代码,这样可以兼容以前老的懒加载方法.
//                    mCurrentPrimaryItem.setUserVisibleHint(false);
//                }
//            }
//            fragment.setMenuVisibility(true);
//            // 当传入MyFragmentStatePagerAdapter中的为FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT时,将下面要展示的Fragment设置为onResume
//            if (mBehavior == BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//                //...
//                mCurTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
//            } else {
//                // 如果传入的是BEHAVIOR_SET_USER_VISIBLE_HINT,则会执行这行代码,这样可以兼容以前老的懒加载方法.
//                fragment.setUserVisibleHint(true);
//            }
//            mCurrentPrimaryItem = fragment;
//        }
    }


    @Override
    @Nullable
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
