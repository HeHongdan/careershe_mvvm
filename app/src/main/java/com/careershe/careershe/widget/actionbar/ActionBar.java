package com.careershe.careershe.widget.actionbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.careershe.careershe.R;
import com.hehongdan.actionbarex.ActionBarEx;
import com.hehongdan.actionbarex.OnActionBarChildClickListener;

/**
 * 类描述：通用的（中间图标和标题冲突时优先显示标题）。
 * FrameLayout->ConstraintLayout->【左边(图标+文本)+标题(图标+文本)+右边(图标+文本)】
 *
 * @author HeHongdan
 * @version v12/5/20
 * @date 12/5/20
 */
public final class ActionBar extends ActionBarEx {

    /** 左边图标 */
    private ImageView leftIconView;
    /** 左边文本 */
    private TextView leftTextView;
    /** 标题（中间）文本 */
    private TextView titleTextView;
    /** 标题（中间）图标 */
    private ImageView titleIconView;
    /** 右边图标 */
    private TextView rightTextView;
    /** 右边图标 */
    private ImageView rightIconView;


    /** 左边图标（内）边距 */
    private int leftIconPaddingLeft;
    /** 左边图标（内）边距 */
    private int leftIconPaddingTop;
    /** 左边图标（内）边距 */
    private int leftIconPaddingRight;
    /** 左边图标（内）边距 */
    private int leftIconPaddingBottom;
    /** 左边图标（外）边距 */
    private int leftIconMarginLeft;
    /** 左边图标（外）边距 */
    private int leftIconMarginTop;
    /** 左边图标（外）边距 */
    private int leftIconMarginRight;
    /** 左边图标（外）边距 */
    private int leftIconMarginBottom;
    /** 左边图标资源ID。 */
    private int leftIconRes;
    /** 左边图标颜色。 */
    private int leftIconColor;
    /** 左边图标点击返回 */
    private boolean leftIconClickToFinish;

    /** 左边文本点击返回。 */
    private boolean leftTextClickToFinish;
    /** 左边文本左边（内）距 */
    private int leftTextPaddingLeft;
    private int leftTextPaddingTop;
    /** 左边文本右边（内）距 */
    private int leftTextPaddingRight;
    private int leftTextPaddingBottom;
    /** 左边图标（外）边距 */
    private int leftTextMarginLeft;
    /** 左边图标（外）边距 */
    private int leftTextMarginRight;
    /** 左边图标（外）边距 */
    private int leftTextMarginTop;
    /** 左边图标（外）边距 */
    private int leftTextMarginBottom;
    /** 左边文本内容 */
    private String leftText;
    /** 左边文本大小 */
    private float leftTextSize;
    /** 左边文本颜色 */
    private int leftTextColor;

    /** 左边图标资源ID */
    private int titleIconRes;
    /** 左边图标颜色 */
    private int titleIconColor;
    /** 标题图标（内）边距 */
    private int titleIconPaddingLeft;
    private int titleIconPaddingTop;
    private int titleIconPaddingRight;
    private int titleIconPaddingBottom;
    /** 标题图标（外）边距 */
    private int titleIconMarginLeft;
    /** 标题图标（外）边距 */
    private int titleIconMarginTop;
    /** 右边图标（外）边距 */
    private int titleIconMarginRight;
    /** 右边图标（外）边距 */
    private int titleIconMarginBottom;

    /** 标题文本 */
    private String titleText;
    /** 标题文本字号 */
    private float titleTextSize;
    /** 标题文本颜色 */
    private int titleTextColor;
    /** 标题文本最大宽度 */
    private int titleTextMaxWidth;
    /** 标题文本（内）边距 */
    private int titleTextPaddingLeft;
    private int titleTextPaddingTop;
    private int titleTextPaddingRight;
    private int titleTextPaddingBottom;
    /** 标题文本（外）边距 */
    private int titleTextMarginLeft;
    /** 标题文本（外）边距 */
    private int titleTextMarginTop;
    /** 右边文本（外）边距 */
    private int titleTextMarginRight;
    /** 右边文本（外）边距 */
    private int titleTextMarginBottom;


