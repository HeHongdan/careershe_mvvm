package com.careershe.ui.floatview;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.careershe.ui.R;

/**
 * 类描述：继承 RelativeLayout 的浮动基础视图。
 *
 * RelativeLayout
 *  ┗━━BaseFloatView
 *      ┗━━BaseContentFloatView
 *          ┗━━BaseContentView
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public abstract class BaseFloatView extends RelativeLayout
        implements Utils.OnAppStatusChangedListener {

    /** 窗口布局参数。 */
    protected WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
    /** 是否创建了。 */
    private boolean isCreated;

    /**
     * 构造方法。
     *
     * @param context 上下文。
     */
    public BaseFloatView(Context context) {
        super(context);

        setId(R.id.baseFloatView);
        if (bindLayout() != NO_ID) {
            inflate(getContext(),bindLayout(),this);
        }
        onCreateLayoutParams();
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        //设置监听器(安装、运行、前台...)
        AppUtils.registerAppStatusChangedListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        AppUtils.unregisterAppStatusChangedListener(this);
        super.onDetachedFromWindow();
    }

    /**
     * App(在前台)回调监听。
     *
     * @param activity
     */
    @Override
    public void onForeground(Activity activity) {
        setVisibility(VISIBLE);
    }

    /**
     * App(在后台)回调监听。
     *
     * @param activity
     */
    @Override
    public void onBackground(Activity activity) {
        setVisibility(GONE);
    }

    /**
     * 处理按键事件。
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity != null) {
            if (topActivity.getWindow().getDecorView().dispatchKeyEvent(event)) {
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }



    /**
     * 绑定布局。
     *
     * @return 布局资源Id。
     */
    @LayoutRes
    public abstract int bindLayout();

    /**
     * 子类初始化(填充)内容视图。
     */
    public abstract void initContentView();

    /**
     * 显示(浮动)本布局。
     */
    public void show() {
        FloatViewManager.getInstance().show(this);
    }

    /**
     * 关闭(浮动)本布局。
     */
    public void dismiss() {
        FloatViewManager.getInstance().dismiss(this);
    }

    /**
     * 创建浮动视图。
     */
    void createFloatView() {
        if (isCreated) {
            return;
        }
        isCreated = true;
        initContentView();
    }

    /**
     * 回调创建布局的参数。
     */
    @CallSuper
    protected void onCreateLayoutParams() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //悬浮窗的窗口类型
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            //这不是一个程序的窗口,它用来提供与用户交互的界面(特别是接电话的界面),这个window通常会置于所有程序window之上,但是会在状态栏之下
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        //设置为透明
        mLayoutParams.format = PixelFormat.TRANSPARENT;
        //不能获取焦点
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        try {
            //TODO
            int currentFlags = (Integer) mLayoutParams.getClass().getField("privateFlags").get(mLayoutParams);
            mLayoutParams.getClass().getField("privateFlags").set(mLayoutParams, currentFlags | 0x00000040);
        } catch (Exception ignore) {
            Log.d("【HHD】", "悬浮窗口反射出错");
        }
    }

}
