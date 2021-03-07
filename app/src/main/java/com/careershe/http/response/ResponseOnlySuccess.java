package com.careershe.http.response;


/**
 * 类描述：请求结果(响应)回调接口（简化版：只有成功）。
 *
 * @author HeHongdan
 * @version v2020/11/29
 * @date 2020/11/29
 */
public abstract class ResponseOnlySuccess<E> extends ResponseOnlySuccess_F<E> {

    @Override
    public void onStart() {
    }

    @Override
    public void onFailed(int code, String msg) {

    }

    @Override
    public void onFinish() {
    }
}
