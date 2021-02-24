package com.careershe.common.fragment.lazy.x

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.LogUtils


/**
 * 类描述：打印生命周期日志的Fragment基类。
 *
 *
 * @author HeHongdan
 * @date 2/19/21
 * @since v2/19/21
 *
 * Author:  andy.xwt
 * Date:    2020-01-14 18:29
 * Description: 负责打印日志的Fragment基类
 */
abstract class LogFragment : CacheFragment() {

    protected var TAG = javaClass.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.d("懒加载日志: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("懒加载日志: ")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.d("懒加载日志: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.d("懒加载日志: ")
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtils.d("懒加载日志: ")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("懒加载日志: ")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d("懒加载日志: ")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d("懒加载日志: ")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d("懒加载日志: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d("懒加载日志: ")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.d("懒加载日志: ")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        LogUtils.d("懒加载日志:隐藏= $hidden")
    }

}