    /** 右边文本内容 */
    private String rightText;
    /** 右边文本大小 */
    private float rightTextSize;
    /** 右边文本颜色 */
    private int rightTextColor;
    /** 右边文本左边（内）距 */
    private int rightTextPaddingLeft;
    /** 右边文本上面边（内）距 */
    private int rightTextPaddingTop;
    /** 右边文本右边（内）距 */
    private int rightTextPaddingRight;
    /** 右边文本右边（内）距 */
    private int rightTextPaddingBottom;
    /** 右边文本（外）边距 */
    private int rightTextMarginLeft;
    /** 右边文本（外）边距 */
    private int rightTextMarginTop;
    /** 右边文本（外）边距 */
    private int rightTextMarginRight;
    /** 右边文本（外）边距 */
    private int rightTextMarginBottom;

    /** 右边图标资源ID */
    private int rightIconRes;
    /** 右边图标颜色 */
    private int rightIconColor;
    /** 右边图标（内）边距 */
    private int rightIconPaddingLeft;
    /** 右边图标（内）边距 */
    private int rightIconPaddingTop;
    /** 右边图标（内）边距 */
    private int rightIconPaddingRight;
    /** 右边图标（内）边距 */
    private int rightIconPaddingBottom;
    /** 右边图标（外）边距 */
    private int rightIconMarginLeft;
    /** 右边图标（外）边距 */
    private int rightIconMarginTop;
    /** 右边图标（外）边距 */
    private int rightIconMarginRight;
    /** 右边图标（外）边距 */
    private int rightIconMarginBottom;


    public ActionBar(Context context) {
        this(context, null);
    }

    public ActionBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 初始化各个控件的属性。
     *
     * @param attrs 属性集合。
     */
    @Override
    protected void initAttrs(AttributeSet attrs) {
        super.initAttrs(attrs);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ActionBar);

        //定义默认值
        int paddingLeftDef = (int) getContext().getResources().getDimension(R.dimen.actionbar_padding_left_def);
        int paddingRightDef = (int) getContext().getResources().getDimension(R.dimen.actionbar_padding_right_def);


        int iconColorDef = ContextCompat.getColor(getContext(), R.color.actionbar_icon_color_def);
        int textColorDef = ContextCompat.getColor(getContext(), R.color.actionbar_text_color_def);
        int titleTextColorDef = ContextCompat.getColor(getContext(), R.color.actionbar_title_text_color_def);
        float textSizeDef = getContext().getResources().getDimension(R.dimen.actionbar_text_size);
        float titleTextSizeDef = getContext().getResources().getDimension(R.dimen.actionbar_title_text_size_def);
        int titleTextMaxWidthDef = (int) getContext().getResources().getDimension(R.dimen.actionbar_title_text_max_width_def);


        //左边图标
        leftIconRes = typedArray.getResourceId(R.styleable.ActionBar_leftIconRes, 0);
        leftIconColor = typedArray.getColor(R.styleable.ActionBar_leftIconColor, iconColorDef);
        leftIconClickToFinish = typedArray.getBoolean(R.styleable.ActionBar_leftIconClickToFinish, false);

        leftIconMarginLeft = typedArray.getInt(R.styleable.ActionBar_leftIconMarginLeft, 0);
        leftIconMarginTop = typedArray.getInt(R.styleable.ActionBar_leftIconMarginTop, 0);
        leftIconMarginRight = typedArray.getInt(R.styleable.ActionBar_leftIconMarginRight, 0);
        leftIconMarginBottom = typedArray.getInt(R.styleable.ActionBar_leftIconMarginBottom, 0);

