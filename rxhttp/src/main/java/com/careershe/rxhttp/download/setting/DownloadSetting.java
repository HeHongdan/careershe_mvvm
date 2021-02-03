package com.careershe.rxhttp.download.setting;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.careershe.rxhttp.download.DownloadInfo;

/**
 * 类描述：下载请求配置。
 *
 * @author HeHongdan
 * @version v2020/11/22
 * @date 2020/11/22
 */
public interface DownloadSetting {

    /**
     * 设置默认BaseUrl。
     *
     * @return BaseUrl。
     */
    @NonNull
    String getBaseUrl();

    /**
     * 获取超时时长，单位为毫秒。
     *
     * @return 超时时长（最小1毫秒）。
     */
    @IntRange(from = 1)
    long getTimeout();

    /**
     * 获取连接超时时长，单位为毫秒。
     * <p>
     *     返回0则取{@link DownloadSetting#getTimeout()}。
     * </p>
     *
     * @return 连接超时时长（最小0毫秒）。
     */
    @IntRange(from = 0)
    long getConnectTimeout();

    /**
     * 获取读取超时时长，单位为毫秒。
     * <p>
     *     返回0则取{@link DownloadSetting#getTimeout()}。
     * </p>
     * @return 读取超时时长（最小0毫秒）。
     */
    @IntRange(from = 0)
    long getReadTimeout();

    /**
     * 获取写出超时时长，单位为毫秒。
     * <p>
     *     返回0则取{@link DownloadSetting#getTimeout()}。
     * </p>
     * @return 写出超时时长（最小0毫秒）。
     */
    @IntRange(from = 0)
    long getWriteTimeout();

    /**
     * 获取保存下载文件路径。
     *
     * @return 保存路径。
     */
    @Nullable
    String getSaveDirPath();

    /**
     * 获取断点续传模式（追加、替换、重命名）。
     *
     * @return 断点续传模式。
     */
    @NonNull
    DownloadInfo.Mode getDefaultDownloadMode();
}
