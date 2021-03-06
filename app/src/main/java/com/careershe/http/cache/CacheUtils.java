package com.careershe.http.cache;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;

/**
 * 类描述：缓存辅助类。
 *
 * @author HeHongdan
 * @version v2020/11/29
 * @date 2020/11/29
 */
public class CacheUtils {

    /**
     * 获取App默认缓存文件夹（优先返回SD卡中的缓存文件夹）。
     *
     * @return 缓存文件夹。
     */
    public static String getCacheDir() {
        File cacheFile = null;
        if (SDCardUtils_FileUtils.isSDCardAlive()) {
            cacheFile = Utils.getApp().getExternalCacheDir();
        }
        if (cacheFile == null) {
            cacheFile = Utils.getApp().getCacheDir();
        }
        return cacheFile.getAbsolutePath();
    }

    /**
     * 获取App默认缓存文件夹内的缓存大小。
     *
     * @return 默认缓存大小
     */
    public static String getTotalCacheSize() {
        long cacheSize = SDCardUtils_FileUtils.getSize(Utils.getApp().getCacheDir());
        if (SDCardUtils_FileUtils.isSDCardAlive()) {
            cacheSize += SDCardUtils_FileUtils.getSize(Utils.getApp().getExternalCacheDir());
        }
        return SDCardUtils_FileUtils.formatSize(cacheSize);
    }

    /**
     * 清除App默认缓存文件夹内的缓存。
     */
    public static void clearAllCache() {
        SDCardUtils_FileUtils.delete(Utils.getApp().getCacheDir());
        if (SDCardUtils_FileUtils.isSDCardAlive()) {
            SDCardUtils_FileUtils.delete(Utils.getApp().getExternalCacheDir());
        }
    }

}
