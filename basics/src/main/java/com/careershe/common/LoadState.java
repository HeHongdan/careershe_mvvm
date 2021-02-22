package com.careershe.common;

/**
 * 类描述：网络加载状态。
 *
 * @author HeHongdan
 * @date 2/19/21
 * @since v2/19/21
 */
public enum LoadState {

    /** 加载中。 */
    LOADING,

    /** 没有网络。 */
    NO_NETWORK,

    /** 没有数据。 */
    NO_DATA,

    /** 加载出错。 */
    ERROR,

    /** 加载成功。 */
    SUCCESS
}
