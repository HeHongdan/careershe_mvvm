package com.careershe.rxhttp.request;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import com.careershe.rxhttp.core.RxHttp;
import com.careershe.rxhttp.core.RxLife;
import com.careershe.rxhttp.request.base.BaseResponse;
import com.careershe.rxhttp.request.exception.ApiException;
import com.careershe.rxhttp.request.exception.ExceptionHandle;

/**
 * TODO
 * <p>
 * 类描述：网络请求封装类。
 *
 * @author HeHongdan
 * @version v2020/11/22
 * @date 2020/11/22
 */
public class RxRequest<T, R extends BaseResponse<T>> {
    /** 被观察者（上游）。 */
    private final Observable<R> mObservable;
    /** 请求监听接口。 */
    private RequestListener mListener = null;
    /** 请求生命周期。 */
    private RxLife mRxLife = null;
    /** 请求回调接口。 */
    private ResultCallback<T> mCallback = null;

    /**
     * 构造方法。
     *
     * @param observable 被观察者（上游）。
     */
    private RxRequest(Observable<R> observable) {
        mObservable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 创建网络请求。
     *
     * @param observable 被观察者（上游）。
     * @param <T>
     * @param <R>
     * @return （Disposable可以取消的）请求。
     */
    public static <T, R extends BaseResponse<T>> RxRequest<T, R> create(
            @NonNull Observable<R> observable) {
        return new RxRequest<>(observable);
    }

    /**
     * 添加请求监听（生命周期内）。
     *
     * @param listener 监听接口。
     * @return 请求实例。
     */
    public RxRequest<T, R> listener(RequestListener listener) {
        mListener = listener;
        return this;
    }

    /**
     * 自动生命周期。
     *
     * @param rxLife 生命周期（一般伴随组件）。
     * @return 请求实例。
     */
    public RxRequest<T, R> autoLife(RxLife rxLife) {
        mRxLife = rxLife;
        return this;
    }

    /**
     * 发起请求并设置回调。
     * <p>
     * 用于中断请求，管理请求生命周期详见{@link RxLife})。
     * </p>
     *
     * @param callback 回调接口。
     * @return （RxJava包裹可取消的）请求实例。
     */
    public Disposable request(@NonNull ResultCallback<T> callback) {
        mCallback = callback;

        //待处理的一次性事务。
        Disposable disposable = mObservable.subscribe(
                new Consumer<BaseResponse<T>>() {
                    @Override
                    public void accept(BaseResponse<T> bean) throws Exception {
                        if (!isSuccess(bean.getCode())) {
                            throw new ApiException(bean.getCode(), bean.getMsg());
                        }
                        mCallback.onSuccess(bean.getCode(), bean.getData());
                    }
                },

                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            mCallback.onFailed(apiException.getCode(), apiException.getMsg());
                        } else {
                            if (mListener != null) {
                                ExceptionHandle handle = RxHttp.getRequestSetting().getExceptionHandle();
                                if (handle == null) {
                                    handle = new ExceptionHandle();
                                }
                                handle.handle(e);
                                mListener.onError(handle);
                            }
                        }
                        if (mListener != null) {
                            mListener.onFinish();
                        }
                    }
                },

                new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mListener != null) {
                            mListener.onFinish();
                        }
                    }
                },

                new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (mListener != null) {
                            mListener.onStart();
                        }
                    }
                });
        if (mRxLife != null) {
            mRxLife.add(disposable);
        }

        return disposable;
    }

    /**
     * 通过响应码判断是否请求成功。
     *
     * @param code 响应码。
     * @return 请求是否成功。
     */
    private boolean isSuccess(int code) {
        if (code == RxHttp.getRequestSetting().getSuccessCode()) {
            return true;
        }
        int[] codes = RxHttp.getRequestSetting().getMultiSuccessCode();
        if (codes == null || codes.length == 0) {
            return false;
        }
        for (int i : codes) {
            if (code == i) {
                return true;
            }
        }
        return false;
    }


    /**
     * 请求结果回调。
     *
     * @param <E> 响应结果类型。
     */
    public interface ResultCallback<E> {
        /**
         * 请求响应成功。
         *
         * @param code 响应码。
         * @param data 响应数据。
         */
        void onSuccess(int code, E data);

        /**
         * 请求响应失败。
         *
         * @param code 响应码。
         * @param msg  响应信息。
         */
        void onFailed(int code, String msg);
    }

    /**
     * 请求回调接口。
     */
    public interface RequestListener {
        /**
         * 开始请求。
         */
        void onStart();

        /**
         * 请求出错。
         *
         * @param handle 异常处理。
         */
        void onError(ExceptionHandle handle);

        /**
         * 请求完成。
         */
        void onFinish();
    }
}
