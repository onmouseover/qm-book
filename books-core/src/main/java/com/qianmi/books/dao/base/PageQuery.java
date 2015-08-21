package com.qianmi.books.dao.base;

/**
 * 分页逻辑接口
 * 
 */
public interface PageQuery {

    /**
     * 根据原始sql获取count语句
     * 
     * @param sql
     * @return
     */
    public String getCountSql(String sql);

    /**
     * 根据原始sql获取分页sql语句
     */
    public String getPagedQuerySql(String sql, int startNum, int endNum);
}
