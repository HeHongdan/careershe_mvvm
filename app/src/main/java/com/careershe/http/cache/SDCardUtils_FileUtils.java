package com.careershe.http.cache;

import android.os.Environment;
import android.text.TextUtils;

import com.careershe.rxhttp.core.RxHttp;

import java.io.File;
import java.math.BigDecimal;

/**
 * 类描述：缓存在SD的工具类。
 *
 * @author HeHongdan
 * @version v2020/11/28
 * @date 2020/11/28
 */
public class SDCardUtils_FileUtils {


    /**
     * SD卡是否可以操作。
     *
     * @return 是否可以操作。
     */
    public static boolean isSDCardAlive() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡的Dir（绝对路径）。
     *
     * @return SD路径。
     */
    public static String getCacheDir() {
        File cacheFile = null;
        if (isSDCardAlive()) {
            cacheFile = RxHttp.getAppContext().getExternalCacheDir();
        }
        if (cacheFile == null) {
            cacheFile = RxHttp.getAppContext().getCacheDir();
        }
        return cacheFile.getAbsolutePath();
    }

    /**
     * 获取系统的下载（绝对路径）。
     *
     * @return 下载路径。
     */
    public static String getDownloadCacheDir() {
        File dir = null;
        if (isSDCardAlive()) {
            dir = RxHttp.getAppContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        }
        if (dir == null) {
            dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
        return dir.getAbsolutePath();
    }

    //==============================================================================================
    /**
     * 删除指定除外的数据。
     *
     * @param file 指定文件夹。
     * @param except 除外的数据。
     */
    public static void delete(File file, String except) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            String[] children = file.list();
            for (String c : children) {
                File childFile = new File(file, c);
                if (!TextUtils.equals(childFile.getName(), except)) {
                    delete(childFile);
                }
            }
        } else {
            if (!TextUtils.equals(file.getName(), except)) {
                file.delete();
            }
        }
    }

    /**
     * 删除指定文件夹。
     *
     * @param file 文件夹。
     * @return 是否删除成功。
     */
    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            String[] children = file.list();
            for (String c : children) {
                boolean success = delete(new File(file, c));
                if (!success) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /**
     * 获取文件/文件夹的文件大小。
     *
     * @param file 文件/文件夹。
     * @return 空间大小。
     */
    public static long getSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File f : fileList) {
                if (f.isDirectory()) {
                    size = size + getSize(f);
                } else {
                    size = size + f.length();
                }
            }
        } catch (Exception ignore) {
        }
        return size;
    }

    /**
     * 格式化单位（KB、MB、GB、TB）。
     *
     * @param size 字节大小。
     * @return 格式化后单位。
     */
    public static String formatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0KB";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
