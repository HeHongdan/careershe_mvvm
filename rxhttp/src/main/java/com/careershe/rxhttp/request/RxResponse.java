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
import com.careershe.rxhttp.request.exception.ExceptionHandle;

/**
 * 描述：直接返回RxResponse(网络请求)。TODO
 *
 * @author Cuizhen
 * @date 2018/9/9
 */
public class RxResponse<Resp> {

    private final Observable<Resp> mObservable;
    private RequestListener mListener = null;
    private ResultCallback<Resp> mCallback = null;
    private RxLife mRxLife = null;

    private RxResponse(Observable<Resp> observable) {
        mObservable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <Resp> RxResponse<Resp> create(@NonNull Observable<Resp> observable) {
        return new RxResponse<>(observable);
    }

    /**
     * 添加请求生命周期的监听
     */
    public RxResponse<Resp> listener(RequestListener listener) {
        mListener = listener;
        return this;
    }

    /**
     * 用于中断请求，管理请求生命周期
     *
     * @param rxLife 详见{@link RxLife}
     */
    public RxResponse<Resp> autoLife(RxLife rxLife) {
        mRxLife = rxLife;
        return this;
    }

    /**
     * 发起请求并设置成功回调
     *
     * @return Disposable 用于中断请求，管理请求生命周期
     * 详见{@link RxLife}
     */
    public Disposable request(@NonNull ResultCallback<Resp> callback) {
        mCallback = callback;
        Disposable disposable = mObservable.subscribe(new Consumer<Resp>() {
            @Override
            public void accept(Resp resp) throws Exception {
                mCallback.onResponse(resp);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable e) throws Exception {
                ExceptionHandle handle = RxHttp.getRequestSetting().getExceptionHandle();
                if (handle == null) {
                    handle = new ExceptionHandle();
                }
                handle.handle(e);
                mCallback.onError(handle);
                if (mListener != null) {
                    mListener.onFinish();
                }
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                if (mListener != null) {
                    mListener.onFinish();
                }
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable d) throws Exception {
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

    public interface RequestListener {
        void onStart();

        void onFinish();
    }

    public interface ResultCallback<Resp> {
        void onError(ExceptionHandle handle);

        void onResponse(Resp resp);
    }
}
