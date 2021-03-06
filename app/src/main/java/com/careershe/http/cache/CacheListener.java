package com.careershe.http.cache;

/**
 * 类描述：缓存回调接口。
 *
 * @author HeHongdan
 * @version v2020/11/29
 * @date 2020/11/29
 */
public interface CacheListener<E> {
    /**
     * 缓存成功回调。
     *
     * @param code
     * @param data
     */
    void onSuccess(int code, E data);

    /**
     * 缓存失败回调。
     */
    void onFailed();
}
