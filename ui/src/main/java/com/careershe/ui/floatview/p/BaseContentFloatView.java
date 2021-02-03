package com.careershe.ui.floatview.p;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

import com.blankj.swipepanel.SwipePanel;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TouchUtils;
import com.careershe.ui.R;
import com.careershe.ui.floatview.debug.config.DebugConfig;
import com.careershe.ui.floatview.BaseFloatView;
import com.careershe.ui.floatview.FloatToast;
import com.careershe.ui.floatview.helper.ShadowHelper;
import com.careershe.ui.floatview.helper.WindowHelper;
import com.careershe.ui.floatview.listener.OnRefreshListener;

import java.util.Stack;

/**
 * 类描述：浮动内容画板(容器)视图。
 * <p>
 * RelativeLayout
 * ┗━━BaseFloatView
 * ┗━━BaseContentFloatView
 * ┗━━BaseContentView
 *
 * @author HeHongdan
 * @date 2021/2/1
 * @since v2021/2/1
 */
public abstract class BaseContentFloatView<T extends BaseContentFloatView<T>> extends BaseFloatView {

    /** 旋转延迟。 */
    private static final int ROTATE_DELAY = 30;

    /** 根布局。 */
    private LinearLayout bcfRootLayout;

    /** 标题。 */
    private RelativeLayout bcfTitleRl;
    private ImageView bcfCloseIv;
    private TextView bcfTitleTv;
    private ImageView bcfAdjustIv;

