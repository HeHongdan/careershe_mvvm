package com.careershe.rxhttp.core;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 类描述：通过RxJava和组件生命周期管理请求的生命周期。
 *
 * @author HeHongdan
 * @version v2020/11/25
 * @date 2020/11/25
 */
public class RxLife {

    /** 一次性请求的容器。 */
    private CompositeDisposable mCompositeDisposable = null;

    /**
     * 构造方法。
     */
    private RxLife() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public static RxLife create(){
        return new RxLife();
    }

    /**
     * 销毁关联组件的所有请求。
     */
    public void destroy(){
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()){
            return;
        }
        mCompositeDisposable.dispose();
    }

    /**
     * 向请求的容器中添加一次性请求。
     *
     * @param d 一次性请求。
     */
    public void add(Disposable d) {
        if(mCompositeDisposable == null || mCompositeDisposable.isDisposed()){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

}
