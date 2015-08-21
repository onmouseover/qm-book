package com.qianmi.books.dao.base;

import java.io.Serializable;

/**
 * <p>
 * 分页信息
 * </p>
 * 
 */
public class Page implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 默认的分页大小.
     */
    public static final int DEFAULT_PERSIZE = 20;

    /**
     * 总记录集合.
     */
    private int totalRows;

    /**
     * 总页数.
     */
    private int totalPages;

    /**
     * 每页显示数.
     */
    private int perSize = DEFAULT_PERSIZE;

    /**
     * 当前请求页.
     */
    private int curPage = 1;

    /**
     * 根据请求页数构造分页对象.
     * 
     * @param requestNumber
     *            请求页数.
     */
    public Page(int requestNumber) {
        setCurPage(requestNumber);
    }

    /**
     * 增加空构造
     */
    public Page() {
    }

    public int getTotalRows() {
        return totalRows;
    }

    /**
     * 设置总记录集数.
     * 
     * @param totalRows
     *            总记录集数.
     */
    public void setTotalRows(int totalRows) {
        if (totalRows >= 0) {
            this.totalRows = totalRows;
            this.totalPages = (totalRows - 1) / this.perSize + 1;

            if (this.totalPages < this.curPage) {
                this.curPage = 1;
            }
        }
    }

    /**
     * 获取每页大小.
     * 
     * @return 大小.
     */
    public int getPerSize() {
        return perSize;
    }

    /**
     * 设置分页大小.
     * 
     * @param perSize
     *            每页显示数.
     */
    public void setPerSize(int perSize) {
        if (perSize > 0) {
            this.perSize = perSize;
        }
    }

    public int getCurPage() {
        return curPage;
    }

    /**
     * 设置当前请求页数.
     * 
     * @param curPage
     *            请求的页数.
     */
    public void setCurPage(int curPage) {
        // modify by lihe 2012.9.25 curPage可以为0
        if (curPage >= 0) {
            this.curPage = curPage;
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    /**
     * 重写toString
     * 
     * @return 字符串.
     */
    public String toString() {
        StringBuffer msg = new StringBuffer("PageInfo:");
        msg.append("curPage=");
        msg.append(curPage);
        msg.append(",totalPages=");
        msg.append(totalPages);
        msg.append(",perSize=");
        msg.append(perSize);
        msg.append(",totalRows=");
        msg.append(totalRows);
        return msg.toString();
    }

}
