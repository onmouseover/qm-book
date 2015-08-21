package com.qianmi.books.dao.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分页逻辑封装类，主要封装分页sql语句处理，这里仅支持mysql数据库
 * 
 * @see
 * @since
 */
public class MysqlDefaultPageQuery implements PageQuery {

    /**
     * logger
     */
    protected static Logger logger = LoggerFactory.getLogger(MysqlDefaultPageQuery.class);

    /**
     * 分页查询函数.获取总数
     * 
     * @param sql
     *            原始sql
     * @return countQueryString 获取所有记录数sql
     */
    public String getCountSql(String sql) {
        logger.debug("PageQuery.getCountSql() IN");
        logger.debug("sql = {}", sql);
        // Count查询
        String countQueryString = " select count(*) from(" + removeOrders(sql) + ") l";
        logger.debug("countQueryString = {}", countQueryString);

        logger.debug("PageQuery.getCountSql() OUT");
        return countQueryString;
    }

    /**
     * 分页查询函数.
     * 
     * SELECT NAME, ERROR_MSG, CREAT_TIME FROM ( SELECT NAME A , ERROR_MSG,
     * CREAT_TIME, ROWNUM ROWNO FROM ( SELECT NAME, ERROR_MSG, CREAT_TIME FROM
     * T_COMM_EXCEPTION T WHERE T.ERROR_CODE = '-14501' ) ) WHERE ROWNO >= 51
     * AND ROWNO <= 60
     * 
     * @param sql
     *            原始sql语句
     * @param curPage
     *            当前序号
     * @param perSize
     *            分页大小
     * @return pagedQuerySql 分页查询语句
     */
    public String getPagedQuerySql(String sql, int curPage, int perSize) {
        int startNum = curPage * perSize;

        logger.debug("PageQuery.getPagedQuerySql() IN");
        logger.debug("sql = {},startNum = {},perSize = {}", new Object[] { sql, startNum, perSize });

        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);

        pagingSelect.append(" select * from(");
        pagingSelect.append(sql);
        pagingSelect.append(" ) s limit " + startNum + "," + perSize);

        return pagingSelect.toString();

    }

    /**
     * 去除sql的orderby 子句，用于pagedQuery.
     * 
     * @param
     */
    private String removeOrders(String sql) {
        logger.debug("PageQuery.removeOrders() IN");
        logger.debug("sql = {}", sql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S&&[^\\)]]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sql);
        logger.debug("m = {}", m);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        logger.debug("sb is: {}", sb.toString());
        logger.debug("PageQuery.removeOrders() OUT");
        return sb.toString();
    }

}
