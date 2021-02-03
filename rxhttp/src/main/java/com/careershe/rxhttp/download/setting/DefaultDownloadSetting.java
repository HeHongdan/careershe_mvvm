package com.careershe.rxhttp.download.setting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.careershe.rxhttp.download.DownloadInfo;

/**
 * 类描述：默认下载请求配置。
 *
 * <p>
 *     默认路径         ==  "http://api.rxhttp.download/"
 *     超时           ==  60000
 *     默认断点续传   ==  追加
 * </p>
 *
 * @author HeHongdan
 * @version v2020/11/22
 * @date 2020/11/22
 */
public class DefaultDownloadSetting implements DownloadSetting {

    @NonNull
    @Override
    public String getBaseUrl() {
        return "http://api.rxhttp.download/";
    }

    @Override
    public long getTimeout() {
        return 60000;
    }

    @Override
    public long getConnectTimeout() {
        return 0;
    }

    @Override
    public long getReadTimeout() {
        return 0;
    }

    @Override
    public long getWriteTimeout() {
        return 0;
    }

    @Nullable
    @Override
    public String getSaveDirPath() {
        return null;
    }

    @NonNull
    @Override
    public DownloadInfo.Mode getDefaultDownloadMode() {
        return DownloadInfo.Mode.APPEND;
    }
}
