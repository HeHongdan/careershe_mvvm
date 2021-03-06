package com.careershe.http.bean;


import com.careershe.rxhttp.request.base.BaseBean;

import java.util.List;

/**
 * 类描述：问答列表(主页-问答)。
 *
 * @author HeHongdan
 * @date 3/5/21
 * @since v3/5/21
 */
public class QnaListBean extends BaseBean {

	/** 问答列表。 */
	private List<QnaBean> result;
	/** 分页。 */
	private PageBean pageResult;

	public List<QnaBean> getResult() {
		return result;
	}

	public PageBean getPageResult() {
		return pageResult;
	}
}
