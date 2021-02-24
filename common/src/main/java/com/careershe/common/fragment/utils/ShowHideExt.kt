package com.careershe.common.fragment.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

/**
 * 类描述：(懒加载)显示隐藏的[Fragment]拓展。
 *
 * @author HeHongdan
 * @date 2021/1/17
 * @since v2021/1/17
 *
 *
 * Author:  andy.xwt
 * Date:    2020-01-14 18:35
 * Description:
 * 扩展Fragment及Activity,使用add+show+hide模式下的Fragment的懒加载
 * 当调用 Fragment.showHideFragment ，确保已经先调用 Fragment.loadFragments
 * 当调用 FragmentActivity.showHideFragment，确保已经先调用 FragmentActivity.loadFragments
 */
open class ShowHideExt() {
}

/**
 * 加载同级的[Fragment]。
 *
 * @param containerViewId 放[Fragment]布局Id。
 * @param showPosition  默认显示的角标。
 * @param fragments    加载的[Fragment]。
 */
fun Fragment.loadFragments(
        @IdRes containerViewId: Int,
        showPosition: Int = 0,
        vararg fragments: Fragment
) {
    loadFragmentsTransaction(containerViewId, showPosition, childFragmentManager, *fragments)
}

/**
 * 加载同级的[Fragment]。
 *
 * @param containerViewId 放[Fragment]布局Id。
 * @param showPosition  默认显示的角标。
 * @param fragments    加载的[Fragment]。
 */
fun FragmentActivity.loadFragments(
        @IdRes containerViewId: Int,
        showPosition: Int = 0,
        vararg fragments: Fragment
) {
    loadFragmentsTransaction(containerViewId, showPosition, supportFragmentManager, *fragments)
}

/**
 * 使用add+show+hide模式加载[Fragment]。
 *
 * 默认显示位置[showPosition]的[Fragment]，最大[Lifecycle]为[Lifecycle.State.RESUMED]；
 * 同时隐藏其他[Fragment],并设置最大[Lifecycle]为[Lifecycle.State.STARTED]。
 *
 *@param containerViewId 放[Fragment]布局Id。
 *@param showPosition  默认显示的角标。
 *@param fragmentManager Fragment管理器。
 *@param fragments  [Fragment]集合。
 */
private fun loadFragmentsTransaction(
        @IdRes containerViewId: Int,
        showPosition: Int,
        fragmentManager: FragmentManager,
        vararg fragments: Fragment
) {
    if (fragments.isNotEmpty()) {
        fragmentManager.beginTransaction().apply {
            for (index in fragments.indices) {
                val fragment = fragments[index]
                add(containerViewId, fragment, fragment.javaClass.name)
                if (showPosition == index) {
                    setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                } else {
                    hide(fragment)
                    setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                }
            }

        }.commit()
    } else {
        throw IllegalStateException(
                "fragments must not empty"
        )
    }
}




/**
 * 显示目标[showFragment]，并隐藏其他 Fragment。
 *
 * @param showFragment 需要显示的 Fragment。
 */
fun FragmentActivity.showHideFragment(showFragment: Fragment) {
    showHideFragmentTransaction(supportFragmentManager, showFragment)
}

/**
 * 显示目标[showFragment]，并隐藏其他[Fragment]。
 * @param showFragment 需要显示的[Fragment]。
 */
fun Fragment.showHideFragment(showFragment: Fragment) {
    showHideFragmentTransaction(childFragmentManager, showFragment)
}

/**
 * 显示需要显示的[showFragment]，并设置其最大[Lifecycle]为[Lifecycle.State.RESUMED]；
 * 同时隐藏其他[Fragment],并设置最大[Lifecycle]为[Lifecycle.State.STARTED]。
 *
 * @param fragmentManager [Fragment]管理器。
 * @param showFragment 需要显示的 Fragment。
 */
private fun showHideFragmentTransaction(fragmentManager: FragmentManager, showFragment: Fragment) {
    fragmentManager.beginTransaction().apply {
        show(showFragment)
        //把showFragment的生命周期限制到RESUMED
        setMaxLifecycle(showFragment, Lifecycle.State.RESUMED)

        //获取管理器中所有的fragment,隐藏其他的fragment(只显示当前的)
        val fragments = fragmentManager.fragments
        for (fragment in fragments) {
            if (fragment != showFragment) {
                hide(fragment)
                //把非showFragment的生命周期限制到STARTED
                setMaxLifecycle(fragment, Lifecycle.State.STARTED)
            }
        }
    }.commit()
}
