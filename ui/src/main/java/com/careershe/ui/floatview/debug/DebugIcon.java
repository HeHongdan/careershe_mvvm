package com.careershe.ui.floatview.debug;

import android.content.res.Configuration;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.TouchUtils;
import com.careershe.ui.R;
import com.careershe.ui.floatview.helper.ShadowHelper;
import com.careershe.ui.floatview.debug.config.DebugConfig;
import com.careershe.ui.floatview.helper.WindowHelper;

/**
 * 类描述：浮动圆形调试按钮。
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public class DebugIcon extends RelativeLayout {

    private static final DebugIcon INSTANCE = new DebugIcon();

    private int mIconId;

    public static DebugIcon getInstance() {
        return INSTANCE;
    }

    public static void setVisibility(boolean isShow) {
        if (INSTANCE == null) {
            return;
        }
        INSTANCE.setVisibility(isShow ? VISIBLE : GONE);
    }

    public DebugIcon() {
        super(DebugUtils.getApp());
        inflate(getContext(), R.layout.du_debug_icon, this);
        ShadowHelper.applyDebugIcon(this);
        TouchUtils.setOnTouchListener(this, new TouchUtils.OnTouchUtilsListener() {

            private int rootViewWidth;
            private int rootViewHeight;
            private int viewWidth;
            private int viewHeight;
            private int statusBarHeight;

            @Override
            public boolean onDown(View view, int x, int y, MotionEvent event) {
                viewWidth = view.getWidth();
                viewHeight = view.getHeight();
                View contentView = view.getRootView().findViewById(android.R.id.content);
                rootViewWidth = contentView.getWidth();
                rootViewHeight = contentView.getHeight();
                statusBarHeight = BarUtils.getStatusBarHeight();

                processScale(view, true);
                return true;
            }

            @Override
            public boolean onMove(View view, int direction, int x, int y, int dx, int dy, int totalX, int totalY, MotionEvent event) {
                view.setX(Math.min(Math.max(0, view.getX() + dx), rootViewWidth - viewWidth));
                view.setY(Math.min(Math.max(statusBarHeight, view.getY() + dy), rootViewHeight - viewHeight));
                return true;
            }

            @Override
            public boolean onStop(View view, int direction, int x, int y, int totalX, int totalY, int vx, int vy, MotionEvent event) {
                stick2HorizontalSide(view);
                processScale(view, false);
                return true;
            }

            private void stick2HorizontalSide(View view) {
                view.animate()
                        .setInterpolator(new DecelerateInterpolator())
                        .translationX(view.getX() + viewWidth / 2f > rootViewWidth / 2f ? rootViewWidth - viewWidth : 0)
                        .setDuration(100)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                savePosition();
                            }
                        })
                        .start();
            }

            private void processScale(final View view, boolean isDown) {
                float value = isDown ? 1 - 0.1f : 1;
                view.animate()
                        .scaleX(value)
                        .scaleY(value)
                        .setDuration(100)
                        .start();
            }
        });

        if (true) {
            //触发点击3次进入
            this.setOnClickListener(new ClickUtils.OnMultiClickListener(3) {
                //触发点击
                @Override
                public void onTriggerClick(View v) {
                    LogUtils.v("点击3次");
                }

                //触发点击前
                @Override
                public void onBeforeTriggerClick(View v, int count) {
                    LogUtils.v("点击次数= " + count);
                }
            });
        } else {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        PermissionUtils.requestDrawOverlays(new PermissionUtils.SimpleCallback() {
                            @Override
                            public void onGranted() {
                                //TODO DebugMenu.getInstance().show();
                                LogUtils.v("显示列表");
                            }

                            @Override
                            public void onDenied() {
                                LogUtils.v("请授权给浮窗");
                                ToastUtils.showLong(R.string.de_permission_tips);
                            }
                        });
                    } else {
                        //TODO DebugMenu.getInstance().show();
                        LogUtils.v("显示列表");
                    }
                }
            });
        }


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        wrapPosition();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        savePosition();
    }

    private void savePosition() {
        DebugConfig.saveViewX(this, (int) getX());
        DebugConfig.saveViewY(this, (int) getY());
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        wrapPosition();
    }

    private void wrapPosition() {
        post(new Runnable() {
            @Override
            public void run() {
                View contentView = getRootView().findViewById(android.R.id.content);
                if (contentView == null) {
                    return;
                }
                setX(DebugConfig.getViewX(DebugIcon.this));
                setY(DebugConfig.getViewY(DebugIcon.this, contentView.getHeight() / 3));
                setX(getX() + getWidth() / 2f > contentView.getWidth() / 2f ? contentView.getWidth() - getWidth() : 0);
            }
        });
    }

    public void setIconId(final int iconId) {
        ImageView debugPanelIconIv = findViewById(R.id.debugIconIv);
        debugPanelIconIv.setImageResource(mIconId);
    }

    public int getIconId() {
        return mIconId;
    }
}