        leftIconPaddingLeft = typedArray.getInt(R.styleable.ActionBar_leftIconPaddingLeft, paddingLeftDef);
        leftIconPaddingTop = typedArray.getInt(R.styleable.ActionBar_leftIconPaddingTop, 0);
        leftIconPaddingRight = typedArray.getInt(R.styleable.ActionBar_leftIconPaddingRight, paddingRightDef);
        leftIconPaddingBottom = typedArray.getInt(R.styleable.ActionBar_leftIconPaddingBottom, 0);


        //左边文本
        leftText = typedArray.getString(R.styleable.ActionBar_leftText);
        leftTextSize = typedArray.getDimension(R.styleable.ActionBar_leftTextSize, textSizeDef);
        leftTextColor = typedArray.getColor(R.styleable.ActionBar_leftTextColor, textColorDef);
        leftTextClickToFinish = typedArray.getBoolean(R.styleable.ActionBar_leftTextClickToFinish, false);

        leftTextMarginLeft = typedArray.getInt(R.styleable.ActionBar_leftTextMarginLeft, 0);
        leftTextMarginTop = typedArray.getInt(R.styleable.ActionBar_leftTextMarginTop, 0);
        leftTextMarginRight = typedArray.getInt(R.styleable.ActionBar_leftTextMarginRight, 0);
        leftTextMarginBottom = typedArray.getInt(R.styleable.ActionBar_leftTextMarginBottom, 0);

        leftTextPaddingLeft = typedArray.getInt(R.styleable.ActionBar_leftTextPaddingLeft, paddingLeftDef);
        leftTextPaddingTop = typedArray.getInt(R.styleable.ActionBar_leftTextPaddingTop, 0);
        leftTextPaddingRight = typedArray.getInt(R.styleable.ActionBar_leftTextPaddingRight, paddingRightDef);
        leftTextPaddingBottom = typedArray.getInt(R.styleable.ActionBar_leftTextPaddingBottom, 0);


        //中间(标题)图标
        titleIconRes = typedArray.getResourceId(R.styleable.ActionBar_titleIconRes, -1);
        titleIconColor = typedArray.getColor(R.styleable.ActionBar_titleIconColor, iconColorDef);

        titleIconMarginLeft = typedArray.getInt(R.styleable.ActionBar_titleIconMarginLeft, 0);
        titleIconMarginTop = typedArray.getInt(R.styleable.ActionBar_titleIconMarginTop, 0);
        titleIconMarginRight = typedArray.getInt(R.styleable.ActionBar_titleIconMarginRight, 0);
        titleIconMarginBottom = typedArray.getInt(R.styleable.ActionBar_titleIconMarginBottom, 0);

        titleIconPaddingLeft = typedArray.getInt(R.styleable.ActionBar_titleIconPaddingLeft, paddingLeftDef);
        titleIconPaddingTop = typedArray.getInt(R.styleable.ActionBar_titleIconPaddingTop, 0);
        titleIconPaddingRight = typedArray.getInt(R.styleable.ActionBar_titleIconPaddingRight, paddingRightDef);
        titleIconPaddingBottom = typedArray.getInt(R.styleable.ActionBar_titleIconPaddingBottom, 0);


        //中间(标题)文本
        titleText = typedArray.getString(R.styleable.ActionBar_titleText);
        titleTextColor = typedArray.getColor(R.styleable.ActionBar_titleTextColor, titleTextColorDef);
        titleTextSize = typedArray.getDimension(R.styleable.ActionBar_titleTextSize, titleTextSizeDef);
        titleTextMaxWidth = (int) typedArray.getDimension(R.styleable.ActionBar_titleTextMaxWidth, titleTextMaxWidthDef);

        titleTextMarginLeft = typedArray.getInt(R.styleable.ActionBar_titleTextMarginLeft, 0);
        titleTextMarginTop = typedArray.getInt(R.styleable.ActionBar_titleTextMarginTop, 0);
        titleTextMarginRight = typedArray.getInt(R.styleable.ActionBar_titleTextMarginRight, 0);
        titleTextMarginBottom = typedArray.getInt(R.styleable.ActionBar_titleTextMarginBottom, 0);

