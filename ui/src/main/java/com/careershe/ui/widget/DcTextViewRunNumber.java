package com.careershe.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.LogUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/3/16
 * @since v2021/3/16
 *
 * <p>
 *     使用方法
 *     <cn.up.com.textviewrun.DcTextViewRunNumber
 *         android:id="@+id/numberRunView"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:layout_marginTop="20dp"
 *         android:text="123"
 *         android:textSize="30sp" />
 *
 * numberRunView = (DcTextViewRunNumber) findViewById(R.id.numberRunView);
 * numberRunView.setShowNum("711", 0);//终止的数字，小数点，这里为0所以没有小数点
 * numberRunView.setRunCount(50);//动画执行的次数，50次执行完
 * // numberRunView.setShowNum("221.918899");
 * numberRunView.startRun();
 * <p>
 * numberRunView.startRun();
 */
public class DcTextViewRunNumber extends AppCompatTextView {

    /** 延迟。 */
    public static final int DELAY = 20;
    /** 动画延迟(更新频率)。 */
    private int delayMillis = DELAY;
    /** 跑的次数。 */
    private final int RUN_COUNT = 40;
    /** 每次跑的次数。 */
    private int runCount = RUN_COUNT;

    /** 保留小数位数  默认2为。 */
    private final int DECIMALS_COUNT = 2;
    /** 保留小数位数。 */
    private int decimals = DECIMALS_COUNT;

    /** 发送跑的Msg。 */
    private final int START_RUN = 101;
    private final int STOP_RUN = 102;
    private float speed;
    /** 开始显示的数字(=speed)。 */
    private float startNum;
    /** 最后显示的数字。 */
    private float endNum;


    private boolean isAniming;



    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == START_RUN) {
                if (speed == 0) {
                    if (endNum != 0) {
                        speed = getSpeed();
                        startNum = speed;
                    } else {
                        return;
                    }
                }
                isAniming = !running();
                if (isAniming) {
                    sendEmptyMessageDelayed(START_RUN, delayMillis);
                } else {
                    speed = 0;
                    startNum = 0;
                }
            }
        }
    };



    public DcTextViewRunNumber(Context context) {
        super(context);
    }

    public DcTextViewRunNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DcTextViewRunNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 开始数字跳动动画。
     *
     * @return 动画是否结束。
     */
    @SuppressLint("SetTextI18n")
    private boolean running() {
        setText(withDEC(String.valueOf(startNum)) + "");
        startNum += speed;
        if (startNum >= endNum) {
            setText(withDEC(String.valueOf(endNum)) + "");
            return true;
        }
        return false;
    }

    /**
     * 计算速度。
     *
     * @return 速度。
     */
    private float getSpeed() {
        float speed = withDEC(String.valueOf(endNum / runCount)).floatValue();
        LogUtils.d("最后数字= " + endNum
                + "；次数= " + runCount
                + "，速度(数字/次数)= " + speed
                + "，耗费时间(次数*速度)= " + (runCount * speed / 1000.0f)
                + " 秒；间隔= " + delayMillis
        );
        return speed;
    }

    /**
     * 判断是否是非负数
     *
     * @return
     */
    private boolean isNumber(String num) {
        if ("".equals(num) || num == null)
            return false;
        Pattern pattern = Pattern.compile("^\\d+$|\\d+\\.\\d+$");
        Matcher matcher = pattern.matcher(num);
        return matcher.find();
    }

    /**
     * 取整四舍五入 保留(2位)小数。
     *
     * @param num 数字。
     * @return 四舍五入的数字。
     */
    private BigDecimal withDEC(@Nullable String num) {
        return new BigDecimal(num).setScale(decimals, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 设置显示的数字
     *
     * @param num
     */
    public void setShowNum(String num) {
        setShowNum(num, DECIMALS_COUNT);
    }

    /**
     * 设置显示的数字
     *
     * @param num
     * @param decimals 要保留的小数位
     */
    public void setShowNum(String num, int decimals) {
        if (!isNumber(num)) {
            return;
        }
        setText(num);
        setDecimals(decimals);
    }

    public void setShowNum(int num, int decimals) {
        setText(String.valueOf(num));
        setDecimals(decimals);
    }

    /**
     * 开始跑
     */
    public void startRun() {
        if (isAniming) {
            return;
        }
        if (isNumber(getText().toString())) {
            endNum = withDEC(getText().toString()).floatValue();
            mHandler.sendEmptyMessage(START_RUN);
        }
    }

    public int getDecimals() {
        return decimals;
    }

    /**
     * 设置保留的小数位     0:不保留小数
     *
     * @param decimals
     */
    public void setDecimals(int decimals) {
        if (decimals >= 0) {
            this.decimals = decimals;
        }
        setText(withDEC(getText().toString()) + "");
    }

    public int getRunCount() {
        return runCount;
    }

    /**
     * 设置动画跑的次数
     *
     * @param runCount
     */
    public void setRunCount(int runCount) {
        if (runCount <= 0) {
            return;
        }

        if (isNumber(getText().toString())){
            int count = withDEC(getText().toString()).intValue();
            if (runCount > count){
                this.runCount = count;
                LogUtils.d("修正的次数= " + count);
                return;
            }
        }

        this.runCount = runCount;
    }


    /**
     * 设置跑一次的间隔时间(更新频率)。
     *
     * @param delayMillis 间隔时间。
     */
    public void setDelayMillis(int delayMillis) {
        this.delayMillis = delayMillis;
    }
}
