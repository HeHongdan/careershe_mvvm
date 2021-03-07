package com.careershe.http.response;


import com.careershe.rxhttp.request.exception.ExceptionHandle;

/**
 * 类描述：请求结果(响应)回调接口（简化版：只有成功）。
 *
 * @author HeHongdan
 * @version v2020/11/29
 * @date 2020/11/29
 */
public abstract class ResponseOnlyS_Finish<E> implements ResponseListener<E> {

    private int code;
    private E data;

    @Override
    public void onSuccess(int code, E data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public void onFailed(int code, String msg) {}

    @Override
    public void onError(ExceptionHandle handle) {}


    @Override
    public void onFinish() {
        onFinish(this.code, this.data);
    }



    public abstract void onStart();
    /**
     * 重构后的onFinish回调。
     *
     * @param code 结果码。
     * @param data 响应数据。
     */
    public abstract void onFinish(int code, E data);

}
