package com.careershe.rxhttp.download.exception;

/**
 * TODO
 * @author Cuizhen
 * @date 2018/10/12
 */
public class SaveFileDirMakeException extends RuntimeException {
    public SaveFileDirMakeException() {
        super("下载保存的文件父文件夹创建失败");
    }
}
