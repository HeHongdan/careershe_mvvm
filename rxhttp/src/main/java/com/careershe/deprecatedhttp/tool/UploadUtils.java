package com.careershe.deprecatedhttp.tool;

import android.text.TextUtils;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 类描述：Retrofit文件上传。
 *
 * @author HeHongdan
 * @date 2021/2/2
 * @since v2021/2/2
 */
public class UploadUtils {
    private static final String FILE_NOT_NULL = "文件不能为空";
    private static final String FILE_PATH_NOT_NULL = "文件路径不能为空";

    /**
     * 生成图片上传格式。
     *
     * @param path
     * @return
     */
    public static MultipartBody.Part getMultipartBody(String path) {
        if(TextUtils.isEmpty(path)){
            throw new NullPointerException(FILE_PATH_NOT_NULL);
        }
        File file = new File(path);
        if (file.exists()){
            RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imgFile", file.getName(), requestFile);

            return body;
        } else {
            return null;
        }
    }

    /**
     * 生成文件上传格式。
     *
     * @param file
     * @return
     */
    public static MultipartBody.Part getMultipartBody(File file) {
        if (file.exists()) {
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("application/octet-stream"), file);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            return body;
        } else {
            throw new NullPointerException(FILE_NOT_NULL);
        }
    }

    /**
     * 生成(多)文件上传格式。
     *
     * @param paths
     * @return
     */
    public static List<MultipartBody.Part> getMultipartBodysForPath(List<String> paths) {
        if (paths.isEmpty()) throw new NullPointerException(FILE_PATH_NOT_NULL);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("application/octet-stream"), file);
                builder.addFormDataPart("file", file.getName(), requestFile);
            } else {
                throw new NullPointerException(FILE_NOT_NULL);
            }
        }
        return builder.build().parts();
    }

    /**
     * 生成(多)文件上传格式。
     *
     * @param files
     * @return
     */
    public static List<MultipartBody.Part> getMultipartBodysForFile(List<File> files) {
        if (files.isEmpty()) throw new NullPointerException(FILE_NOT_NULL);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            if (file.exists()) {
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("application/octet-stream"), file);
                builder.addFormDataPart("file", file.getName(), requestFile);
            } else {
                throw new NullPointerException(FILE_NOT_NULL);
            }
        }
        return builder.build().parts();
    }

}
