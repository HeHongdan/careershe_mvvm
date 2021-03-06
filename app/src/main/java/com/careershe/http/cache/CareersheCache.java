package com.careershe.http.cache;

import android.annotation.SuppressLint;
import android.text.TextUtils;


import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.careershe.http.CareersheApi;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 类描述：网络缓存管理类。
 *
 * @author HeHongdan
 * @version v2020/11/29
 * @date 2020/11/29
 */
public class CareersheCache {

    /** 缓存空间大小。 */
    private static final int MAX_SIZE = 10 * 1024 * 1024;
    /** 缓存路径。 */
    public static final String DIR_NAME = "rx-cache";
    /** 本类实例。 */
    private static CareersheCache INSTANCE;
    /** 磁盘缓存管理。 */
    private DiskLruCache mDiskLruCache = null;
    /** Json数据处理工具。 */
    private Gson mGson = new Gson();

    /**
     * 构造方法。
     */
    private CareersheCache() {
        openDiskLruCache();
    }

    /**
     * 初始化。
     */
    public static void init() {
        if (INSTANCE == null) {
            INSTANCE = new CareersheCache();
        }
    }

    /**
     * 获取实例。
     *
     * @return 本类实例(WanCache)。
     */
    public static CareersheCache getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("RxCache未初始化");
        }
        return INSTANCE;
    }

    /**
     * 获取磁盘缓存。
     *
     * @return 磁盘缓存。
     */
    private DiskLruCache getDiskLruCache() {
        if (mDiskLruCache == null) {
            throw new RuntimeException("磁盘缓存 未初始化或初始化失败");
        }
        return mDiskLruCache;
    }

    /**
     * 打开缓存。
     */
    public void openDiskLruCache() {
        if (mDiskLruCache != null && !mDiskLruCache.isClosed()) {
            try {
                mDiskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(CacheUtils.getCacheDir(), DIR_NAME);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(file, 1, 1, MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断数据是否相同。
     *
     * @param cache 磁盘缓存。
     * @param net   网络请求。
     * @return 是否相同。
     */
    public boolean isSame(Object cache, Object net) {
        String cacheJson = mGson.toJson(cache);
        String netJson = mGson.toJson(net);
        return TextUtils.equals(cacheJson, netJson);
    }

    /**
     * （数据的实例）保存到磁盘缓存。
     *
     * @param key  数据的key。
     * @param bean 数据的实例。
     */
    public void save(String key, Object bean) {
        Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> emitter) throws Exception {
                saveSync(key, bean);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    /**
     * 根据key删除磁盘缓存（Rxjava）。
     *
     * @param key      数据的key。
     * @param listener 结果回调。
     * @return 封装成一次性操作。
     */
    public Disposable remove(String key, SimpleListener listener) {
        return Observable.just(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        removeSync(s);
                        listener.onResult();
                    }
                });
    }

    /**
     * 根据key获取对应的实例（Rxjava）。
     *
     * @param key
     * @param clazz
     * @param listener
     * @param <T>
     * @return
     */
    public <T> Disposable getBean(String key, Class<T> clazz, CacheListener<T> listener) {
        return Observable
                .create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                        T bean = getBeanSync(key, clazz);
                        if (bean == null) {
                            throw new NullCacheException();
                        }
                        emitter.onNext(bean);
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T bean) throws Exception {
                        listener.onSuccess(CareersheApi.ApiCode.SUCCESS, bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onFailed();
                    }
                });
    }


    /**
     * 根据key获取数据集合（Rxjava）。
     *
     * @param key
     * @param clazz
     * @param listener
     * @param <T>
     * @return
     */
    public <T> Disposable getList(String key, Class<T> clazz, CacheListener<List<T>> listener) {
        return Observable
                .create(new ObservableOnSubscribe<List<T>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<T>> emitter) throws Exception {
                        List<T> bean = getListSync(key, clazz);
                        if (bean == null) {
                            throw new NullCacheException();
                        }
                        emitter.onNext(bean);
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<T>>() {
                    @Override
                    public void accept(List<T> bean) throws Exception {
                        listener.onSuccess(CareersheApi.ApiCode.SUCCESS, bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onFailed();
                    }
                });
    }

    /**
     * 同步删除数据。
     *
     * @param key
     * @throws IOException
     */
    private void removeSync(String key) throws IOException {
        synchronized (getDiskLruCache()) {
//            getDiskLruCache().remove(MD5Coder.encode(key));
            getDiskLruCache().remove(EncryptUtils.encryptMD5ToString(key));
        }
    }

    /**
     * 同步保存数据。
     *
     * @param key
     * @param bean
     * @throws IOException
     */
    private void saveSync(String key, Object bean) throws IOException {
        synchronized (getDiskLruCache()) {
            DiskLruCache.Editor editor = getDiskLruCache().edit(EncryptUtils.encryptMD5ToString(key));
            editor.set(0, mGson.toJson(bean));
            editor.commit();
            getDiskLruCache().flush();
        }
    }

    /**
     * 同步获取数据对应的实例。
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return 实例。
     * @throws IOException
     */
    private <T> T getBeanSync(String key, Class<T> clazz) throws IOException {
        synchronized (getDiskLruCache()) {
            DiskLruCache.Snapshot snapshot = getDiskLruCache().get(EncryptUtils.encryptMD5ToString(key));
            String json = snapshot.getString(0);
            T bean = mGson.fromJson(json, clazz);
            snapshot.close();
            return bean;
        }
    }

    /**
     * 同步获取数据对应的实例集合。
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return 实例集合。
     * @throws IOException
     */
    private <T> List<T> getListSync(String key, Class<T> clazz) throws IOException {
        synchronized (getDiskLruCache()) {
            DiskLruCache.Snapshot snapshot = getDiskLruCache().get(EncryptUtils.encryptMD5ToString(key));
            String json = snapshot.getString(0);
            List<T> list = jsonToBeanList(json, clazz);
            snapshot.close();
            return list;
        }
    }

    /**
     * Json数据转实例集合。
     *
     * @param json
     * @param t
     * @param <T>
     * @return 实例集合。
     */
    private <T> List<T> jsonToBeanList(String json, Class<T> t) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        List<T> list = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonarray = parser.parse(json).getAsJsonArray();
        for (JsonElement element : jsonarray) {
            list.add(mGson.fromJson(element, t));
        }
        return list;
    }

    /**
     * 缓存的键（Key）。
     */
    @SuppressLint("DefaultLocale")
    public static class CacheKey {
        public static final String GET_PROFESSIONAL = CareersheApi.ApiUrl.GET_PROFESSIONAL;
        public static final String GET_LISTPAGE = CareersheApi.ApiUrl.GET_LISTPAGE;
        public static final String SEARCH_LIST_PAGE = CareersheApi.ApiUrl.SEARCH_LIST_PAGE;
        public static final String BANNER = "banner/json";
        /** 搜索。 */
        private static final String SEARCH = "article/query/%d/json?key=%s";//page+key
        /** 微信列表。 */
        private static final String WXARTICLE_LIST = "wxarticle/list/%d/%d/json";//id+page

    }


    /**
     * 关键字：加请求属性的请求缓存（如第几页是否收藏）。
     *
     * @param page 属性2。
     * @param isC 属性1。
     * @return 加属性的关键字。
     */
    public static String SEARCH(int page,String isC) {
        return String.format(CacheKey.SEARCH, page, isC);
    }


//    private static String addUserId(String key) {
//        int userId = UserUtils.getInstance().getUserId();
//        return userId + "@" + key;
//    }

}
