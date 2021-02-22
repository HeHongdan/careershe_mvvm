package com.careershe.common.lazyfragment.x

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

/**
 * 类描述：缓存视图的Fragment。
 *
 * @author HeHongdan
 * @date 2021/1/13
 * @since v2021/1/13
 */

abstract class CacheFragment : Fragment() {

    /** 根视图。 */
    protected var mRootView: View? = null
    /** 标记：Fragment是否创建视图{@link CacheFragment#onCreateView}。 */
    protected var mViewCreated = false
    /** 缓存视图集合。 */
    protected var mCacheViews:SparseArray<View>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            val layoutId = _onCreateView()
            if (layoutId > 0) {
                mRootView = inflater.inflate(_onCreateView(), container, false)
            }
        }
        mViewCreated = true
        return mRootView
    }

    override fun onDestroyView() {
        super.onDestroyView()

        //清除缓存的视图资源
        mRootView = null
        if (mCacheViews != null) {
            mCacheViews!!.clear()
            mCacheViews = null
        }
        mViewCreated = false
    }


    /**
     * 获取Fragment的根布局。
     *
     * @return 根布局。
     */
    open fun getRootView(): View? {
        return mRootView
    }


    /**
     * 缓存中获取指定视图(根据资源的Id)。
     *
     * @param id 资源Id。
     * @param <V> 继承于{@link View}的泛型。
     * @return 视图。
     */
    open fun <V : View?> getView(@IdRes id: Int): V? {
        if (mCacheViews == null) {
            mCacheViews = SparseArray()
        }
        var view = mCacheViews!![id]
        if (view == null) {
            view = findViewById(id)
            if (view != null) {
                mCacheViews!!.put(id, view)
            }
        }
        return view as V
    }


    /**
     * 查找指定视图(根据资源的Id)。
     *
     * @param id 资源Id。
     * @param <V> 继承于{@link View}的泛型。
     * @return 视图。
     */
    fun <V : View?> findViewById(@IdRes id: Int): V? {
        return if (mRootView == null) {
            null
        } else mRootView!!.findViewById<V>(id)
    }

    /**
     * 把设置视图的资源，延迟到子类实现。
     * <p>
     *     重载onCreateView，绑定butterknife。
     *     子类必须实现。
     * </p>
     *
     * @return 视图资源的Id。
     */
    protected abstract fun _onCreateView(): Int

}