        titleTextPaddingLeft = typedArray.getInt(R.styleable.ActionBar_titleTextPaddingLeft, paddingLeftDef);
        titleTextPaddingTop = typedArray.getInt(R.styleable.ActionBar_titleTextPaddingTop, 0);
        titleTextPaddingRight = typedArray.getInt(R.styleable.ActionBar_titleTextPaddingRight, 0);
        titleTextPaddingBottom = typedArray.getInt(R.styleable.ActionBar_titleTextPaddingBottom, paddingRightDef);


        //右边图标
        rightIconRes = typedArray.getResourceId(R.styleable.ActionBar_rightIconRes, -1);
        rightIconColor = typedArray.getColor(R.styleable.ActionBar_rightIconColor, iconColorDef);

        rightIconMarginLeft = typedArray.getInt(R.styleable.ActionBar_rightIconMarginLeft, 0);
        rightIconMarginTop = typedArray.getInt(R.styleable.ActionBar_rightIconMarginTop, 0);
        rightIconMarginRight = typedArray.getInt(R.styleable.ActionBar_rightIconMarginRight, 0);
        rightIconMarginBottom = typedArray.getInt(R.styleable.ActionBar_rightIconMarginBottom, 0);

        rightIconPaddingLeft = typedArray.getInt(R.styleable.ActionBar_rightIconPaddingLeft, paddingLeftDef);
        rightIconPaddingTop = typedArray.getInt(R.styleable.ActionBar_rightIconPaddingTop, 0);
        rightIconPaddingRight = typedArray.getInt(R.styleable.ActionBar_rightIconPaddingRight, paddingRightDef);
        rightIconPaddingBottom = typedArray.getInt(R.styleable.ActionBar_rightIconPaddingBottom, 0);


        //右边文本
        rightText = typedArray.getString(R.styleable.ActionBar_rightText);
        rightTextSize = typedArray.getDimension(R.styleable.ActionBar_rightTextSize, textSizeDef);
        rightTextColor = typedArray.getColor(R.styleable.ActionBar_rightTextColor, textColorDef);

        rightTextMarginLeft = typedArray.getInt(R.styleable.ActionBar_rightTextMarginLeft, 0);
        rightTextMarginTop = typedArray.getInt(R.styleable.ActionBar_rightTextMarginTop, 0);
        rightTextMarginRight = typedArray.getInt(R.styleable.ActionBar_rightTextMarginRight, 0);
        rightTextMarginBottom = typedArray.getInt(R.styleable.ActionBar_rightTextMarginBottom, 0);

        rightTextPaddingLeft = typedArray.getInt(R.styleable.ActionBar_rightTextPaddingLeft, paddingLeftDef);
        rightTextPaddingTop = typedArray.getInt(R.styleable.ActionBar_rightTextPaddingTop, 0);
        rightTextPaddingRight = typedArray.getInt(R.styleable.ActionBar_rightTextPaddingRight, paddingRightDef);
        rightTextPaddingBottom = typedArray.getInt(R.styleable.ActionBar_rightTextPaddingBottom, 0);


