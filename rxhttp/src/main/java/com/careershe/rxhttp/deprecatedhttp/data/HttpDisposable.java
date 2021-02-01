package com.careershe.rxhttp.deprecatedhttp.data;

import io.reactivex.observers.DisposableObserver;


/**
 * 类描述：返回数据(RxJava)。
 *
 * @author HHD
 * @version v2021/2/2
 * @since 2021/2/2
 */
public abstract class HttpDisposable<T> extends DisposableObserver<T> {

    public HttpDisposable() {
    }

    @Override
    protected void onStart() {
    }

    @Override
    public void onNext(T value) {
        success(value);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public abstract void success(T t);
}
