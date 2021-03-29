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

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.careershe.ui.screenmatch.utils.ConvertUtils

/**
 * 网格布局的分割线(宽=高)，不绘制四周。
 *
 * @author vipyinzhiwei
 * @since  2020/5/11
 *
 * @param spanCount 最后一行,第一个item索引。
 * @param space 分割线宽度。
 */
class GridListItemDecoration(private val spanCount: Int = 2, private val space: Float = 2f) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // item position
        val count = parent.adapter?.itemCount //item count
        //列数
        val spanIndex = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
        val lastRowFirstItemPostion = count?.minus(spanCount)   //最后一行,第一个item索引
        val space = ConvertUtils.dp2px(parent.getContext(), space)

        when {
            position < spanCount -> {
                outRect.bottom = space
            }
            position < lastRowFirstItemPostion!! -> {
                outRect.top = space
                outRect.bottom = space
            }
            else -> {
                outRect.top = space
            }
        }
        when (spanIndex) {
            0 -> {
                outRect.right = space
            }
            spanCount - 1 -> {
                outRect.left = space
            }
            else -> {
                outRect.left = space
                outRect.right = space
            }
        }
    }
}