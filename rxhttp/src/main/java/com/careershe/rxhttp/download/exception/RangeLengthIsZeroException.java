package com.careershe.rxhttp.download.exception;

/**
 * TODO
 * @author Cuizhen
 * @date 2018/10/12
 */
public class RangeLengthIsZeroException extends RuntimeException {
    public RangeLengthIsZeroException() {
        super("断点处请求长度为0");
    }
}
