package com.careershe.common.widget.actionbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hehongdan.statusbarcompat.StatusBarCompat;


/**
 * 类描述：适配状态栏（沉浸、背景、亮暗...）。
 *
 * @author HeHongdan
 * @date 12/5/20
 * @version v12/5/20
 */
public class StatusBarView extends View {

    public StatusBarView(Context context) {
        super(context);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = StatusBarCompat.getHeight(getContext());
        setMeasuredDimension(width, height);
    }

    public boolean isVisibility() {
        return getVisibility() == VISIBLE;
    }

    public void setVisibility(boolean visible) {
        setVisibility(visible ? VISIBLE : GONE);
    }
}
