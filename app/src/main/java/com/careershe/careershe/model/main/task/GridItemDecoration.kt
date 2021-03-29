/*
 * Copyright (c) 2020. vipyinzhiwei <vipyinzhiwei@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.careershe.careershe.model.main.task

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.careershe.careershe.R
import com.careershe.ui.screenmatch.utils.ConvertUtils

/**
 * 网格布局的分割线(宽=高)，绘制四周。
 *
 * @author vipyinzhiwei
 * @since  2020/5/11
 *
 * @param 列总数 最后一行,第一个item索引。
 * @param space 分割线宽度。
 */
class GridItemDecoration(private val 列总数: Int = 2, private val space: Int = 2) : RecyclerView.ItemDecoration() {

//    private val mPaint: Paint? = null





    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // item下标
        val item下标 = parent.getChildAdapterPosition(view)
        // item总数
        val item总数 = parent.adapter?.itemCount

        // 分割线宽/高
        val space_ = space
        val space_2 = space.div(2)

        LogUtils.d("RV的高度= " + ConvertUtils.px2dp(parent.context, space.toFloat()))


        // 列数
        val 列数下标 = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex


        val 余数= item总数?.rem(列总数)

        // 最后一行，第一个item索引
        val lastRowFirstItemPostion = if (余数 == 0)  item总数-列总数 else item总数?.minus(余数!!)

        LogUtils.w("最底部的行"
                + "，item总数= " + item总数
                + "，列总数= " + 列总数
                + "，余数= " + 余数
                + "，相减= " + item总数?.minus(列总数)
        )


        //TODO 纵向分割线
        when(列数下标){
            //最右边的列
            列总数 - 1 -> {
                outRect.left = space_2
                outRect.right = space_
                LogUtils.v("最右边的列，下标= " + item下标)
            }
            //最左边的列
            0 -> {
                outRect.left = space_
                outRect.right = space_2
            }
            else -> {
                outRect.left = space_2
                outRect.right = space_2
            }
        }

        // TODO 横向分割线
        when {
            //最顶部的行
            item下标 < 列总数 -> {
                outRect.top = space_
                outRect.bottom = space_2
                LogUtils.v("最底部的行，下标= " + item下标)
            }
            //中间的行
            item下标 < lastRowFirstItemPostion!! -> {
                outRect.top = space_2
                outRect.bottom = space_2
                LogUtils.v("最底部的行，下标= " + item下标 + " "+ lastRowFirstItemPostion)
            }
            //最底部的行
            else -> {
                outRect.top = space_2
                outRect.bottom = space_
                LogUtils.d("最底部的行，下标= " + item下标 + " "+ lastRowFirstItemPostion)
            }
        }

    }







    companion object{
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }




    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        mPaint.setColor(ContextCompat.getColor(parent.context, R.color.transparent))
        mPaint.setStyle(Paint.Style.FILL)

        val mDividerWidth = space


        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val layoutParams =
                child.layoutParams as RecyclerView.LayoutParams

            //画水平分隔线
            var left = child.left
            var right = child.right
            var top = child.bottom + layoutParams.bottomMargin
            var bottom: Int = top + mDividerWidth
            if (mPaint != null) {
                canvas.drawRect(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    mPaint
                )
            }
            //画垂直分割线
            top = child.top
            bottom = child.bottom + mDividerWidth
            left = child.right + layoutParams.rightMargin
            right = left + mDividerWidth
            if (mPaint != null) {
                canvas.drawRect(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    mPaint
                )
            }
        }

    }


}