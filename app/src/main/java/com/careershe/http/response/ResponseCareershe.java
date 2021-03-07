package com.careershe.http.response;


import com.blankj.utilcode.util.LogUtils;
import com.careershe.rxhttp.request.exception.ExceptionHandle;

/**
 * 类描述：请求结果(响应)回调接口（简化版）。
 * <p>
 *    1.异常(出错)统一处理放到 onFailed 中处理；
 *    2.无需 onFinish 回调。
 * </p>
 *
 * @author HeHongdan
 * @version v2020/11/29
 * @date 2020/11/29
 */
public abstract class ResponseCareershe<E> implements ResponseListener<E> {

    /** 间隔时长。 */
    private final static long DELAY = 500L;
    /** 最后响应（失败）时间。 */
    private long lastTime = 0L;
    private long nowTime = 0L;

    @Override
    public void onError(ExceptionHandle handle) {
        LogUtils.v("给出网络错误(间隔)时间");
        lastTime = System.currentTimeMillis();
        onlyFirstFailed(handle);
    }

    @Override
    public void onFinish() {

    }

    /**
     * 处理短时间内多次回调失败，只响应第一次。
     *
     * @param handle 异常处理类。
     */
    protected void onlyFirstFailed(ExceptionHandle handle) {
        LogUtils.v("给出网络错误(间隔)时间");
        nowTime = System.currentTimeMillis();
        if (nowTime - lastTime > DELAY) {
            onFailed(handle.getCode(), handle.getMsg());
            lastTime = nowTime;
        }
    }

}
