package com.careershe.common.fragment.utils.dapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * 类描述：固定个数的Fragment适配器。
 *
 * @author HeHongdan
 * @date 12/6/20
 * @version v12/6/20
 */
class FixedFragmentPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var mFragments: MutableList<Fragment>? = mutableListOf()
    var mTitles: MutableList<String>? = mutableListOf()


    fun setFragmentList(fragmentList: MutableList<Fragment>?) {
        mFragments = fragmentList;
        notifyDataSetChanged()
    }

    fun setTitles(titles: MutableList<String>?) {
        mTitles = titles;
    }


    fun setFragmentList(vararg fragments: Fragment?) {
        for (fragment in fragments) {
            fragment?.let { mFragments?.add(it) }
        }
        notifyDataSetChanged()
    }

    fun setTitles(vararg titles: String?) {
        for (t in titles) {
            t?.let { mTitles?.add(it) }
        }
    }

    override fun getItem(position: Int): Fragment {
        return mFragments!![position]
    }

    override fun getCount(): Int {
        return if (mFragments == null) 0 else mFragments!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (mTitles != null && mTitles!!.size == count) {
            return mTitles!![position]
        }
        val fragment = mFragments!![position]
        if (fragment is PageTitle) {
            val pageTitle = fragment as PageTitle
            return pageTitle.pageTitle
        }
        return ""
    }

    interface PageTitle {
        val pageTitle: CharSequence?
    }
}