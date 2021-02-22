package com.careershe.common.lazyfragment.x

import com.blankj.utilcode.util.LogUtils


/**
 * 类描述：(同级的)Fragment懒加载。
 *
 * @author HeHongdan
 * @date 2021/1/12
 * @since v2021/1/12
 *
 * Author:  andy.xwt
 * Date:    2020-01-14 14:44
 * Description: Androidx 下支持栏加载的fragment
 * https://github.com/AndyJennifer/AndroidxLazyLoad
 */
abstract class LazyFragment : CacheFragment() {

    /** 是否已经加载过数据。 */
    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            /**
             * 懒加载(子类加载数据、视图必须实现在个方法里)。
             */
            lazyInit()
            LogUtils.d("懒加载(onDestroyView前只执行1次)")
            isLoaded = true
        }
    }

    /**
     * 懒加载(子类加载数据、视图必须实现在个方法里)。
     */
    abstract fun lazyInit()

    override fun onDestroyView() {
        super.onDestroyView()

        //还原回还没加载过
        isLoaded = false
    }



}