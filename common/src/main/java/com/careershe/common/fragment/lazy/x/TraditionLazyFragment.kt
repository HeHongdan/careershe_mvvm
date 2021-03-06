package com.careershe.common.fragment.lazy.x

import com.blankj.utilcode.util.LogUtils

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/1/13
 * @since v2021/1/13
 */
abstract class TraditionLazyFragment : LogFragment() {


    /** 是否执行懒加载 */
    private var isLoaded = false

    /** 当前Fragment是否对用户可见。 */
    private var isVisibleToUser = false

    /**
     * 是否调用了setUserVisibleHint方法。
     *
     * 处理show+add+hide模式下，默认可见 Fragment 不调用
     * onHiddenChanged 方法，进而不执行懒加载方法的问题。
     */
    private var isCallUserVisibleHint = false

    /**
     * 是否调用onResume。
     *
     * 因为当使用ViewPager+Fragment形式会调用该方法时，setUserVisibleHint会优先Fragment生命周期函数调用，
     * 所以这个时候就,会导致在setUserVisibleHint方法执行时就执行了懒加载，
     * 而不是在onResume方法实际调用的时候执行懒加载。所以需要这个变量
     */
    private var isCallResume = false



    override fun onResume() {
        super.onResume()
        isCallResume = true
        if (!isCallUserVisibleHint) isVisibleToUser = !isHidden
        judgeLazyInit()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        isVisibleToUser = !hidden
        judgeLazyInit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
        isVisibleToUser = false
        isCallUserVisibleHint = false
        isCallResume = false
    }

    /**
     * 检验后执行懒加载。
     */
    private fun judgeLazyInit() {
        if (!isLoaded && isVisibleToUser && isCallResume){
            lazyInit()
            LogUtils.d("懒加载")
            isLoaded = true
        }
    }



    /** 懒加载(子类加载数据、视图必须实现在个方法里)。 */
    abstract fun lazyInit()

}