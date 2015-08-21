package com.qianmi.books.dao.base;

/**
 * <p>
 * 分页帮助
 * </p>
 * 
 * 
 */
public final class PageQueryHelper {

    /**
     * Content of pageQuery
     */
    static ThreadLocal<Page> pageInfo = new ThreadLocal<Page>();

    private PageQueryHelper() {
    }

    /**
     * <p>
     * 是否分页查询
     * </p>
     * 
     * @param
     * @return Boolean
     */
    public static Boolean isPageQuery() {
        return pageInfo.get() == null ? false : true;
    }

    /**
     * <p>
     * 设置分页信息
     * </p>
     * 
     * @param value
     *            分页对象
     */
    public static void setPageInfo(Page value) {
        pageInfo.set(value);
    }

    /**
     * <p>
     * 获取分页信息
     * </p>
     * 
     * @param
     * @return Page
     */
    public static Page getPageInfo() {
        return pageInfo.get();
    }

    /**
     * <p>
     * clear
     * </p>
     */
    public static void clear() {
        pageInfo.remove();
    }
}