package com.careershe.http.bean;


import com.careershe.rxhttp.request.base.BaseBean;

/**
 * 类描述：分页数据。
 *
 * @author HeHongdan
 * @version v12/3/20
 * @date 12/3/20
 */
public class PageBean extends BaseBean {

    /** 一页请求条数 */
    private int size;
    /** 当前第几页 */
    private int curPage;
    /** 是否到最后一页 */
    private boolean over;
    /** 数据总共条数 */
    private int total;
    /** 总多少页 */
    private int pageCount;


    public int getSize() {
        return size;
    }

    public int getCurPage() {
        return curPage;
    }

    public boolean getOver() {
        return over;
    }

    public int getTotal() {
        return total;
    }

    public int getPageCount() {
        return pageCount;
    }

    @Override
    public String toString() {
        return "页数数据{" +
                "一页数=" + size +
                ", 当前页=" + curPage +
                ", 最后一页=" + over +
                ", 总条数=" + total +
                ", 总页数=" + pageCount +
                '}';
    }
}
