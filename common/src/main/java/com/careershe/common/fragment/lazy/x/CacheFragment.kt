package com.careershe.common.fragment.lazy.x

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils

/**
 * 类描述：缓存视图的Fragment。
 *
 * @author HeHongdan
 * @date 2021/1/13
 * @since v2021/1/13
 */

abstract class CacheFragment : Fragment() {

    /** 标记：Fragment是否创建视图{@link CacheFragment#onCreateView}。 */
    protected var mViewCreated = false
    /** 缓存视图集合。 */
    protected var mCacheViews:SparseArray<View>? = null
    /** onCreateView填充的视图。 */
    protected var rootView: View? = null
//        get() {
//            field
//            return null
//        }
//        set(value) {
//            rootView = value
//        }
    /** 布局资源的Id。 */
    protected var layoutId: Int = 0
//        get() {
//            field
//            return 0
//        }
//        set(value) {
//            layoutId = value
//        }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtils.v("Fragment 视图= "+ rootView)
        if (rootView == null) {
            layoutId = _onCreateView()
            LogUtils.v("Fragment 布局ID= "+ layoutId)
            if (layoutId > 0) {
                LogUtils.v("Fragment 布局ID(大于0)= "+ layoutId)
                rootView = inflater.inflate(layoutId, container, false)
            }
        }
        mViewCreated = true
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()

        //清除缓存的视图资源
        rootView = null
        if (mCacheViews != null) {
            mCacheViews!!.clear()
            mCacheViews = null
        }
        mViewCreated = false
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
        LogUtils.d("根视图= " + rootView)
        LogUtils.d("目标资源ID= " + id)

        return if (rootView == null) {
            LogUtils.d("目标资源ID，视图= null")
            null
        } else {
            LogUtils.d("目标资源ID，视图= " + rootView!!.findViewById<V>(id))
            rootView!!.findViewById<V>(id)
        }
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