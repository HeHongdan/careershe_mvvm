package com.careershe.http;

import androidx.annotation.NonNull;


import com.careershe.http.cache.CacheListener;
import com.careershe.http.cache.CareersheCache;
import com.careershe.rxhttp.core.RxLife;
import com.careershe.rxhttp.request.RxRequest;
import com.careershe.rxhttp.request.exception.ExceptionHandle;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 类描述：请求网络抽取的基类。
 *
 * @author HeHongdan
 * @version v2020/11/29
 * @date 2020/11/29
 */
public class BaseRequest {

    /**
     * 直接请求缓存（不存在则失败）-单个对象。
     *
     * @param key 缓存的键。
     * @param clazz 缓存成为的类型。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    public static <T> void cacheBean(String key,
                                        Class<T> clazz,
                                        ResponseListener<T> listener) {

        CareersheCache.getInstance().getBean(key, clazz, new CacheListener<T>() {
            @Override
            public void onSuccess(int code, T data) {
                listener.onSuccess(code, data);
            }

            @Override
            public void onFailed() {
                listener.onFailed(CareersheApi.ApiCode.FAILED_NO_CACHE, "");
            }
        });
    }


    /**
     * 直接请求网络并缓存本地-单个对象。
     *
     *
     * @param rxLife 请求的生命周期。
     * @param observable 回调结果（被观察者、上游）。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    public static <T> void netBean(RxLife rxLife,
                                   Observable<CareersheResponse<T>> observable,
                                   ResponseListener<T> listener) {
        rxLife.add(request(observable, listener, new ResponseToCache<T>() {
            @Override
            public boolean onResponse(T resp) {
                return true;
            }
        }));
    }

    /**
     * 直接请求网络并缓存本地-单个对象。
     *
     *
     * @param rxLife 请求的生命周期。
     * @param observable 回调结果（被观察者、上游）。
     * @param key 缓存的键。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    public static <T> void netBean(RxLife rxLife,
                                   Observable<CareersheResponse<T>> observable,
                                   String key,
                                   ResponseListener<T> listener) {
        rxLife.add(request(observable, listener, new ResponseToCache<T>() {
            @Override
            public boolean onResponse(T resp) {
                CareersheCache.getInstance().save(key, resp);
                return true;
            }
        }));
    }

    /**
     * 先请求缓存再请求网络-单个对象。
     *
     * @param rxLife 请求的生命周期。
     * @param observable 回调结果（被观察者、上游）。
     * @param cacheKey 缓存的键。
     * @param clazz 缓存成为的类型。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    public static <T> void cacheAndNetBean(RxLife rxLife,
                                           Observable<CareersheResponse<T>> observable,
                                           String cacheKey,
                                           Class<T> clazz,
                                           ResponseListener<T> listener) {
        cacheAndNetBean(rxLife, observable, false, cacheKey, clazz, listener);
    }

    /**
     * 先请求缓存再请求网络-单个对象（带刷新）。
     *
     * @param rxLife 请求的生命周期。
     * @param observable 回调结果（被观察者、上游）。
     * @param refresh 是否刷新。
     * @param cacheKey 缓存的键。
     * @param clazz 缓存成为的类型。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    protected static <T> void cacheAndNetBean(RxLife rxLife,
                                              Observable<CareersheResponse<T>> observable,
                                              boolean refresh,
                                              String cacheKey,
                                              Class<T> clazz,
                                              ResponseListener<T> listener) {
        if (refresh) {
            //刷新方式请求，先请求网络再缓存到本地
            rxLife.add(request(observable, listener, new ResponseToCache<T>() {
                @Override
                public boolean onResponse(T resp) {
                    CareersheCache.getInstance().save(cacheKey, resp);
                    return true;
                }
            }));
            return;
        }
        //先缓存中取，不存在再请求网络，不同则重新缓存（判断是否相同）
        rxLife.add(CareersheCache.getInstance().getBean(cacheKey, clazz, new CacheListener<T>() {
            @Override
            public void onSuccess(int code, T data) {
                listener.onSuccess(code, data);
                rxLife.add(request(observable, listener, new ResponseToCache<T>() {
                    @Override
                    public boolean onResponse(T resp) {
                        if (CareersheCache.getInstance().isSame(data, resp)) {
                            return false;
                        }
                        CareersheCache.getInstance().save(cacheKey, resp);
                        return true;
                    }
                }));
            }

            @Override
            public void onFailed() {
                rxLife.add(request(observable, listener, new ResponseToCache<T>() {
                    @Override
                    public boolean onResponse(T resp) {
                        //缓存失败再次缓存
                        CareersheCache.getInstance().save(cacheKey, resp);
                        return true;
                    }
                }));
            }
        }));
    }


    /**
     * 直接请求缓存（不存在则失败）-对象集合。
     *
     * @param key 缓存的键。
     * @param clazz 缓存成为的类型。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    protected static <T> void cacheList(String key,
                                        Class<T> clazz,
                                        ResponseListener<List<T>> listener) {
        CareersheCache.getInstance().getList(key, clazz, new CacheListener<List<T>>() {
            @Override
            public void onSuccess(int code, final List<T> data) {
                listener.onSuccess(code, data);
            }

            @Override
            public void onFailed() {
                listener.onFailed(CareersheApi.ApiCode.FAILED_NO_CACHE, "");
            }
        });
    }


    /**
     * 直接请求网络并缓存本地-对象集合。
     *
     * @param rxLife 请求的生命周期。
     * @param observable 回调结果（被观察者、上游）。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    public static <T> void netList(RxLife rxLife,
                                   Observable<CareersheResponse<List<T>>> observable,
                                   ResponseListener<List<T>> listener) {
        rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
            @Override
            public boolean onResponse(List<T> resp) {
                return true;
            }
        }));
    }


    /**
     * 直接请求网络并缓存本地-对象集合。
     *
     * @param rxLife 请求的生命周期。
     * @param observable 回调结果（被观察者、上游）。
     * @param key 缓存的键。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    public static <T> void netList(RxLife rxLife,
                                   Observable<CareersheResponse<List<T>>> observable,
                                   String key,
                                   ResponseListener<List<T>> listener) {
        rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
            @Override
            public boolean onResponse(List<T> resp) {
                CareersheCache.getInstance().save(key, resp);
                return true;
            }
        }));
    }

    /**
     * 先请求缓存再请求网络-对象集合。
     *
     * @param rxLife 请求的生命周期。
     * @param observable 回调结果（被观察者、上游）。
     * @param cacheKey 缓存的键。
     * @param clazz 缓存成为的类型。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    protected static <T> void cacheAndNetList(RxLife rxLife,
                                              Observable<CareersheResponse<List<T>>> observable,
                                              String cacheKey,
                                              Class<T> clazz,
                                              ResponseListener<List<T>> listener) {
        cacheAndNetList(rxLife, observable, false, cacheKey, clazz, listener);
    }


    /**
     * 先请求缓存再请求网络-对象集合（带刷新）。
     *
     * @param rxLife 请求的生命周期。
     * @param observable 回调结果（被观察者、上游）。
     * @param refresh 是否刷新。
     * @param cacheKey 缓存的键。
     * @param clazz 缓存成为的类型。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     */
    protected static <T> void cacheAndNetList(RxLife rxLife,
                                              Observable<CareersheResponse<List<T>>> observable,
                                              boolean refresh,
                                              String cacheKey,
                                              Class<T> clazz,
                                              ResponseListener<List<T>> listener) {
        if (refresh) {
            //刷新方式请求，先请求网络再缓存到本地
            rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
                @Override
                public boolean onResponse(List<T> resp) {
                    CareersheCache.getInstance().save(cacheKey, resp);
                    return true;
                }
            }));
            return;
        }
        //先缓存中取，不存在再请求网络，不同则重新缓存（判断是否相同）
        rxLife.add(CareersheCache.getInstance().getList(cacheKey, clazz, new CacheListener<List<T>>() {
            @Override
            public void onSuccess(int code, final List<T> data) {
                listener.onSuccess(code, data);
                rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
                    @Override
                    public boolean onResponse(List<T> resp) {
                        if (CareersheCache.getInstance().isSame(data, resp)) {
                            return false;
                        }
                        CareersheCache.getInstance().save(cacheKey, resp);
                        return true;
                    }
                }));
            }

            @Override
            public void onFailed() {
                rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
                    @Override
                    public boolean onResponse(List<T> resp) {
                        //缓存失败再次缓存
                        CareersheCache.getInstance().save(cacheKey, resp);
                        return true;
                    }
                }));
            }
        }));
    }


    //==============================================================================================


    /**
     * （封装）请求网络（不带缓存监听）。
     *
     * @param observable 回调结果（被观察者、上游）。
     * @param listener 请求回调监听器。
     * @param <T> 请求结果的实例类型。
     * @return 可取消的请求。
     */
    protected static <T> Disposable request(
            @NonNull Observable<CareersheResponse<T>> observable,
            @NonNull ResponseListener<T> listener) {

        return RxRequest.create(observable)
                .listener(new RxRequest.RequestListener() {
                    @Override
                    public void onStart() {
                        listener.onStart();
                    }

                    @Override
                    public void onError(ExceptionHandle handle) {
                        listener.onError(handle);
                        listener.onFailed(CareersheApi.ApiCode.ERROR, handle.getMsg());
                    }

                    @Override
                    public void onFinish() {
                        listener.onFinish();
                    }
                })
                .request(new RxRequest.ResultCallback<T>() {
                    @Override
                    public void onSuccess(int code, T data) {
                        listener.onSuccess(code, data);
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        if (code == CareersheApi.ApiCode.FAILED_NOT_LOGIN) {
//                            UserUtils.getInstance().logout();
//                            LoginReceiver.sendNotLogin();
                        }
                        listener.onFailed(code, msg);
                    }
                });
    }

    /**
     * （封装）请求网络。
     *
     * @param observable 回调结果（被观察者、上游）。
     * @param listener 请求回调监听器。
     * @param responseToCache 缓存响应结果监听器。
     * @param <T> 请求结果的实例类型。
     * @return 可取消的请求。
     */
    protected static <T> Disposable request(
            @NonNull Observable<CareersheResponse<T>> observable,
            @NonNull ResponseListener<T> listener,
            ResponseToCache<T> responseToCache) {

        return RxRequest.create(observable)

                //监听请求网络进度
                .listener(new RxRequest.RequestListener() {
                    @Override
                    public void onStart() {
                        listener.onStart();
                    }

                    @Override
                    public void onError(ExceptionHandle handle) {
                        listener.onError(handle);
                        listener.onFailed(CareersheApi.ApiCode.ERROR, handle.getMsg());
                    }

                    @Override
                    public void onFinish() {
                        listener.onFinish();
                    }
                })
                //监听网络请求结果
                .request(new RxRequest.ResultCallback<T>() {
                    @Override
                    public void onSuccess(int code, T data) {
                        if (responseToCache.onResponse(data)) {
                            listener.onSuccess(code, data);
                        }
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        if (code == CareersheApi.ApiCode.FAILED_NOT_LOGIN) {
                            //如何响应结果是没有登录，App清除登录
//                            UserUtils.getInstance().logout();
//                            LoginReceiver.sendNotLogin();
                        }
                        listener.onFailed(code, msg);
                    }
                });
    }




    /**
     * 缓存响应结果监听接口。
     *
     * @param <T> 缓存的实例类型。
     */
    public interface ResponseToCache<T> {
        boolean onResponse(T resp);
    }
}