        typedArray.recycle();
    }


    @Override
    protected View inflateTitleBar() {
        ConstraintLayout titleBarChild = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.widget_actionbar, getTitleBar(), false);
        leftIconView = titleBarChild.findViewById(R.id.iv_left);
        leftTextView = titleBarChild.findViewById(R.id.tv_left);
        titleTextView = titleBarChild.findViewById(R.id.tv_title);
        titleIconView = titleBarChild.findViewById(R.id.iv_title);
        rightTextView = titleBarChild.findViewById(R.id.tv_right);
        rightIconView = titleBarChild.findViewById(R.id.iv_right);

        //左边图标
        ConstraintLayout.LayoutParams leftIconViewParams = (ConstraintLayout.LayoutParams) leftIconView.getLayoutParams();
        leftIconViewParams.leftMargin = leftIconMarginLeft;
        leftIconViewParams.topMargin = leftIconMarginTop;
        leftIconViewParams.rightMargin = leftIconMarginRight;
        leftIconViewParams.bottomMargin = leftIconMarginBottom;
        leftIconView.setLayoutParams(leftIconViewParams);
        if (leftIconRes > 0) {
            leftIconView.setVisibility(VISIBLE);
            leftIconView.setPadding(leftIconPaddingLeft, leftIconPaddingTop, leftIconPaddingRight, leftIconPaddingBottom);
            leftIconView.setImageResource(leftIconRes);
            leftIconView.setColorFilter(leftIconColor);
            if (leftIconClickToFinish) {
                leftIconView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
        } else {
            leftIconView.setVisibility(GONE);
        }

        //左边文本
        ConstraintLayout.LayoutParams leftTextViewParams = (ConstraintLayout.LayoutParams) leftTextView.getLayoutParams();
        leftTextViewParams.leftMargin = leftTextMarginLeft;
        leftTextViewParams.topMargin = leftTextMarginTop;
        leftTextViewParams.rightMargin = leftTextMarginRight;
        leftTextViewParams.bottomMargin = leftTextMarginBottom;
        leftTextView.setLayoutParams(leftTextViewParams);
        if (!TextUtils.isEmpty(leftText)) {
            leftTextView.setVisibility(VISIBLE);
            leftIconView.setVisibility(INVISIBLE);
            leftTextView.setText(leftText);
            leftTextView.setTextColor(leftTextColor);
            leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
            leftTextView.setPadding(leftTextPaddingLeft, leftTextPaddingTop, leftTextPaddingRight, leftTextPaddingBottom);
            if (leftTextClickToFinish) {
                leftTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
        } else {
            leftTextView.setVisibility(GONE);
        }

        //中间(标题)图标
        ConstraintLayout.LayoutParams titleIconViewParams = (ConstraintLayout.LayoutParams) titleIconView.getLayoutParams();
        titleIconViewParams.leftMargin = titleIconMarginLeft;
        titleIconViewParams.topMargin = titleIconMarginTop;
        titleIconViewParams.rightMargin = titleIconMarginRight;
        titleIconViewParams.bottomMargin = titleIconMarginBottom;
        titleIconView.setLayoutParams(titleIconViewParams);
        if (TextUtils.isEmpty(titleText) && titleIconRes > 0) {
            //LogUtils.w("图标信息，资源ID= "+titleIconRes+"，颜色ID= "+titleIconColor+"，宽度= "+titleIconView.getWidth()+"，高度= "+titleIconView.getHeight());
            titleIconView.setVisibility(VISIBLE);
            titleTextView.setVisibility(INVISIBLE);
            titleIconView.setPadding(titleIconPaddingLeft, titleIconPaddingTop, titleIconPaddingRight, titleIconPaddingBottom);
            titleIconView.setImageResource(titleIconRes);
            if (titleIconColor > 0) {
                titleIconView.setColorFilter(titleIconColor);
            }
        } else {
            titleIconView.setVisibility(GONE);
        }

        //中间(标题)文本
        ConstraintLayout.LayoutParams titleTextViewParams = (ConstraintLayout.LayoutParams) titleTextView.getLayoutParams();
        titleTextViewParams.leftMargin = titleTextMarginLeft;
        titleTextViewParams.topMargin = titleTextMarginTop;
        titleTextViewParams.rightMargin = titleTextMarginRight;
        titleTextViewParams.bottomMargin = titleTextMarginBottom;
        titleTextView.setLayoutParams(titleTextViewParams);
        if (!TextUtils.isEmpty(titleText)) {
            titleIconView.setVisibility(INVISIBLE);
            titleTextView.setVisibility(VISIBLE);
            titleIconView.setPadding(titleTextPaddingLeft, titleTextPaddingTop, titleTextPaddingRight, titleTextPaddingBottom);
            titleTextView.setText(titleText);
            titleTextView.setTextColor(titleTextColor);
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
            titleTextView.setMaxWidth(titleTextMaxWidth);
        } else {
            titleTextView.setVisibility(GONE);
        }

        //右边图标
        ConstraintLayout.LayoutParams rightIconViewParams = (ConstraintLayout.LayoutParams) rightIconView.getLayoutParams();
        rightIconViewParams.rightMargin = rightIconMarginLeft;
        rightIconViewParams.topMargin = rightIconMarginTop;
        rightIconViewParams.rightMargin = rightIconMarginRight;
        rightIconViewParams.bottomMargin = rightIconMarginBottom;
        rightIconView.setLayoutParams(rightIconViewParams);
        if (rightIconRes > 0) {
            rightIconView.setVisibility(VISIBLE);
            rightIconView.setPadding(rightIconPaddingLeft, rightIconPaddingTop, rightIconPaddingRight, rightIconPaddingBottom);
            rightIconView.setImageResource(rightIconRes);
            rightIconView.setColorFilter(rightIconColor);

        } else {
            rightIconView.setVisibility(GONE);
        }

        //右边文本
        ConstraintLayout.LayoutParams rightTextViewParams = (ConstraintLayout.LayoutParams) rightTextView.getLayoutParams();
        rightTextViewParams.rightMargin = rightTextMarginLeft;
        rightTextViewParams.topMargin = rightTextMarginTop;
        rightTextViewParams.rightMargin = rightTextMarginRight;
        rightTextViewParams.bottomMargin = rightTextMarginBottom;
        rightTextView.setLayoutParams(rightTextViewParams);
        if (!TextUtils.isEmpty(rightText)) {
            rightTextView.setVisibility(VISIBLE);
            rightIconView.setVisibility(INVISIBLE);
            rightTextView.setText(rightText);
            rightTextView.setTextColor(rightTextColor);
            rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
            rightTextView.setPadding(rightTextPaddingLeft, rightTextPaddingTop, rightTextPaddingRight, rightTextPaddingBottom);
        } else {
            rightTextView.setVisibility(GONE);
        }

        return titleBarChild;
    }


    public ImageView getLeftIconView() {
        return leftIconView;
    }

    public TextView getLeftTextView() {
        return leftTextView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public ImageView getTitleIconView() {
        return titleIconView;
    }

    public TextView getRightTextView() {
        return rightTextView;
    }

    public ImageView getRightIconView() {
        return rightIconView;
    }


    /**
     * 左边图标点击监听器。
     *
     * @param onLeftIconClickListener 监听器（接口）。
     */
    public void setOnLeftIconClickListener(final OnActionBarChildClickListener onLeftIconClickListener) {
        leftIconView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftIconClickListener != null) {
                    onLeftIconClickListener.onClick(v);
                }
            }
        });
    }

    /**
     * 左边文本点击监听器。
     *
     * @param onLeftTextClickListener 监听器（接口）。
     */
    public void setOnLeftTextClickListener(final OnActionBarChildClickListener onLeftTextClickListener) {
        leftTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftTextClickListener != null) {
                    onLeftTextClickListener.onClick(v);
                }
            }
        });
    }

    /**
     * 右边文本点击监听器。
     *
     * @param onRightTextClickListener 监听器（接口）。
     */
    public void setOnRightTextClickListener(final OnActionBarChildClickListener onRightTextClickListener) {
        rightTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightTextClickListener != null) {
                    onRightTextClickListener.onClick(v);
                }
            }
        });
    }

    /**
     * 右边图标点击监听器。
     *
     * @param onRightIconClickListener 监听器（接口）。
     */
    public void setOnRightIconClickListener(final OnActionBarChildClickListener onRightIconClickListener) {
        rightIconView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightIconClickListener != null) {
                    onRightIconClickListener.onClick(v);
                }
            }
        });
    }
}