    /** 画板。 */
    private SwipePanel swipePanel;
    /** 刷新监听器。 */
    private OnRefreshListener mRefreshListener;
    /** 浮窗内容视图。 */
    private BaseContentView<T> mContentView;
    /** 视图栈。 */
    private Stack<BaseContentView<T>> mViewStack = new Stack<>();
    /** 视图布局参数。 */
    private static final ViewGroup.LayoutParams PARAMS = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
    );
    private Runnable mRotateRunnable = new Runnable() {
        @Override
        public void run() {
            Drawable topDrawable = swipePanel.getTopDrawable();
            topDrawable.setLevel(topDrawable.getLevel() + 500);
            swipePanel.invalidate();
            startRotate();
        }
    };

    /**
     * 构造方法。
     *
     * @param context 上下文。
     */
    public BaseContentFloatView(Context context) {
        super(context);

        bcfRootLayout = findViewById(R.id.bcfRootLayout);
        ShadowHelper.applyFloatView(bcfRootLayout);

        initTitleBar();
        initSwipePanel();

        if (bindContentLayout() != NO_ID) {
            //noinspection unchecked
            new BaseContentView<T>(context) {
                @Override
                public int bindLayout() {
                    return BaseContentFloatView.this.bindContentLayout();
                }

                @Override
                public void onAttach() {
                }
            }.attach((T) this, true);
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        DebugConfig.saveViewY(this, mLayoutParams.y);
        DebugConfig.saveViewHeight(this, mLayoutParams.height);
        DebugConfig.saveViewAlpha(this, mLayoutParams.alpha);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onCreateLayoutParams() {
        super.onCreateLayoutParams();
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = DebugConfig.getViewHeight(BaseContentFloatView.this, WindowManager.LayoutParams.WRAP_CONTENT);
        mLayoutParams.windowAnimations = R.style.FloatAnimation;
        mLayoutParams.alpha = DebugConfig.getViewAlpha(this);
        mLayoutParams.y = DebugConfig.getViewY(this);
        post(new Runnable() {
            @Override
            public void run() {
                if (getParent() != null) {
                    wrapWindow();
                }
            }
        });
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        wrapWindow();
    }

    /**
     * 初始化切换画板。
     */
    private void initSwipePanel() {
        swipePanel = findViewById(R.id.bcfSwipePanel);
        swipePanel.setOnFullSwipeListener(new SwipePanel.OnFullSwipeListener() {
            @Override
            public void onFullSwipe(int direction) {
                if (direction == SwipePanel.LEFT) {
                    swipePanel.close(direction);
                    back();
                } else if (direction == SwipePanel.TOP) {
                    if (mRefreshListener != null) {
                        startRotate();
                        mRefreshListener.onRefresh(BaseContentFloatView.this);
                    }
                }
            }
        });
    }

    /**
     * 初始化标题栏。
     */
    private void initTitleBar() {
        bcfTitleRl = findViewById(R.id.bcfTitleRl);
        bcfCloseIv = findViewById(R.id.bcfCloseIv);
        bcfTitleTv = findViewById(R.id.bcfTitleTv);
        bcfAdjustIv = findViewById(R.id.bcfAdjustIv);

        bcfTitleTv.setText(bindTitle());

        ClickUtils.applyPressedBgDark(bcfTitleRl);
        //触发点击2次进入
        bcfTitleRl.setOnClickListener(new ClickUtils.OnMultiClickListener(2) {
            //触发点击
            @Override
            public void onTriggerClick(View v) {
                mLayoutParams.alpha = mLayoutParams.alpha == 0.5f ? 1f : 0.5f;
                WindowHelper.updateViewLayout(BaseContentFloatView.this, mLayoutParams);

                //保存窗口透明配置
                DebugConfig.saveViewAlpha(BaseContentFloatView.this, mLayoutParams.alpha);
            }

            //触发点击前
            @Override
            public void onBeforeTriggerClick(View v, int count) {
                if (count == 1) {

                }
            }
        });

        TouchUtils.setOnTouchListener(bcfTitleRl, new TouchUtils.OnTouchUtilsListener() {
            @Override
            public boolean onDown(View view, int x, int y, MotionEvent event) {
                return true;
            }

            @Override
            public boolean onMove(View view, int direction, int x, int y, int dx, int dy, int totalX, int totalY, MotionEvent event) {
                mLayoutParams.y = Math.min(Math.max(mLayoutParams.y + dy, 0), WindowHelper.getAppWindowHeight() - bcfRootLayout.getHeight());
                WindowHelper.updateViewLayout(BaseContentFloatView.this, mLayoutParams);
                return true;
            }

            @Override
            public boolean onStop(View view, int direction, int x, int y, int totalX, int totalY, int vx, int vy, MotionEvent event) {
                DebugConfig.saveViewY(BaseContentFloatView.this, mLayoutParams.y);
                return true;
            }
        });

        ClickUtils.applyPressedBgDark(bcfCloseIv, 0.8f);
        bcfCloseIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ClickUtils.applyPressedBgDark(bcfAdjustIv, 0.8f);
        bcfAdjustIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatToast.showLong(FloatToast.WARNING, StringUtils.getString(R.string.du_adjust_tips));
            }
        });

        TouchUtils.setOnTouchListener(bcfAdjustIv, new TouchUtils.OnTouchUtilsListener() {
            private int minHeight;

            @Override
            public boolean onDown(View view, int x, int y, MotionEvent event) {
                int[] locations = new int[2];
                getLocationOnScreen(locations);
                mLayoutParams.height = WindowHelper.getAppWindowHeight() - locations[1];
                WindowHelper.updateViewLayout(BaseContentFloatView.this, mLayoutParams);

                minHeight = bcfTitleRl.getHeight() + SizeUtils.dp2px(30);
                return true;
            }

            @Override
            public boolean onMove(View view, int direction, int x, int y, int dx, final int dy, int totalX, int totalY, MotionEvent event) {
                ViewGroup.LayoutParams layoutParams = bcfRootLayout.getLayoutParams();
                layoutParams.height = Math.min(Math.max(bcfRootLayout.getHeight() + dy, minHeight), mLayoutParams.height);
                bcfRootLayout.setLayoutParams(layoutParams);
                return true;
            }

            @Override
            public boolean onStop(View view, int direction, int x, int y, int totalX, int totalY, int vx, int vy, MotionEvent event) {
                mLayoutParams.height = bcfRootLayout.getHeight();
                WindowHelper.updateViewLayout(BaseContentFloatView.this, mLayoutParams);
                DebugConfig.saveViewHeight(BaseContentFloatView.this, mLayoutParams.height);
                return true;
            }
        });
    }

    /**
     * 窗口包裹(大于)浮窗。
     */
    private void wrapWindow() {
        int[] locations = new int[2];
        //当前控件相对于屏幕左上角位置
        getLocationOnScreen(locations);
        int floatViewHeight = DebugConfig.getViewHeight(BaseContentFloatView.this, bcfRootLayout.getHeight());
        if (locations[1] + floatViewHeight > WindowHelper.getAppWindowHeight()) {
            floatViewHeight = WindowHelper.getAppWindowHeight() - locations[1];
        }
        mLayoutParams.height = floatViewHeight;

        WindowHelper.updateViewLayout(BaseContentFloatView.this, mLayoutParams);

        ViewGroup.LayoutParams layoutParams = bcfRootLayout.getLayoutParams();
        layoutParams.height = mLayoutParams.height;
        bcfRootLayout.setLayoutParams(layoutParams);
    }


    private void startRotate() {
        swipePanel.postDelayed(mRotateRunnable, ROTATE_DELAY);
    }

    private void stopRotate() {
        swipePanel.removeCallbacks(mRotateRunnable);
    }

    void replace(BaseContentView<T> view, boolean isAddStack) {
        if (view == null) {
            return;
        }
        if (isAddStack) {
            mViewStack.add(view);
        }
        swipePanel.removeAllViews();
        mContentView = view;
        mContentView.onAttach();
        swipePanel.addView(mContentView, PARAMS);
    }

    /**
     * 设置刷新监听。
     *
     * @param listener
     */
    void setOnRefreshListener(OnRefreshListener listener) {
        mRefreshListener = listener;
    }

    void setRefreshEnabled(boolean enabled) {
        swipePanel.setTopEnabled(enabled);
    }

    /**
     * 返回。
     */
    public void back() {
        // 返回先关闭刷新
        swipePanel.close(SwipePanel.TOP, false);
        swipePanel.removeAllViews();
        if (mContentView == null) {
            dismiss();
            return;
        }
        mContentView.onBack();
        if (mViewStack.isEmpty()) {
            dismiss();
            return;
        }
        if (mContentView.isAddStack()) {
            mViewStack.pop();
        }
        if (mViewStack.isEmpty()) {
            dismiss();
            return;
        }
        BaseContentView<T> peek = mViewStack.peek();
        swipePanel.addView(peek, PARAMS);
        setOnRefreshListener(peek.getOnRefreshListener());
        setRefreshEnabled(peek.isRefreshEnabled());
        mContentView = peek;
    }

    public BaseContentView<T> getContentView() {
        return mContentView;
    }

    /**
     * 关闭刷新。
     */
    public void closeRefresh() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                swipePanel.close(SwipePanel.TOP);
            }
        }, 500);
    }

    public void setSwipeBackEnabled(boolean enabled) {
        swipePanel.setLeftEnabled(enabled);
    }

    public void setTitle(CharSequence title) {
        bcfTitleTv.setText(title);
    }


    /**
     * 设置标题。
     *
     * @return
     */
    @StringRes
    public abstract int bindTitle();

    /**
     * 设置(填充)内容。
     *
     * @return
     */
    @LayoutRes
    public abstract int bindContentLayout();


    /**
     * 设置布局。
     *
     * @return
     */
    @Override
    public int bindLayout() {
        return R.layout.du_base_content_float;
    }

}
