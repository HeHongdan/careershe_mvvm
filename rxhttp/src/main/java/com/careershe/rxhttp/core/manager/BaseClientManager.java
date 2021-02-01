package com.careershe.rxhttp.core.manager;

import retrofit2.Retrofit;

/**
 * 类描述：用于管理Retrofit实例。
 * 子类继承后自行判断是否采用单例模式。
 *
 * @author HeHongdan
 * @version v2020/11/25
 * @date 2020/11/25
 */
public abstract class BaseClientManager {
    protected abstract Retrofit create();
}
